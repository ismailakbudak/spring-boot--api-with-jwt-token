## Login As Merchant
```
curl -i -H "Content-Type: application/json" -X POST -d '{
    "email": "merchant@dev.com",
    "password": "password" 
}' https://reporting-service-api.herokuapp.com/api/v3/merchant/user/login
```
Set Authorization token in console for further usage
```
$ YOUR_TOKEN=set_your_token
$ echo $YOUR_TOKEN
```

## Transactions Report
- Implemented all feature of this end point
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "fromDate": "2018-12-04", 
    "endDate": "2060-01-04"
}' https://reporting-service-api.herokuapp.com/api/v3/transactions/report
```
Use all filter queries
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "fromDate": "2018-12-04", 
    "endDate": "2060-01-04",
    "merchant": 3,
    "acquirer": 11
}' https://reporting-service-api.herokuapp.com/api/v3/transactions/report
```


## Transaction List
- Not implemented all feature of this end point
```
curl -i -H "Content-Type: application/json" -H "Authorization: $YOUR_TOKEN" -X POST -d '{
    "fromDate": "2018-12-04", 
    "endDate": "2060-01-04"
}' https://reporting-service-api.herokuapp.com/api/v3/transaction/list
```