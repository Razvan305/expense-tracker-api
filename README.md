# Expense Tracker REST API

A modular, production-ready RESTful backend service built with Java 17 and Spring Boot 3 to manage and analyze personal expenses. The application implements clean architecture patterns using dedicated Request/Response DTOs, compile-time MapStruct mapping, and database-level aggregate queries in MySQL.

---

## Features

- **Expense Management:** Full CRUD operations (create, read, update, delete).
- **Safe Partial Updates:** Partial entity patching using MapStruct's null-safe strategy (`NullValuePropertyMappingStrategy.IGNORE`), preventing unintended field nullification and identifier mutation.
- **Architectural Decoupling:** Complete separation of persistence models from API transport contracts using dedicated DTOs.
- **Financial Precision:** Currency fields handled strictly via `BigDecimal` (`DECIMAL(12, 2)`) to eliminate floating-point rounding errors.
- **Server-Side Aggregations:** Fast summation and monthly expense calculations executed directly in MySQL via optimized JPQL queries.
- **Standardized HTTP Semantics:** Strict adherence to REST principles using Spring's `ResponseEntity` (`201 Created`, `200 OK`, `204 No Content`).

---

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3 (Spring Data JPA, Spring Web)
- **Database:** MySQL 8
- **Object Mapping:** MapStruct 1.6
- **Boilerplate Reduction:** Project Lombok
- **Build Tool:** Maven

---

## Architecture Overview

```text
src/main/java/com/razvan/expensetracker/
├── controllers/       # REST endpoints handling HTTP requests & responses
├── dtos/              # Decoupled Request and Response data transfer objects
├── entities/          # Relational JPA entities mapped to MySQL tables
├── mappers/           # Compile-time MapStruct object mappers
├── repositories/      # Spring Data JPA repositories with JPQL aggregations
├── services/          # Business logic and transaction orchestration
└── ExpenseTrackerApplication.java