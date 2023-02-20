# High level overview

We have created a simple application that comprises a backend app built in Java with Spring Webflux.

The purpose of the app is to create shortened versions of URLs. For example, given the url `https://github.com/VivyTeam/url-shortener-be-test`, the backend application would create a short url such as `http://localhost:9000/41e515a91a72`.

## Functional requirements

The application must have three endpoints:

- one endpoint is used by the client to shorten a given URL. 
  This endpoint will be used either by a web client or another service.
  Example: `https://google.com?q=somelongurl -> https://myservicedomain.de/31u98`.


- one endpoint is used by the client to get the original version of the URL, given its shortened version.
  This endpoint will be used by another service which contains a shortened URL and want to find out its original version.
  Example: `https://myservicedomain.de/31u98 -> https://google.com?q=somelongurl`.


- one endpoint is used by the client to evaluate and redirect the user to the original URL, given the shortened version.
  This endpoint will be used by a web client when an end-user tries to access the shortened URL and should be redirected to the original URL.
  Example: `https://myservicedomain.de/31u98 redirects to https://google.com?q=somelongurl`.

## Non-functional requirements

- code must be written using Spring Webflux with Reactor
- any JVM language can be chosen
- write all kinds of tests you judge necessary

## Important things you should consider when designing your service

- what is the capacity of my service? How much can it handle/scale?
- service should be observable
- every decision made regarding the design of the service or the technologies may be questioned later