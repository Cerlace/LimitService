# Микросервис управления лимитами и транзакциями

Микросервис для обработки финансовых транзакций с контролем лимитов расходов и интеграцией с внешним API курсов валют.

## Структура проекта
Мультимодульный Maven проект:
- **limit-service-parent** - родительский модуль
  - **limit-service-persistence** - модуль работы с БД и JPA
  - **limit-service-core** - основная бизнес-логика и интеграции
  - **limit-service-web** - веб-слой и REST API

## Основные возможности
- Установка лимитов расходов по категориям
- Сохранение входящих транзакций с конвертацией валют и проверкой превышения лимитов
- Получение транзакций, превысивших лимит
- Интеграция с Twelve Data API для получения курсов валют

## Технологии
- Java 17
- Spring Boot 3.4.4
- PostgreSQL
- Liquibase
- OpenFeign
- MapStruct
- Lombok
- Swagger/OpenAPI 3
- Maven

## Требования
- Java 17 JDK
- PostgreSQL 14+
- Maven 3.8+
- API ключ Twelve Data

## Конфигурация
Основные параметры в `application.properties`:
```properties
spring.application.name=limit-service
exchange-rate-api.url=https://api.twelvedata.com
exchange-rate-api.key=${TWELVE_DATA_API_KEY}

spring.datasource.url=${LIMIT_SERVICE_DB_URL}
spring.datasource.username=${POSTGRES_USERNAME}
spring.datasource.password=${POSTGRES_PASSWORD}

spring.jpa.hibernate.ddl-auto=validate
spring.liquibase.enabled=true
```
## Запуск сервиса
1. Настройка окружения: cоздайте .env файл с переменными окружения, или добавьте их вручную:

```bash
# PostgreSQL
export LIMIT_SERVICE_DB_URL=jdbc:postgresql://localhost:5432/limit_service
export POSTGRES_USERNAME=limit_user
export POSTGRES_PASSWORD=limit_password

# Twelve Data API
export TWELVE_DATA_API_KEY=your_api_key_here
```
2.  Сборка проекта
```bash
mvn clean install -DskipTests
```
3. Запуск приложения
```bash
cd limit-service-web
mvn spring-boot:run
```
## Документация API
После запуска сервиса документация доступна по адресам:

Swagger UI: http://localhost:8080/swagger-ui.html

OpenAPI JSON: http://localhost:8080/v3/api-docs
