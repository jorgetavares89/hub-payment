# Payment Online Cloud (POC) Solution

This project aims to be an Online Payment solution designed to act as a list of gateway of payment methods for Restaurant, processing the request coming to both Brazil and Mexico restaurants.
This solution makes usage of recent and well known frameworks families such as `Spring and Netflix OSS`.

 ```
  Just out of curiosity below are listed some of the tecnologies used to build the microservices and its dependencies:
	 - Spring Boot
	 - Spring Web (REST)
	 - Spring Data
	 - Spring Aspect
	 - Spring Zuul (Netflix OSS)
	 - Docker and Docker compose
	 - MariaDB
 ```

The micro services that compose the solution are:

 ``` 
 - country-gateway-ms
 - payment-gatewy-ms
 - payment-methods-ms
 - payment-br-ms
 - payment-mx-ms
 ```

### country-gateway-ms
* A microservice built on top of Zuul and SpringBoot. It aims to redirect requests coming from BR and MX and redirect them to its own service. Below follow the mapping:
 ```yaml
   prefix: 
   	/payment
   	routes:
	            /**     redirect to payment-methods-ms
	            /mx/**  redirec to payment-mx-ms
	            /br/**  redirect to payment-gateway-ms 
```

### payment-gateway-ms
* A microservice built on top of Zuul and SpringBoot. It aims to redirect requests coming from BR and redirect to `payment-br-ms`. Follow the mapping:

 ```yaml
   prefix: 
   	/hub
   	routes:
	            /**     redirect to payment-br-ms
```

### payment-methods-ms
* A microservice built on top SpringBoot that aims to be responsible to list the payment methods by a Restaurant ID. When this application is up, the `DatabasePopulator` run and populate the payment-methods DB, creating some dummy relationship of `PaymentMethod` and `Restaurant`. Below follow a resquest and result example:

Request:

`http://localhost:8080/payment/methods/restaurant/2`

Result:
```json
{
    "id": 2,
    "name": "Big Jack",
    "paymentMethods": [
        {
            "id": 1,
            "name": "Cred Card",
            "type": "ONLINE"
        },
        {
            "id": 2,
            "name": "Digital Wallet",
            "type": "ONLINE"
        }
    ]
}
```

### payment-br-ms
* A microservice built on top SpringBoot that aims to be responsible to process a `RequestPayment` that comes from `/**` of `payment-gateway-ms`. This application has a Strategy with a mock of the providers of some payment service to process the `Digital Wallet (Visa Checkout, Google, Masterpass)` and `Cred card brand (Visa,Mastercard)` requests. Below follow a resquest and result example:

Request

```http://localhost:8080/payment/br/hub/restaurant/4```

body
```json
{
	"restaurantId": 2,
	"userId": 2,
	"countryCode": "BR",
	"paymentBrand": "MASTERCARD",
	"credCard": "4716436403106225",
	"digitalWallet": null
}
```

Result:
```json
{
    "id": 2817310435374561510,
    "restaurantId": 2,
    "userId": 2,
    "status": "Visa/Master Brand Successfully process BR payment"
}
```

### payment-mx-ms
* A microservice built on top SpringBoot that aims to be responsible to process a `RequestPayment` that comes from `/mx` of `gateway-gateway-ms`. Below follow a resquest and result example:

Request:

```http://localhost:8080/payment/mx/restaurant/1```

body
```json
{
	"restaurantId": 1,
	"userId": 2,
	"countryCode": "MX",
	"paymentBrand": "MASTERCARD",
	"credCard": "4716436403106225",
	"digitalWallet": null
}
```

Result:
```json
{
    "id": 2817310435374561510,
    "restaurantId": 2,
    "userId": 2,
    "status": "Visa/Master Brand Successfully process BR payment"
}
```

# Running on Local environment

To run this application in local environment we need to :

 * Clone this repository.
 * Build a docker image. 
 * Run the docker-compose file.
 

## Clone this repository

`$ git clone https://github.com/jorgetavares89/hub-payment.git`

## Build Docker images

To build the docker image, we need to install the [https://gradle.org/install/](Gradle) or use its wrapper that can be found in the root folder of each application. All applications has a Gradle task that builds the docker image. Just go to the root application path, e.g. `$ cd hub-payment/payment-methods-ms/` and run `$ gradle buildDockerImage` and the `payment-methods-ms:0.0.1` image will be generated. To confirm, run: `$ docker images`.

It is **required** to generate the docker images of **all** projects, `country-gateway-ms`, `payment-gateway-ms`, `payment-methods-ms`, `payment-br-ms` and `payment-mx-ms` **before** tyring trying to use this project.

	Note: 
	The docker installed in the host machine must be executed in the admin domain (running without sudo)
	
## Running the application

Once you have generated the images of each application, you can get the containers up and running by using the `docker-compose` file on root path `(can be locale in hub-payment/docker-compose.yml)`.  Just run:  `$ docker-compose up` and the solution is coming up automatically. This command should be up the following architecture:

<p align="center"><img src="https://github.com/jorgetavares89/hub-payment/blob/master/HubPPayment.png"/></p>

	Note:
	In the root app path, has a Postman collections .json exported to test the APIs.

