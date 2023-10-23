# Show all Transactions

Show information about all transactions.

**URL** : `http://localhost:8080/api/transactions`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any transactions

**Code** : `200 OK`

**Content example** : This example response contains details of all transactions.

```json
[
    {
        "transactionId": 1,
        "amount": 2333.0,
        "transactionOk": true,
        "transactionDate": "2023-10-08",
        "transactionTime": "22:31:02",
        "timestamp": "Last time edited: 2023-10-08 22:31:02"
    },
    {
        "transactionId": 2,
        "amount": 2333.0,
        "transactionOk": true,
        "transactionDate": "2023-10-08",
        "transactionTime": "22:31:03",
        "timestamp": "Last time edited: 2023-10-08 22:31:03"
    },
    {
        "transactionId": 4,
        "amount": 2333.0,
        "transactionOk": true,
        "transactionDate": "2023-10-08",
        "transactionTime": "22:31:04",
        "timestamp": "Last time edited: 2023-10-08 22:31:04"
    }
]
```

## Error Response

**Condition** : If there are no transactions

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response provides information about all sales transactions. 
In case there are no sales transactions, an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___


# Show single sales transaction

Show information about sales transaction based on its unique identifier `transactionId`.

**URL** : `http://localhost:8080/api/transactions/{transactionId}`

**Method** : `GET`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `transactionId` is found

**Code** : `200 OK`

**Content example** : This example response contains details of a single sales transaction.

```json
{
    "transactionId": 1,
    "amount": 2333.0,
    "transactionOk": true,
    "transactionDate": "2023-10-08",
    "transactionTime": "22:31:02",
    "timestamp": "Last time edited: 2023-10-08 22:31:02"
}
```

## Error Response

**Condition** : If there is no sales transaction with given `transactionId`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This endpoint provides information about a single sales transaction based on its unique identifier.
In the case of no sales transaction being found with the provided `transactionId`, 
an HTTP 404 NOT FOUND response is returned with an empty JSON object.
___

