# Show all Transactions

Show information about all transactions.

**Endpoint** : `/api/transactions`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If there are any transactions

**Code** : `200 OK`

**Content example** : This example response contains details of all transactions.

```json
[
    {
        "transactionId": 1,
        "amount": 15.0,
        "transactionOk": false,
        "transactionDate": "2023-10-10",
        "transactionTime": "10:10:10"
    },
    {
        "transactionId": 2,
        "amount": 25.0,
        "transactionOk": true,
        "transactionDate": "2023-10-10",
        "transactionTime": "10:10:10"
    },
    {
        "transactionId": 3,
        "amount": 35.0,
        "transactionOk": false,
        "transactionDate": "2023-10-10",
        "transactionTime": "10:10:10"
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

**Endpoint** : `/api/transactions/{transactionId}`

**Method** : `GET`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the specified `transactionId` is found

**Code** : `200 OK`

**Content example** : This example response contains details of a single sales transaction.

```json
{
    "transactionId": 8,
    "amount": 123456.8,
    "transactionOk": true,
    "transactionDate": "2023-11-03",
    "transactionTime": "19:06:50"
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

