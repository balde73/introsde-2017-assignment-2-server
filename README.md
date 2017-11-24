# introsde-2017-assignment-2-server

Federico Baldessari | federico.baldesssari@studenti.unitn.it

[Heroku Server](https://example-balde.herokuapp.com/sdelab/person)

## About the code
The project is divided into 4 packages
- `rest`: contains _App.java_ and _MyApplicationConfig.java_ in order to run the server
- `rest.dao`: contains the data access object that connects to the database and persist data. All the CRUD operations happens here
- `rest.model`: contains the entity
- `rest.resources`: contains the classes that handle the REST response
- `rest.utils`: _DBHandler.java_ and _DateHandler.java_

The configuration file are: `build.xml` and `ivy.xml`

## Tasks
The server allow every call as defined in the requirements.
request#10 updates the value of the activitytype only for the particular activity interested!
PUT /person/{id}/{activity_type}/{activity_id}


## Deploy
Clone the repository
```
git clone https://github.com/balde73/introsde-2017-assignment-2-server.git
```
open the directory and run
```
ant create.war
```
now deploy the war on heroku
```
heroku war:deploy <WAR_PATH> --app <APP_NAME>
```
