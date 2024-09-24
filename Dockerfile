FROM docker:latest

RUN apk add --no-cache docker-compose

WORKDIR /app

COPY docker-compose.yml /app/
COPY backend /app/backend
COPY frontend-beer-frontier /app/frontend-beer-frontier

CMD ["docker-compose", "up"]