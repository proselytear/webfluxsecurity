Учебный проект c использованием Spring Webflux Security
================================


## Цель видео - дать общее понимание этапов создания приложения с использованием следующих технологий:
 - Spring Security (JWT)
 - Spring WebFlux
 - Spring Data R2DBC
 - MapStruct
 - PostgreSQL
 - Flyway


## Локальный запуск приложения
- Установить PostgreSQL

## Создать БД
```sql
CREATE DATABASE "proselyte_webflux_security";
```

## Установить корректные значения в application.yaml
```sql
spring:r2dbc:username
```

```sql
spring:r2dbc:password
```

# cURL запросов:

## 1. Регистрация пользователя
```bash
curl --location 'http://localhost:8083/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data '{
    "username": "proselyte",
    "password": "test",
    "first_name": "Eugene",
    "last_name": "Suleimanov"
}'
```

Пример ответа:
```json
{
  "id": 1,
  "username": "proselyte",
  "role": "USER",
  "first_name": "Eugene",
  "last_name": "Suleimanov",
  "enabled": true,
  "created_at": "2023-05-13T14:53:32.36094",
  "updated_at": "2023-05-13T14:53:32.360954"
}
```

## 2. Аутентификация пользователя
```bash
curl --location 'http://localhost:8083/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "proselyte",
    "password": "test"
  }'
```

Пример ответа
```json
{
  "user_id": 1,
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0Iiwicm9sZSI6IlVTRVIiLCJpc3MiOiJwcm9zZWx5dGUiLCJleHAiOjE2ODM5ODI0MzYsImlhdCI6MTY4Mzk3ODgzNiwianRpIjoiZjlmZDliMjYtN2UyOC00Y2QzLWIzY2MtOWM3MjdmNTdkNTliIiwidXNlcm5hbWUiOiJwcm9zZWx5dGUifQ.8gdTqi18le0h4GTAd_JnxTDybnDFQS03biRnMbRRpQQ",
  "issued_at": "2023-05-13T11:53:56.390+00:00",
  "expires_at": "2023-05-13T12:53:56.390+00:00"
}
```

## 3. Получение данных пользователя с использованием токена, полученного в предыдущем запросе

```bash
curl --location 'http://localhost:8083/api/v1/auth/info' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0Iiwicm9sZSI6IlVTRVIiLCJpc3MiOiJwcm9zZWx5dGUiLCJleHAiOjE2ODM5ODI0MzYsImlhdCI6MTY4Mzk3ODgzNiwianRpIjoiZjlmZDliMjYtN2UyOC00Y2QzLWIzY2MtOWM3MjdmNTdkNTliIiwidXNlcm5hbWUiOiJwcm9zZWx5dGUifQ.8gdTqi18le0h4GTAd_JnxTDybnDFQS03biRnMbRRpQQ'
```

Пример ответа
```json
{
  "id": 1,
  "username": "proselyte",
  "role": "USER",
  "first_name": "Eugene",
  "last_name": "Suleimanov",
  "enabled": true,
  "created_at": "2023-05-13T14:02:37.248466",
  "updated_at": "2023-05-13T14:02:37.248482"
}
```

Удачи и приятной работы!!!
