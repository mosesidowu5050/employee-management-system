# 🧩 Employee Management System (Microservices + Kafka)

## 📘 Overview
This project is a **microservices-based Employee Management System** built with **Spring Boot**, **Eureka Discovery Server**, and **Apache Kafka** to demonstrate **event-driven asynchronous communication** between services.  
It allows you to create departments and employees, and automatically notifies other services when a new employee is created.

---

## 🚀 Services Overview

| Service                    | Description                                               | Port |
|----------------------------|-----------------------------------------------------------|------|
| **discovery-server**       | Eureka registry for service discovery                     | 8761 |
| **authentication-service** | Handles operations for authentication                     | 8089 |
| **employee-service**       | Manages employees and publishes creation events to Kafka  | 8082 |
| **notification-service**   | Consumes Kafka events and logs asynchronous notifications | 8083 |
| **api-gateway**            | Routes requests to appropriate services                   | 8085 |
| **config-server**          | Config Server                                             | 8888 |

---

## ⚙️ Setup Instructions

### Prerequisites
Before running the project, ensure you have:
- **Java 17 or later**
- **Maven 3.9+**
- **Docker Desktop**
- **Postman** (for API testing)

---

### 1️⃣ Clone the Repository
```bash

git clone https://github.com/your-username/employee-management-system.git
cd employee-management-system

docker compose -f docker-compose.kafka.yml up -d

docker-compose.kafka.yml

services:
  zookeeper:
    image: wurstmeister/zookeeper:3.4.6
    container_name: zookeeper
    ports:
      - "2181:2181"

  kafka:
    image: wurstmeister/kafka:2.13-2.8.1
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: localhost
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181

Check if the containers are running using:
 - docker ps


# In discovery-server/
mvn spring-boot:run

# In authentication-service/
mvn spring-boot:run

# In employee-service/
mvn spring-boot:run

# In notification-service/
mvn spring-boot:run

# In api-gateway/
mvn spring-boot:run
```

```
POST http://localhost:8089/api/auth/register
Content-Type: application/json
{
  "name": "Admin User",
  "phoneNumber": "08122222222",
  "password": "admin123",
  "role": "ADMIN"
}


{
    "message": "User registered successfully",
    "phoneNumber": "08122222222",
    "role": "ADMIN",
    "userId": 4
}
```
