# Create a game
- path: `/games`
- method: `POST`
- body:
  - expected type: JSON
  - object properties:
    | Name | Type |
    |------|------|
    | `title` | `String` |
    | `listPrice` | `Integer` |
    | `salePrice` | `Integer` |
    | `coverImage` | `String` |
    | `isActive` | `Boolean` |
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `id` | `String` |
    | `title` | `String` |
    | `listPrice` | `Integer` |
    | `salePrice` | `Integer` |
    | `coverImage` | `String` |
    | `isActive` | `Boolean` |
- failed response:

# Get a game
- path: `/games/<game-id>`
- method: `GET`
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `id` | `String` |
    | `title` | `String` |
    | `listPrice` | `Integer` |
    | `salePrice` | `Integer` |
    | `coverImage` | `String` |
    | `isActive` | `Boolean` |
- failed response:

# Delete a game
- path: `/games/<game-id>`
- method: `DELETE`
- successful response:
  - status code: `200`
- failed response:

# List all registered games
- path: `/games`
- method: `GET`
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `id` | `String` |
    | `title` | `String` |
    | `listPrice` | `Integer` |
    | `salePrice` | `Integer` |
    | `coverImage` | `String` |
    | `isActive` | `Boolean` |
- failed response:
