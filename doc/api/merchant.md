```
curl -v -XPOST 'http://localhost:10100/merchant/create' \
  --header "Content-Type: application/json" \
  --data '{
      "merNum": "商户编号", 
      "merName": "商户全称",
      "merShortName": "商户简称",
      "status": "1",
      "adminId": 123,
      "adminAuthLetter": "管理员授权函",
      "merAttr": "1",
      "createBy": 456
    }'

curl -v -G 'http://localhost:10100/merchant/get' \
  --data-urlencode 'filters=[
      {"field": "id", "operator": "=", "value": 1}
  ]'

curl -v -G 'http://localhost:10100/merchant/count' \
  --data-urlencode 'filters=[
  ]'

curl -v -G 'http://localhost:10100/merchant/list' \
  --data-urlencode 'page=0' \
  --data-urlencode 'size=2' \
  --data-urlencode 'order=-createDate' \
  --data-urlencode 'filters=[
  ]'

curl -v -XPOST 'http://localhost:10100/merchant/update' \
  --header "Content-Type: application/json" \
  --data '{
      "id": 1
    }'

curl -v -XPOST 'http://localhost:10100/merchant/delete' \
  --data-urlencode 'filters=[
      {"field": "id", "operator": "=", "value": 1}
  ]'
```