## Create profile
- path: `/profiles`
- method: `POST`
- body:
  - expected type: JSON
  - object properties:
    | Name | Type |
    |------|------|
    | `firstName` | `String` |
    | `lastName` | `String` |
    | `wallet` | `Integer` |
    | `points` | `Integer` |
    | `email` | `String` |
    | `password` | `String` |
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `firstName` | `String` |
    | `lastName` | `String` |
    | `wallet` | `Integer` |
    | `points` | `Integer` |
    | `email` | `String` |
    | `id` | `String` |
    | `library` | `Array<Game>` |
- failed response:

## Get profile
- path: `/profiles/<profile-id>`
- method: `GET`
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `firstName` | `String` |
    | `lastName` | `String` |
    | `wallet` | `Integer` |
    | `points` | `Integer` |
    | `email` | `String` |
    | `id` | `String` |
    | `library` | `Array<Game>` |
- failed response:

## Delete profile
- path: `/profiles/<profile-id>`
- method: `DELETE`
- successful response:
  - status code: `200`
- failed response:

## List all registered profiles
- path: `/profiles`
- method: `GET`
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `firstName` | `String` |
    | `lastName` | `String` |
    | `wallet` | `Integer` |
    | `points` | `Integer` |
    | `email` | `String` |
    | `id` | `String` |
    | `library` | `Array<Game>` |
- failed response:

## Buy product for a profile
- path: `/profiles/<profile-id>/cart/<game-id>`
- method: `POST`
- successful response:
  - status code: `200`
- failed response:
