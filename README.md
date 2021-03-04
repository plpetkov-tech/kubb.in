# Kubb.in

An application made to manage your subscriptions, where you can access the renewal page of each subscription, be aware and notified when subscriptions are about to be renewed and change the frequency of each subscription (monthly, yearly, weekly...etc)

## Getting Started

Project is launched with docker compose so you need docker and docker compose to use it locally. Just docker-compose up --build -d to have everything up and running.

### Prerequisites

NPM
Docker and Docker Compose

```
// first modify the endpoint in the service in the front so it can use localhost in case you will run it locally
// you need an ssl certificate configured in the backend and front : sorry this are requirements so the prod app can be super secure and modern 👍
// in the server folder : 
mvn clean package

```

### Installing

After setting up the pre requisites just go to the main folder and run

```
docker-compose up --build # to see logs
docker-compose up --build -d # to run in detached mode

```

To launch each micro service in dev mode so you can tweak everything with hot reloading

```
// in the front 
npm run serve
// in the back 
mvn spring-boot:run
```


## Running the tests

For now empty. Need to develop end to end tests with cypress for the front and unit tests in the back

## Deployment

When having the desired app just as I wrote before 

```
docker-compose up --build -d
```

## Authors

* **Plamen Petkov** - *Project* - [PlamenPetkov](https://plamenpetkovonline.com/)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.
