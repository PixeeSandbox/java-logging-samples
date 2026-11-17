# returns-portal

Intake API for customer returns: opens an RMA for an order and lets the
storefront look it up.

```bash
docker build -t returns-portal .
docker run --rm -p 8080:8080 returns-portal
curl -X POST localhost:8080/api/returns -H 'Content-Type: application/json' \
  -H 'X-Store-Id: 14' -d '{"orderNumber":"A-10023","email":"pat@example.com","reason":"Wrong size"}'
```
