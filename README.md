Fitness Management System REST API для управления фитнес клубом. Позволяет вести учёт клиентов, тренеров, абониментов и занятий.

Стэк:

Java 17
Spring Boot 3.5
Spring Data JPA
PostgreSQL
Liquibase (миграции БД)
MapStruct (маппинг DTO)
Lombok
Docker / Docker Compose
Swagger (SpringDoc OpenAPI)
JUnit 5 / Mockito (тесты)

Схема базы данных Основные таблицы:
clients — клиенты фитнес клуба.
trainers — тренеры работающие в фитнес клубе.
memberships — абонименты.
training_Bookings — запись на тренировку.
training_Sessions — тренировка.
