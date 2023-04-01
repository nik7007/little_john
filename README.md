# LittleJohn

In order to execute this application you must have installed maven and jdk17 and follow this steps in your termal:
> nvm package

> java -jar ./target/littlejohn-0.0.1-SNAPSHOT.jar

Or simply run the Dockerfile and the application will be automatically build, run and expose to the port 8080

In the following lines are present some example of working calls:

> GET http://localhost:8080/tickers <br />
> Authorization: Basic dGVzdDo=
>
> GET http://localhost:8080/tickers/PFE/history <br />
> Authorization: Basic dGVzdDo=
>
> GET http://localhost:8080/tickers/PFE/history?page=5 <br />
> Authorization: Basic dGVzdDo=

In the history call is present and optional params 'page'. If present it allows to obtain results up to 10 years in the
past.
