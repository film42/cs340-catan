# CS340 - Settlers of Catan

## About
This implementation written in JavaScript on the front end and Java backend was created by Jon George, Garrett Thornburg, Adam Johnson, Steve Allred, and June Tang; and of course help from the 340 TAs.

## Running
Run the server with the following arguments:

```
ant our-server checkpoint-interval (SQLPersisence|MongoDBPersistence) erase?

# Definitions:
# ------------
# checkpoint-interval: Save checkpoint after n commands
# persistence: Mongo, SQLite, or In Memory
# erase?: Set true to erase the persistence plugin's contents
```