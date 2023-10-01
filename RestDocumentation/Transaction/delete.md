# Delete single sales Transaction

Delete single sales transaction based on its unique identifier `transactionId`.

**URL** : `http://localhost:8080/api/transactions/{transactionId}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : NO

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the sales transaction id is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the removed sales transaction.
```json
{
    "transactionId": 5,
    "amount": 1500.0,
    "transactionDate": "01.10.2023 POSTMAN"
}
```

## Error Response

**Condition** : If the sales transaction is not found with the given `transactionId`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the removed sales transaction. Please note that once you receive an HTTP 200 OK response, this particular sales transaction will be permanently deleted.
