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
import webapp2, json
from models import *


class MainHandler(webapp2.RequestHandler):
    def get(self):
        self.response.write("Hi, meu fi! That's Mao Na Massa Server. Don't try to invade")


class LoginHandler(webapp2.RequestHandler):
    def get(self):
        self.response.write('Not working yet!')


class SearchHandler(webapp2.RequestHandler):
    """
    UNFINISHED.
    """
    def get(self):
        all = Recipe.all()
        for recipe in all:
            self.response.write(">>>" + recipe.name + " " + recipe.author.email + "<br>")
            for step in sorted(list(recipe.steps), key=lambda x: x.order, reverse=True):
                 self.response.write(">>>>>>>" + step.description + " " + str(step.time) + "<br>")
            self.response.write("========<br>")


class CreateHandler(webapp2.RequestHandler):
    def get(self):
        if self.request.get("json") and self.request.get("what"):
            my = self.request.get("json")
            result = json.loads(my)
            what = self.request.get("what")

            if what == "User":
                self.create_user(result)
            elif what == "Recipe":
                self.create_recipe(result)
        else:
            self.error(400)


    def create_recipe(self, result):
        """
        Creates the recipe itself
        """
        new_user = User(email=db.Email("joopeeds@gmail.com"))
        new_user.put()

        new_recipe = Recipe(
            key_name=result.get("id"),
            id=result.get("id"),
            author=new_user,
            name=result.get("name"),
            ingredients=result.get("ingredients"))
        new_recipe.put()

        steps = result.get("steps")
        for i in range(len(steps)):
            step = steps[i]
            new_step = Step(id=step.get("id"), description=step.get("description"), time=step.get("time"), order=i, recipe=new_recipe)
            new_step.put()


    def create_user(self, result):
        """
        Creates the recipe itself
        """
        new_user = User(
            key_name=result.get("id"),
            id=result.get("id"),
            email=result.get("email")
        )
        new_user.put()


app = webapp2.WSGIApplication([
                                  ('/', MainHandler),
                                  ('/login', LoginHandler),
                                  ('/create', CreateHandler),
                                  ('/search', SearchHandler)
                              ], debug=True)
