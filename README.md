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
 * Run the `dockerComposeUp` gradle task.
 

## Clone this repository

`$ git clone https://github.com/jorgetavares89/payment-hub.git`
	
## Running the application

	Note: 
	To execute the next step, we put the docker installed in the host machine must be executed in the admin domain (running without sudo)

#### The dockerComposeUp gradle task

  This task aims to build all solution with just once command `$ gradle dockerComposeUp`. Running this command, a pipe of gradle tasks will be executing, following the sequence:
  ```
  :dockerComposeDown
  :cleanDockerImage 
  :createDockerFile
  :buildDockerImage
  ```
  This tasks, is executed for each microservice, generating all docker images. After that, the `dockerComposeUp` task is executed, coming up automatically all infrastructure.
  
  When the is build script is done, should be up the following architecture:

<p align="center"><img src="https://github.com/jorgetavares89/payment-hub/blob/master/PaymentHub.png"/></p>

	Note:
	In the root app path, has a Postman collections .json exported to test the APIs.

