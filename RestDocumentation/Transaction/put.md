# Edit sales Transaction

Edit sales transaction.

**Endpoint** : `/api/transactions/{transactionId}`

**Method** : `PUT`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    
    "amount": "double",
    "transactionOk": "boolean",
    "transactionDate":  "String min=10, max=10 yyyy-MM-dd",
    "transactionTime": "String min=8, max=8 hh-mm-ss"
    
}
```

**Data example**

```json
{
    
    "amount": 156.0,
    "transactionOk": true,
    "transactionDate": "2023-10-08",
    "transactionTime": "21:22:39"
    
}
```

## Success Response

**Code** : `200 OK`  

```json
{
    "transactionId": 4,
    "amount": 156.0,
    "transactionOk": true,
    "transactionDate": "2023-10-08",
    "transactionTime": "21:22:39",
    "timestamp": "Last time edited: 2023-10-08 22:12:35"
}
```

## Error response  

Id does not exist:  

**Code** : `404 Not Found`  

**Message** : `The requested resource could not be found.`  

Unexpected input:  
```json
{
    
    "amount": 156.0,
    "transactionOk": false,
    "transactionDate": "This is not ",
    "transactionTime": "valid input"
    
}
```

**Code** : `400 Bad Request`  

**Message** :  

```json
{
    "transactionTime": "size must be between 8 and 8",
    "transactionDate": "size must be between 10 and 10",
    "transactionOk": "must be true"
}
```

