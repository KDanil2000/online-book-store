# <h1 align="center">📚Online Bookstore📚</h1>


`An application for ordering YOUR favourite books`

---
## 📝 Introduction
`This project is an online bookstore that allows the User to make purchases. The application is built in Java using such frameworks as Spring Boot and Hibernate. There are two roles: User and Admin. The User can: browse books, add books they like to a cart and order them. He will also be able to register and log in. Admin will be able to do all the same things as User, but also can change the status of the order, as well as update and delete resources.`

---
## 🧑‍💻 Technologies Used
`The following technologies are used to build the 📚 application:`
- ☕ **Java**
- 🌱 **Spring Boot**
- 🌱🛢️ **Spring Data JPA**
- 🌱🛡️ **Spring Security**
- 🗎  **Swagger**
- 🐬 **MySQL**
- 🌶️ **Lombok**
- ↔️ **MapStruct**
---
## 🛢️ Database structure:
###### <h4 align="center"> ![Снимок экрана 2023-12-06 в 14.10.46.png](..%2F..%2FDesktop%2F%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-12-06%20%D0%B2%2014.10.46.png)</h4>

---
## **📃 API Endpoints**

### 🚨 **Authentication Controller**

| **HTTP method** |     **Endpoint**     | **Role** | **Function**                          |
|:---------------:|:--------------------:|:--------:|:--------------------------------------|
|      POST       | `/api/auth/register` |   ALL    | Register a new user                   |
|      POST       |  `/api/auth/login`   |   ALL    | Get JWT tokens for authentication     |

---

### 📖 **Book Controller**

| **HTTP method** |   **Endpoint**    | **Role** | **Function**               |
|:---------------:|:-----------------:|:--------:|:---------------------------|
|       GET       |   `/api/books`    |   USER   | Get a list of all books    |
|       GET       | `/api/books/{id}` |   USER   | Get information about book |
|      POST       |   `/api/books`    |  ADMIN   | Add new book               |
|       PUT       | `/api/books/{id}` |  ADMIN   | Update book                |
|     DELETE      | `/api/books/{id}` |  ADMIN   | Delete book                |

---

### 🔗 **Category Controller**

| **HTTP method** |         **Endpoint**         | **Role** | **Function**                   |
|:---------------:|:----------------------------:|:--------:|:-------------------------------|
|       GET       |      `/api/categories`       |   USER   | Get all books categories       |
|       GET       |    `/api/categories/{id}`    |   USER   | Get specific category by ID    |
|       GET       | `/api/categories/{id}/books` |   USER   | Get books by specific category |
|      POST       |      `/api/categories`       |  ADMIN   | Add new category               |
|       PUT       |    `/api/categories/{id}`    |  ADMIN   | Update category by ID          |
|     DELETE      |    `/api/categories/{id}`    |  ADMIN   | Delete category by ID          |


---
### 🛒 **Shopping cart Controller**

| **HTTP method** |        **Endpoint**         | **Role** | **Function**                        |
|:---------------:|:---------------------------:|:--------:|:------------------------------------|
|       GET       |         `/api/cart`         |   USER   | Get information about shopping cart |
|      POST       |         `/api/cart`         |   USER   | Add new cart item                   |
|       PUT       | `/api/cart/cart-items/{id}` |   USER   | Update quantity of cart item's      |
|     DELETE      | `/api/cart/cart-items/{id}` |   USER   | Delete cart item                    |

---
### 📦 **Order Controller**

| **HTTP method** |              **Endpoint**              | **Role** | **Function**                           |
|:---------------:|:--------------------------------------:|:--------:|:---------------------------------------|
|       GET       |             `/api/orders`              |   USER   | Get user orders history                |
|       GET       |        `/api/orders/{id}/items`        |   USER   | Get list of items for a specific order |
|       GET       | `/api/orders/{orderId}/items/{itemId}` |   USER   | Get specific item for a specific order |
|      POST       |             `/api/orders`              |   USER   | Create new order                       |
|      PATCH      |           `/api/orders/{id}`           |  ADMIN   | Update order's status by ID            |

---

## ❓ How to use

`Before running the Booking app, ensure you have the following installed:`

- ☕ Java Development Kit (JDK)

`Follow the steps below to install:`

- Clone the repository from GitHub and navigate to the project directory.
- Fill up application.properties and liquibase.properties files with the necessary environment variables.
- The application should now be running at http://localhost:8080.

## 🤝 Contribution Guidelines
`We welcome contributions to this project:`

For every new feature or bug fix, please establish a separate branch and initiate a pull request to the primary branch. Prior to merging, it is imperative that all pull requests undergo thorough review and receive approval from 