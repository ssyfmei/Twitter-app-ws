# twitter-app-ws

This a backend program of a twitter-like system. Backend services are exposed to frontend as APIs. Front end apps can get user inputs and call these APIs to inform backend servers to do corresponding operations.



## API requests

The APIs are HTTP-based RESTful APIs that use JSON Web Token for authorization. API request and response bodies are formatted in JSON or XML.



#### user API

| Method   | URL                                         | Description              |
| -------- | ------------------------------------------- | ------------------------ |
| `post`   | 1http://www.demosite.com/users/              | Create a new user        |
| `get`    | 1http://www.demosite.com/users/userId        | Get info of a user       |
| `put`    | 1http://www.demosite.com/users/userId        | Update a user            |
| `delete` | 1http://www.demosite.com/users/userId        | Delete a user            |
| `get`    | 1http://www.demosite.com/users/userId/tweets | Get all tweets of a user |



#### tweet API

| Method   | URL                                    | Description         |
| -------- | -------------------------------------- | ------------------- |
| `post`   | 1http://www.demosite.com/tweets         | Post a new tweet    |
| `get`    | 1http://www.demosite.com/tweets/tweetId | Get info of a tweet |
| `put`    | 1http://www.demosite.com/tweets/tweetId | Update a tweet      |
| `delete` | 1http://www.demosite.com/tweets/tweetId | Delete a tweet      |



#### friend API

| Method   | URL                             | Description                   |
| -------- | ------------------------------- | ----------------------------- |
| `post`   | 1http://www.demosite.com/friends | Establish friend relationship |
| `delete` | 1http://www.demosite.com/friends | Remove friend relationship    |



## Get Started

#### Deployment

1. Set up database connection parameters in application.properties file.
2. Produce a war file and deploy it on a server using a servlet container e.g. Tomcat or Jetty.

#### User Login

Send a post request to www.demosite.com/users/login. The request body formatted in JSON:

```json
{
    "email": "sha@gmail.com",
    "password":"abc123"
}
```

The response header contains an authorization token.

```http
AUthorization →Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtY295QGdtYWlsLmNvbSIsImV4cCI6MTUzNzQ1OTM2M30.FvRlDENJsV3ihjgSw8zCMyebNoBIqlXm--JmDuiWsc8vi--tVIgdThP0NYYXrlND0SacqZz0khWfyMFjWzgOjw
```

This header will authorize a frontend app to manipulate data of the specific user. Add this header to following API calls.
