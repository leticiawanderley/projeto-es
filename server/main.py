#!/usr/bin/env python
#
# Copyright 2007 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
import webapp2, json, urllib
from models import *
from google.appengine.api import urlfetch


CLIENT_ID = "803781473340-fio6taanbucc2jrkth43hu8tm5mi1se6.apps.googleusercontent.com"
CLIENT_SECRET = "x1ILif-C8tUEPEkO54k-mFKq"
SERVER_URL = "https://mao-na-massa2.appspot.com"


class MainHandler(webapp2.RequestHandler):
    def get(self):
        self.response.write("Hi, meu fi! That's Mao Na Massa Server. Don't try to invade")


class CodeHandler(webapp2.RequestHandler):
    def get(self):
        state = ""
        acc_tk = ""
        id = ""
        id_status = {}
        if self.request.get("code") and self.request.get("state") == state:
            form_fields = {
                "grant_type": "authorization_code",
                "code": self.request.get("code"),
                "client_id": CLIENT_ID,
                "client_secret": CLIENT_SECRET,
                "redirect_uri": SERVER_URL + "/code"
            }
            form_data = urllib.urlencode(form_fields)
            result = urlfetch.fetch(url="https://accounts.google.com/o/oauth2/token",
                                    payload=form_data,
                                    method=urlfetch.POST,
                                    headers={'Content-Type': 'application/x-www-form-urlencoded'})

            if result.status_code == 200:
                content = json.loads(result.content)
                id_token = content["id_token"]
                acc_tk = content["access_token"]
                self.response.write("https://www.googleapis.com/oauth2/v1/tokeninfo?id_token=" + id_token)
                try:
                    jwt = self.verify_id_token(id_token)
                    id_status['valid'] = True
                    id_status['id'] = jwt['user_id']
                    id_status['email'] = jwt['email']
                    id_status["verified_email"] = jwt["verified_email"]
                    id = id_status['id']
                except:
                    id_status['valid'] = False
                    id_status['id'] = None
                self.response.write(json.dumps(id_status))
                if "valid" in id_status and id_status["valid"]:
                    name, photo = self.request_name(acc_tk, id_status["id"])
                    new_user = User(
                        key_name=id_status["id"],
                        id=id_status["id"],
                        name=name,
                        photo=photo,
                        email=db.Email(id_status["email"]),
                        verified_email=id_status["verified_email"],
                        access_token=acc_tk
                    )
                    new_user.put()

        if "valid" in id_status:
            self.redirect('/login?authorized=yes&access_token=' + acc_tk + "&id=" + id)
        else:
            self.redirect('/login?authorized=no')



    def request_name(self, acc_tk, id):
        result = urlfetch.fetch(url="https://www.googleapis.com/plus/v1/people/" + id,
                                method=urlfetch.GET,
                                headers={'Authorization': 'Bearer ' + acc_tk})
        result = json.loads(result.content)
        return result["name"]["givenName"] + " " + result["name"]["familyName"] if "name" in result else "", \
               result["image"]["url"] if "image" in result else ""

    def verify_id_token(self, id_token):
        result = urlfetch.fetch("https://www.googleapis.com/oauth2/v1/tokeninfo?id_token=" + id_token)
        result = convert(json.loads(result.content))
        if "error" in result:
            raise Exception
        else:
            return result


def convert(input):
    if isinstance(input, dict):
        return {convert(key): convert(value) for key, value in input.iteritems()}
    elif isinstance(input, list):
        return [convert(element) for element in input]
    elif isinstance(input, unicode):
        return input.encode('utf-8')
    else:
        return input


class LoginHandler(webapp2.RequestHandler):
    def get(self):
        if self.request.get("authorized"):
            if self.request.get("authorized") == "yes":
                self.response.write(
                    "ACCESS TOKEN:" + self.request.get("access_token") + "<br>USER ID:" + self.request.get("id"))
            else:
                self.response.write("Not Authorized")

        else:
            state = ""
            self.redirect(
                "https://accounts.google.com/o/oauth2/auth?scope=email%20profile&" +
                "state=%s&redirect_uri=" % state + SERVER_URL + "/code&" +
                "response_type=code&client_id=" + CLIENT_ID)


class MeHandler(webapp2.RequestHandler):
    def get(self):
        id = self.request.get("id")
        acc_tk = self.request.get("token")
        user = User.get(db.Key.from_path("User", id))
        if user.access_token == acc_tk:
            i = user.photo.find("?") + 1
            i = user.photo[i:].find("=") + 1 + i
            photo = user.photo[:i] + "200"
            self.response.write(json.dumps({"id": user.id, "name": user.name, "email":user.email, "photo": photo}))


class SearchHandler(webapp2.RequestHandler):
    """
    UNFINISHED.
    """

    def get(self):
        all = Recipe.all()
        recipes_list = []
        for recipe in all:
            recipe_dict = {"id": recipe.key().id(), "name": recipe.name}
            user_dict = {"id": recipe.author.id, "email": recipe.author.email, "name": recipe.author.name,
                         "photo": recipe.author.photo}
            recipe_dict["author"] = user_dict
            recipe_dict["ingredients"] = recipe.ingredients
            steps_list = []
            for step in sorted(list(recipe.steps), key=lambda x: x.order, reverse=True):
                step_dict = {"id": step.key().id(), "description": step.description, "time": step.time}
                steps_list.append(step_dict)
            recipe_dict["steps"] = steps_list

            recipes_list.append(recipe_dict)
        self.response.write(json.dumps(recipes_list))


class CreateHandler(webapp2.RequestHandler):
    def get(self):
        if self.request.get("json") and self.request.get("what"):
            my = self.request.get("json")
            result = json.loads(my)
            what = self.request.get("what")
            token = self.request.get("token")
            user_id = urllib.unquote(self.request.get("user_id")).strip()

            if what == "Recipe":
                self.create_recipe(result, user_id, token)
        else:
            self.error(400)


    def create_recipe(self, result, user_id, token):
        """
        Creates the recipe itself
        """
        user = User.get(db.Key.from_path("User", user_id))
        if user.access_token == token:
            new_recipe = Recipe(
                author=user,
                name=result.get("name"),
                ingredients=result.get("ingredients"))
            new_recipe.put()

            steps = result.get("steps")
            for i in range(len(steps)):
                step = steps[i]
                new_step = Step(description=step.get("description"), time=step.get("time"), order=i,
                                recipe=new_recipe)
                new_step.put()
        else:
            self.response.write("Not Authorized")
            self.error(401)


app = webapp2.WSGIApplication([
                                  ('/', MainHandler),
                                  ('/code', CodeHandler),
                                  ('/me', MeHandler),
                                  ('/login', LoginHandler),
                                  ('/create', CreateHandler),
                                  ('/search', SearchHandler)
                              ], debug=True)
