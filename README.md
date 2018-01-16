# Payment Online Cloud Solution

This is the Payment Online cloud solution. Responsible to list a payment methods by Restaurant and process request that's coming from Brazil and Mexico. this solution is compose of five `Java microservices` that use the `Spring Framework and Netflix OSS` as the base of everything.

 ```
  Just to curiosity, the solution was developed using the following:
	 - Spring Boot
	 - Spring Web 
	 - Spring Data
	 - Spring Aspect
	 - Spring Zuul (Netflix OSS)
	 - Controller advice
	 - Docker and Docker compose
	 - MariaDB
 ```

Below we have the list of micro services

 ``` 
 - country-gateway-ms
 - payment-gatewy-ms
 - payment-methods-ms
 - payment-br-ms
 - payment-mx-ms
 ```

### country-gateway-ms
* A Zuul Proxy and Filter SpringBoot Microservice responsible to redirect request coming from BR and MX and redirect for the respective service. Below follow the mapping:
 ```yaml
   prefix: 
   	/payment
   	routes:
	            /**     redirect to payment-methods-ms
	            /mx/**  redirec to payment-mx-ms
	            /br/**  redirect to payment-gateway-ms 
```

### payment-gateway-ms
* A Zuul Proxy and Filter SpringBoot Microservice responsible to redirect request coming from BR and redirect to `payment-br-ms`. Follow the mapping:

 ```yaml
   prefix: 
   	/hub
   	routes:
	            /**     redirect to payment-br-ms
```

### payment-methods-ms
* A Spring Boot Microservice responsible to list the payment methods by a Restaurant ID. When this application is up, the `DatabasePopulator` run and populate the payment-methods DB, creating some dummy relationship of `PaymentMethod` and `Restaurant`. Below follow a resquest and result example:

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
* A Spring Boot Microservice responsible to process a `RequestPayment` that becomes from `/**` of `payment-gateway-ms`. This application has a Strategy with mock provider payments services to process the `Digital Wallet (Visa Checkout, Google, Masterpass)` and `Cred card brand (Visa,Mastercard)` requests. Below follow a resquest and result example:

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
* A Spring Boot Microservice responsible to process a `RequestPayment` that becomes from `/mx` of `gateway-gateway-ms`. Below follow a resquest and result example:

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

To build de docker image, we need to install the [https://gradle.org/install/](Gradle) or use the wrapper that can be find in root application path. Each application, has a build docker image gradle task to help a generate a docker image. Just go to the root path aplication, e.g. `$ cd hub-payment/payment-methods-ms/` and run `$ gradle buildDockerImage` and the `payment-methods-ms:0.0.1` image will be generated. To confirm, run: `$ docker images`.

It's **necessary** general the docker images of **all** projects, `country-gateway-ms`, `payment-gateway-ms`, `payment-methods-ms`, `payment-br-ms` and `payment-mx-ms` **before** try to run the solution.

	Note: 
	The docker installed at host machine, needs to be in the admin domain (running without sudo)
	
## Running the application

To up the docker containers by generated images , we have a `docker-compose` file on root path `(can be locale in hub-payment/docker-compose.yml)`.  Just run:  `$ docker-compose up` and the solution is coming up automatically. This command should be up the following architecture:

<p align="center"><img src="https://github.com/jorgetavares89/hub-payment/blob/master/HubPPayment.png"/></p>



