# MOIP CHALLENGE

Challenge proposed by MOIP.

## Getting Started

Rest API build with Spring Boot. 
Persistence layer using Spring Data MongoDB. 
Application has mongodb embbeded.
Default Running port 8080.  

### Prerequisites

```
Java 8
Maven
```

### Getting the code

Clone repository:

```
$ git clone git://github.com/eliasbusato/moip-challenge.git
```

### Building application

Run the following maven command into root folder:

```
$ mvn clean package -U
```

### Running application

Execute the following commando into root folder:

```
$ java -jar moip-challenge-api/target/moip-challenge-api-0.0.1-SNAPSHOT.jar
```

## Endpoints

Availables endpoints are:

### Creates a new payment

POST http://<server>:<port>/payments

Valid payment types are: "BOLETO", "CREDIT_CARD"

Request body example for a BOLETO payment:

```
{
	"amount" : 30.00,
	"clientId" : "09023FU213A1R6VRAJ225",
	"customer" : {
		"cpf" : "00000000191",
		"email" : "john@doe.com",
		"name" : "John Doe"
	},
	"details" : {
		"type" : "BOLETO"
	}
}
```

All fields are required.

Request body example for a CREDIT_CARD payment:

```
{
	"amount" : 30.00,
	"clientId" : "09023FU213A1R6VRAJ225",
	"customer" : {
		"cpf" : "00000000191",
		"email" : "john@doe.com",
		"name" : "John Doe"
	},
	"details" : {
		"type" : "CREDIT_CARD",
		"brand" : "VISA",
		"expiration" : "2020-02",
		"number" : "39483294832",
		"holder" : "john doe",
		"cvv" : "666"
	}
}
```

All fields are required.
Valid values for brand field are: "VISA", "MASTERCARD".
Expiration date must follow the pattern: yyyy-MM

### Retrieves status of a created payment
	
GET http://<server>:<port>/payments/<paymentId>

Response example: 

```
{
    "id": "5b0723b8e997093a70eb5597",
    "customer": {
        "id": "5b0723b7e997093a70eb5596",
        "name": "John Doe",
        "email": "john@doe.com",
        "cpf": "00000000191"
    },
    "type": "CREDIT_CARD",
    "status": "DECLINED",
    "amount": 30,
    "brand": "VISA",
    "holder": "john doe",
    "number": "39483294832",
    "cvv": "666",
    "expiration": "2020-02-01"
}
```

## Built With

* [Eclipse](https://www.eclipse.org/) - IDE
* [Spring](https://spring.io/) - Java Framework
* [Maven](https://maven.apache.org/) - Dependency Management
