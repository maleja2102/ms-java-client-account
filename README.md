# 🏦 Devsu Banking Platform - Java Microservices

This repository combines two independent Spring Boot services that collaborate to deliver the Devsu digital banking experience. Each service owns its persistence layer, exposes a REST API, and can evolve on its own release cadence.

- **ms-java-client** – customer and person management capabilities.
- **ms-java-account** – account lifecycle, transaction processing, and reporting features.

Both services adopt consistent engineering conventions so teams can move between codebases without friction.

---

## Repository Overview

```
ms-java-client-account/
├── ms-java-client/   # Client service source code
├── ms-java-account/  # Account, transaction, and report service
└── README.md         # Project documentation
```

---

## Technology Highlights

- Java 21 runtime
- Spring Boot 3.3.x with Spring Data JPA
- Maven build system
- MySQL 8 as the relational datastore
- Lombok for boilerplate reduction
- Spring Security with JWT authentication (account service)
- Spring Boot Actuator for operational insight

---

## Architectural Consistency

Both services follow a hexagonal layout that separates the domain from delivery and infrastructure layers:

- **Domain**
  - `model`: Core business entities and value objects.
  - `port.in`: Use cases exposed to the application layer.
  - `port.out`: Contracts that infrastructure adapters must implement.
- **Application**
  - `dto`: Request/response structures used by controllers.
  - `mapper`: Translators between persistence and domain types.
  - `service`: Business workflows orchestrating the domain logic.
- **Infrastructure**
  - `controller`: REST endpoints using Spring MVC.
  - `persistence`: JPA repositories and database adapters.
  - `configuration` & `handler`: Cross-cutting setup and exception handling.

This structure keeps business rules independent from frameworks while still enabling rapid delivery.

---

## Client Service (`ms-java-client`)

- **Default port:** `8081`
- **Base path:** `/clients`

| Method | Endpoint        | Description                               |
|--------|-----------------|-------------------------------------------|
| POST   | `/clients`      | Register a new client profile.            |
| GET    | `/clients`      | Retrieve the full list of clients.        |
| GET    | `/clients/{id}` | Fetch a client by numeric identifier.     |
| PUT    | `/clients/{id}` | Update details of an existing client.     |
| DELETE | `/clients/{id}` | Remove the specified client record.       |

---

## Account Service (`ms-java-account`)

- **Default port:** `8080`
- **Base paths:** `/api/accounts`, `/api/transactions`, `/api/reports`

### Account Endpoints

| Method | Endpoint             | Description                                    |
|--------|----------------------|------------------------------------------------|
| POST   | `/api/accounts`      | Create an account and return its representation. |
| GET    | `/api/accounts`      | List every registered account.                 |
| GET    | `/api/accounts/{id}` | Retrieve account details by identifier.        |
| PUT    | `/api/accounts/{id}` | Modify account attributes.                     |
| DELETE | `/api/accounts/{id}` | Delete the selected account (no body response).|

### Transaction Endpoints

| Method | Endpoint                                 | Description                                              |
|--------|------------------------------------------|----------------------------------------------------------|
| POST   | `/api/transactions/{accountId}`          | Record a deposit or withdrawal for the given account.    |
| GET    | `/api/transactions`                      | List all transactions across accounts.                   |
| GET    | `/api/transactions/account/{accountId}`  | Show the ledger for a specific account.                  |

### Reporting Endpoints

| Method | Endpoint                                                                  | Description                                      |
|--------|---------------------------------------------------------------------------|--------------------------------------------------|
| GET    | `/api/reports?clientId={id}&startDate={yyyy-MM-dd}&endDate={yyyy-MM-dd}`  | Produce a statement for the requested client and period. |

---

## Configuration Notes

Each service maintains its own `application.yml` with datasource credentials, port configuration, and security policies. Update these files to match your environment before launching the applications.

---

## Author

Maria Lavado
