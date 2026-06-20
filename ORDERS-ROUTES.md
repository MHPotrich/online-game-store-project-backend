## Get order
- path: `/order/<order-id>`
- method: `GET`
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `id` | `String` |
    | `profile` | `Profile` |
    | `total` | `Integer` |
    | `discound` | `Integer` |
    | `isComplete` | `Boolean` |
    | `items` | `Game` |
    | `creationDate` | `String` |
    | `paymentMethod` | `Payment` |
- failed response:
