## Create profile
- path: `/profiles`
- method: `POST`
- body:
  - expected type: JSON
  - object properties:
    | Name | Type |
    |------|------|
    | `url` | `String` |
    | `description` | `String` |
    | `type` | `String` |
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `id` | `String` |
    | `url` | `String` |
    | `description` | `String` |
    | `type` | `String` |
- failed response:

## Get media
- path: `/media/<media-id>`
- method: `GET`
- successful response:
  - status code: `200`
  - object properties:
    | Name | Type |
    |------|------|
    | `id` | `String` |
    | `url` | `String` |
    | `description` | `String` |
    | `type` | `String` |
- failed response:

## Delete media
- path: `/media/<media-id>`
- method: `DELETE`
- successful response:
  - status code: `200`
- failed response:
