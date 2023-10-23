# Delete single sales Transaction

Delete single sales transaction based on its unique identifier `transactionId`.

**URL** : `http://localhost:8080/api/transactions/{transactionId}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Auth required** : YES

**Permission required** : NO

**Data constraints** : `{}`

## Success Response

**Condition** : If the sales transaction id is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the removed sales transaction.  
```json
{
    "transactionId": 25,
    "amount": 2333.0,
    "transactionOk": true,
    "transactionDate": "2023-10-08",
    "transactionTime": "22:28:49",
    "timestamp": "Last time edited: 2023-10-08 22:28:49"
}
```
## Error Response

**Condition** : If the sales transaction is not found with the given `transactionId`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the removed sales transaction. Please note that once you receive an HTTP 200 OK response, this particular sales transaction will be permanently deleted.
