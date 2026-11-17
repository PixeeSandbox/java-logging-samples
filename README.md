# invoice-service

Customer invoicing API.

```bash
mvn spring-boot:run
curl -X POST localhost:8080/api/invoices \
  -H 'Content-Type: application/json' \
  -d '{"customerId":"C-2291","amount":149.00,"memo":"November retainer"}'
```
