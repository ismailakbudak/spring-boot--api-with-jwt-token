# Spring Boot API Example App with JWT Token Authorization 

## API End Points
### Login As Merchant
```
curl -i -H "Content-Type: application/json" -X POST -d '{
    "email": "merchant@dev.com",
    "password": "password" 
}' https://reporting-service-api.herokuapp.com/api/v3/merchant/user/login
```
Set Authorization token in  your terminal to use in below curl requests
```
$ YOUR_TOKEN=set_your_token
$ echo $YOUR_TOKEN
```

#### Transactions Report
- Implemented all feature of this end point
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "fromDate": "2018-12-04", 
    "endDate": "2060-01-04"
}' https://reporting-service-api.herokuapp.com/api/v3/transactions/report
```
You can use all filters
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "fromDate": "2018-12-04", 
    "endDate": "2060-01-04",
    "merchant": 3,
    "acquirer": 11
}' https://reporting-service-api.herokuapp.com/api/v3/transactions/report
```

#### Transaction Detail
- Implemented all feature of this end point
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "transactionId": "transaction_10"
}' https://reporting-service-api.herokuapp.com/api/v3/transaction
```
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "transactionId": "not_exists_transaction"
}' https://reporting-service-api.herokuapp.com/api/v3/transaction
```

#### Client Detail
- Implemented all feature of this end point
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "transactionId": "transaction_10"
}' https://reporting-service-api.herokuapp.com/api/v3/client
```
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "transactionId": "not_exists_transaction"
}' https://reporting-service-api.herokuapp.com/api/v3/client
```

#### Transaction List
- Not implemented all feature of this end point
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "fromDate": "2018-12-04", 
    "endDate": "2060-01-04"
}' https://reporting-service-api.herokuapp.com/api/v3/transaction/list
```

## Development
- Assembles and tests this project
```
$ gradle build
```
- Runs the unit tests
```
$ gradle test
```
- Runs this project as a Spring Boot application
```
$ gradle bootRun
```

## License

Copyright (c) 2018 İsmail Akbudak
