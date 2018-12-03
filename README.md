## Login As Merchant
```
curl -i -H "Content-Type: application/json" -X POST -d '{
    "email": "merchant@dev.com",
    "password": "password" 
}' https://reporting-service-api.herokuapp.com/api/v3/merchant/user/login
```

## Transaction List
- Not implemented all feature of this end point
```
curl -i -H "Content-Type: application/json"  -H "Authorization: YourToken" -X POST -d '{
    "fromDate": "2018-12-04", 
    "endDate": "2060-01-04"
}' https://reporting-service-api.herokuapp.com/api/v3/transaction/list
```