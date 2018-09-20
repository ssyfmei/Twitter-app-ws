# twitter-app-ws

This a backend program of a twitter-like system. Backend services are exposed to frontend as APIs. Front end apps can get user inputs and call these APIs to inform backend servers to do corresponding operations.



## API requests

The APIs are HTTP-based RESTful APIs that use JSON Web Token for authorization. API request and response bodies are formatted in JSON or XML.



#### user API

| Method   | URL                                         | Description              |
| -------- | ------------------------------------------- | ------------------------ |
| `post`   | http://www.demosite.com/users/              | Create a new user        |
| `get`    | http://www.demosite.com/users/userId        | Get info of a user       |
| `put`    | http://www.demosite.com/users/userId        | Update a user            |
| `delete` | http://www.demosite.com/users/userId        | Delete a user            |
| `get`    | http://www.demosite.com/users/userId/tweets | Get all tweets of a user |



#### tweet API

| Method   | URL                                    | Description         |
| -------- | -------------------------------------- | ------------------- |
| `post`   | http://www.demosite.com/tweets         | Post a new tweet    |
| `get`    | http://www.demosite.com/tweets/tweetId | Get info of a tweet |
| `put`    | http://www.demosite.com/tweets/tweetId | Update a tweet      |
| `delete` | http://www.demosite.com/tweets/tweetId | Delete a tweet      |



#### friend API

| Method   | URL                             | Description                   |
| -------- | ------------------------------- | ----------------------------- |
| `post`   | http://www.demosite.com/friends | Establish friend relationship |
| `delete` | http://www.demosite.com/friends | Remove friend relationship    |



## Get Started

