```
sudo mkdir -p /data/docker/mysql

sudo chown -R "$USER":docker /data/docker/mysql

docker run -d -it \
  --restart always \
  --name sujiakeji-mysql \
  -p 3306:3306 \
  -v /data/docker/mysql:/var/lib/mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  mysql

MYSQL_ROOT_PASSWORD=root

docker exec -i sujiakeji-mysql mysql -u root -p"$MYSQL_ROOT_PASSWORD" \
  -e "create user 'dev'@'%' identified by 'dev';"

docker exec -i sujiakeji-mysql mysql -u root -p"$MYSQL_ROOT_PASSWORD" \
  -e 'create database sujiakeji default character set `utf8` collate `utf8_unicode_ci`;'

docker exec -i sujiakeji-mysql mysql -u root -p"$MYSQL_ROOT_PASSWORD" \
  sujiakeji < data/sql/schema.sql

docker exec -i sujiakeji-mysql mysql -u root -p"$MYSQL_ROOT_PASSWORD" \
  -e "grant all privileges on sujiakeji.* to 'dev'@'%';"

docker exec -i sujiakeji-mysql mysql -u root -p"$MYSQL_ROOT_PASSWORD" \
  -e "flush privileges"
```