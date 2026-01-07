# battle-code

Đây là Backend cho hệ thống Battlecode, được xây dựng dựa trên Java Spring Boot.

## Yêu cầu

- Java 21
- PostgreSQL 16+ (port mac dinh 5432)
- Redis (port mac dinh 6379)

## Cấu hình

File cấu hình chính nằm ở `src/main/resources/application.yaml`.

Thông số mặc định:

- DB: `jdbc:postgresql://localhost:5432/battlecode`
- user/pass: `postgres` / `1234`
- Redis: `localhost:6379`
- Port app: 8080

## Khởi tạo Database

Repo có file `battlecode.sql` (dump PostgreSQL) có thể dùng để  khởi tạo cơ sở dữ liệu.

## Chạy ứng dụng

```bash
./mvnw spring-boot:run
```

Hoặc build jar và chạy:

```bash
./mvnw -DskipTests package
java -jar target/battlecode-be-*.jar
```

Sau khi chay, API sẵn sàng tại `http://localhost:8080`. Tiếp theo sẽ cần một service Redis để sử dụng làm hàng đợi trận đấu, có thể chạy:
```bash
docker run -d  --name redis -p 6379:6379 redis:7
```
Tiếp đến sẽ cần một hoặc nhiều worker dùng để chạy submission tham khảo tại repo [battle-code-worker](https://github.com/Lalisa10/battle-code-worker)
