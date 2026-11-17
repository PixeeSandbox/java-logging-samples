# catalog-search

Product catalog search API backed by an embedded Solr core (`solr-home/products`).

```bash
mvn spring-boot:run
curl -X POST localhost:8080/api/products -H 'Content-Type: application/json' \
  -d '{"id":"SKU-100","name":"Stainless water bottle"}'
curl 'localhost:8080/api/products/search?q=bottle'
```
