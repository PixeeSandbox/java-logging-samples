# parcel-tracking

Records hub scans for parcels and serves their tracking history.

```bash
mvn spring-boot:run
curl -X POST localhost:8080/api/parcels/1001/scans -H 'Content-Type: application/json' \
  -d '{"type":"PICKED_UP","hubId":12}'
curl localhost:8080/api/parcels/1001
```
