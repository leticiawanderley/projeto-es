import webapp2
from google.appengine.ext import db

class User(db.Model):
    name = db.StringProperty(required=True)
    email = db.EmailProperty(required=True)
    id = db.StringProperty(required=True)
    photo = db.StringProperty()
    verified_email = db.BooleanProperty(required=True)
    access_token = db.StringProperty(required=True)


class Recipe(db.Model):
    author = db.ReferenceProperty(User, required=True)
    name = db.StringProperty(required=True)
    ingredients = db.StringListProperty(str, indexed=True, default=[])
    #steps = db.ListProperty(db.ReferenceProperty(Step), indexed=True, default=[])

class Step(db.Model):
    order = db.IntegerProperty(required=True)
    description = db.StringProperty(required=True)
    time = db.FloatProperty(required=True)
    recipe = db.ReferenceProperty(Recipe, collection_name="steps")
