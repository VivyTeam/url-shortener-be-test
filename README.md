# High level overview

We have created a simple application that comprises a backend app built in Java with Spring Webflux.

The purpose of the app is to create shortened versions of URLs. For example, given the url `https://github.com/VivyTeam/url-shortener-be-test`, the backend application would create a short url such as `http://localhost:9000/41e515a91a72`.

The application has two endpoints, detailed below:

```
GET /{urlToBeShortened}/short # Shorten the given URL
GET /{shortenedUrl}/full # Returns the full URL given the shortened version
```

# Your task

- Implement the logic to shorten a URL
- Implement the logic to get the full URL given a shortened one
- Bonus: implement a new endpoint that redirects the request to the full version of the shortened URL, like
  ```
  GET /{shortenedUrl} # Redirects the request to the full version of the shortened URL
  ```

# Ports

Backend default port: `9000`