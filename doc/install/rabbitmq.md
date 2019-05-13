```
docker run -d -it \
  --restart always \
  --name sujiakeji-rabbitmq \
  -p 5672:5672 \
  -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=dev \
  -e RABBITMQ_DEFAULT_PASS=dev \
  rabbitmq:3-management-alpine
```

management
```
http://localhost:15672/
username: dev
password: dev
```