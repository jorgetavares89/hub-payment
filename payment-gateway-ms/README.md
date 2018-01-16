
# Payment BR Gateway Java Microservice

This is the Payment BR Gateway microservice, a `Spring Cloud Netflix Zuul Proxy` responsible to receive a request thats comming to Brazil and redirect a specific Payment provider. (Request becomes from `/payment/br/**`in `country-gateway-ms`ends here.)


# Running on Local environment

To run this application in local environment we need to :

 * Clone this repository.
 
 * Build a docker image. (Is necessary to have a Gradle installed)
 
 * Run the docker-compose file.
 

## Clone this repository

`$ git clone https://jorge_tavares@bitbucket.org/jorge_tavares/country-gateway-ms.git`

## Build Docker image

To build de docker image, we need to install the [https://gradle.org/install/](Gradle) or use the wrapper that can be find in root application path. After cloned the repos, run: `$ cd country-gateway-ms`and `$ gradle buildDockerImage`and than, the image should be generated with the name  `country-gateway-ms:0.0.1`
. To see, run: `$ docker images` 

	Notes: 
	1-The docker installed at host, needs to be in the admin domain (running without sudo)
	2-If you have to regeneated the image, we have a shell script on app root path, to do it, just run `$ bash regenerateDockerImage` and enjoy. :)
	
## Running the application

To up the docker container generated, we have a `docker-compose`file on root path. That's contains all dependencies to up this microservice (isolated of the solution, just to unit test). To run the `docker-compose`file, run: `$ docker-compose up`




