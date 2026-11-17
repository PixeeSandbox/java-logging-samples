# shipping-quotes

Freight quote API.

```bash
mvn spring-boot:run
curl -X POST localhost:8080/api/quotes \
  -H 'Content-Type: application/json' -H 'X-Client-Id: acme-web' \
  -d '{"origin":"RDU","destination":"SEA","weightKg":12.5,"reference":"PO-1182"}'
```
