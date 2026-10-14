# Session 13 - Bai 2: Cap JWT

Yeu cau JDK 21 va Docker. Chay `docker compose up -d --wait`, sau do `./gradlew :identity-service:bootRun`.

Dang ky bang `POST http://localhost:8080/api/auth/register` voi `{"username":"alice","password":"password123"}`. Goi `GET http://localhost:8080/api/auth/test-token?username=alice` de nhan JWT. Payload co `sub`, `userId`, `role`, `iat`, `exp`. Thoi han 3600000 ms. Dat `JWT_SECRET` giong nhau o cac service, it nhat 32 byte UTF-8. Gia tri mac dinh chi dung cho demo.

Endpoint test-token chi co trong bai 2 vi cap token khong can mat khau.
