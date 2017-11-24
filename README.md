# introsde-2017-assignment-2-server

Federico Baldessari | federico.baldesssari@studenti.unitn.it

[Heroku Server](https://example-balde.herokuapp.com/sdelab/person)

## Project
The project is divided into 4 packages
`rest`: contains _App.java_ and _MyApplicationConfig.java_ in order to run the server
`rest.dao`: contains the data access object that connects to the database and persist data
`rest.model`: the model
`rest.utils`: _DBHandler.java_ and _DateHandler.java_

The configuration file are: `build.xml` and `ivy.xml`

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
