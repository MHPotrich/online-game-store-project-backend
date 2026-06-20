# Online Game Store Project Backend

## Requirements
- Docker
- Java

## Setup

- build container command: ```docker compose up```
- pgadmin server config:

	- Host Name/Address: ```host.docker.internal```

## Routes

### Profiles
- Get - `/profiles`
- Post - `/profiles`
- Get - `/profiles/<profile-id>`
- Delete - `/profiles/<profile-id>`

### Games
- Get - `/games`
- Post - `/games`
- Get - `/games/<game-id>`
- Delete - `/games/<game-id>`

### Orders
- Get - `/order/<order-id>`

### Media
- Get - `/media/<media-id>`
- Post - `/media`
- Delete - `/media`
