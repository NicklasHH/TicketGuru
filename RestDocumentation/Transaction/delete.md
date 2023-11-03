# Delete single sales Transaction

Delete single sales transaction based on its unique identifier `transactionId`.

**Endpoint** : `/api/transactions/{transactionId}`

**Method** : `DELETE`

**Content-Type** : `application/json`

**Authentication required** : YES

**Authority required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : If the sales transaction id is found

**Code** : `200 OK`

**Content example** : This example response contains the details of the removed sales transaction.  
```json
{
    "transactionId": 8,
    "amount": 156.0,
    "transactionOk": true,
    "transactionDate": "2023-10-08",
    "transactionTime": "21:22:39"
}
```
## Error Response

**Condition** : If the sales transaction is not found with the given `transactionId`

**Code** : `404 NOT FOUND`

**Content** : `{}`

## Notes

This example response allows you to review the details of the removed sales transaction. Please note that once you receive an HTTP 200 OK response, this particular sales transaction has been permanently deleted.
