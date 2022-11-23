db = db.getSiblingDB('morroldb');
db.dropDatabase();
db = db.getSiblingDB('morroldb');

db.createCollection('movies');
db.createCollection('studio');
db.createCollection('people');
db.people.insertMany([
    { "_id": new ObjectId,"first_name": "Arnold", "last_name": "Schwarzenegger", "birth_date": "1947-07-30"
    },
    {
        "_id": new ObjectId, "first_name": "James", "last_name": "Cameron", "birth_date": "1954-08-16"
    }
])
db.studio.insertMany([
    {
        "_id": new ObjectId, "name": "Warner Bros.", "year_founded": 1923, "movies": [156, 2234, 334, 2109] , "headquarters": { "address": "4000 Warner Blvd.", "city": "Burbank", "state": "California", "country": "United States"}
    }
])
db.movies.insertMany([
    {
        "_id": new ObjectId,
        "title": "The Terminator",
        "plot": "A cyborg killing machine is sent from 2029 to 1984 to execute",
        "director_id": 217,
        "release_year": 1984,
        "imdb": { "rating": 8.0, "yotes": 782237, "url": "https://www.imdb.com/title/tt0088247/", "last updated": null},
        "actors" : [
            {
                "person_id": 180, "as": "Terminator", "salary": 15000000
            },
            {
                "person_id": 13122, "as": "Sarah Connor"
            },
            {
                "person id": 13122, "as": "Kyle Reese"
            }
        ]
    }
])