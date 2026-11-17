# warehouse-slots

Dock appointment calendar: one slot per hour per warehouse per day.

```bash
mvn spring-boot:run
curl localhost:8080/api/warehouses/7/slots/2026-03-02
curl -X POST localhost:8080/api/warehouses/7/slots/2026-03-02/9 \
  -H 'Content-Type: application/json' -d '{"pallets":12,"priority":"EXPEDITED"}'
```
