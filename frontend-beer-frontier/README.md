# BeerFrontier

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 18.2.3.

## OpenAPI - Generate types from API
1. Update openapi.yaml with swagger.io editor
2. ```openapi-generator-cli generate -i openapi.yaml -g typescript-fetch -o src/app/api --additional-properties=modelPropertyNaming=original```
