# Simple REST App

## Abstract
This is the implementation of the simple REST service written using Spring Boot Framework. JUnit and Mockito were used for unit testing.
JPA and H2 in-memory database were used for storing entities. HTTP error handlers were added.  

## API

### LoginCounters

Endpoints for this API were created for testing purposes. They work with entities from `LOGIN_COUNTER` table, which consists of two columns:`LOGIN`(id) and `REQUEST_COUNT`.
`LOGIN_COUNTER` table stores number of [Users API](#usersApi) calls.

* `GET /counters/`

    **Response example**
    ```json
    [
        {
            "login": "octocat",
            "requestCount": 5
        },
        {
            "login": "tlpchk",
            "requestCount": 2
        }
    ]
    ```

* `GET /counters/{login}`
    
    **Response example (login=octocat)**
    ```json
    {
        "login": "octocat",
        "requestCount": 5
    }
    ```

### <a name="usersApi"></a> Users

Fetches data from https://api.github.com/users/{login} (e.g. https://api.github.com/users/octocat) and pass it in the response.
In `calculations` field value `6 / followers * (2 + public_repos)` is placed. When followers equals zero, then `"Infinity"` is returned.
Every call to this endpoint will increment `REQUEST_COUNT` value by one in `LOGIN_COUNTER` table for corresponding `LOGIN`.


* `GET /users/{login}`

    **Response example (login=octocat)**
    ```json
    {
        "id": 583231,
        "login": "octocat",
        "name": "The Octocat",
        "type": "User",
        "avatarUrl": "https://avatars.githubusercontent.com/u/583231?v=4",
        "createdAt": "2011-01-25T18:44:36Z",
        "calculations": 0.015251653268933296
    }
    ```
  
## Remarks and possible improvements
  * Docker container could be specified
  * Integration tests should be added
  * In-memory database could be changed with the persistent database
  * User authentication could be added to the LoginCounter API