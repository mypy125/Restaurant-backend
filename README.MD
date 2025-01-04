# Restaurant Project

## Project Description

**Restaurant.am** is a web application designed for seamless food ordering. It allows users to browse restaurant menus, place orders, and make secure online payments. Administrators can manage restaurant menus, track orders, and handle user accounts.

- **Location:** Yerevan
- **Launch Date:** 04/2024
- **Developer:** Gagikovich Gor Mkhitaryan
- **Project URL:** [Restaurant.am Backend Repository](https://github.com/mypy125/Restaurant.am-backend)

---

## Inspiration

This project was inspired by Craig Walls' *Spring in Action*, where an example was created around taco-cloud applications. I wanted to build something that serves real-world needs—helping people conveniently order food from home. The initial concept started simple but evolved into a scalable and modular microservices architecture.

---

## Technology Stack

### Frontend

- 🚀 **React.js** - A JavaScript library for building user interfaces
- 💻 **HTML/CSS** - Markup and styling
- 📱 **Responsive Design** - Ensuring the app works well across all device sizes

### Backend

- 🔧 **Java 17** - Programming language
- 🌐 **Spring Boot** - Framework for building microservices and APIs
- 🖥️ **Spring Web** - For the web layer development
- 📊 **Spring Data JPA** - For database interactions
- 🛡️ **Spring Security** - For securing user authentication and authorization
- 🔑 **JWT (JSON Web Token)** - For secure token-based authentication

### Database

- 🗃️ **PostgreSQL** - Relational database management system
- 🔗 **Hibernate ORM** - For object-relational mapping between Java objects and database tables

### Payment Integration

- 💳 **Stripe API, Idram, EasyPay** - For handling online payments

---

## Application Benefits

- **Convenient Ordering**: Allows users to easily order food from a wide variety of restaurants.
- **Secure Payments**: Integration with Stripe ensures secure online transactions.
- **Scalable Architecture**: Built using microservices to ensure easy scaling and maintainability.
- **Flexible**: Supports multiple restaurants, a variety of dishes, and customizations.

---

## Functional Features

### User Features:

- 🔍 **Search for Restaurants & Dishes**: Find and browse restaurants and their menus.
- 🍽️ **Order Management**: Add items to the cart, place an order, and manage orders in real time.
- 💳 **Secure Payments**: Integration with Stripe to securely handle payments.
- 🔐 **Authentication**: Secure user login using JWT tokens.
- ⭐ **Favorites**: Save favorite restaurants and dishes for easy access later.

### Admin Features:

- 🧑‍🍳 **Menu Management**: Add, edit, or remove dishes from the restaurant’s menu.
- 📊 **Order Tracking**: Monitor and manage customer orders in the admin dashboard.
- 👤 **User Management**: Handle user accounts and roles.

---

## Microservices & Entity Relationships

The application is designed with a **microservices architecture** to enhance scalability. Each service has its own database to ensure modularity and efficiency. Below are the core entities used:

### Entities:
1. **User**: Stores user information and roles.
2. **Role**: Defines different user roles (e.g., customer, admin).
3. **ContactInformation**: Stores user contact details.
4. **Address**: Stores delivery address information.
5. **Category**: Represents food categories (e.g., pizza, sushi).
6. **Food**: Represents a menu item.
7. **IngredientCategory & IngredientItem**: Define ingredients and ingredient categories.
8. **Cart**: Represents a user's current order.
9. **CartItem**: Represents items within a cart.
10. **Order**: Represents a finalized customer order.
11. **OrderItem**: Represents individual items in an order.
12. **Restaurant**: Stores restaurant details.

---

### Future Plans:
- **Decentralized Databases**: Each microservice will have its own database containing only the necessary tables. This will ensure a more scalable and efficient architecture.

---

## Architecture Overview

The system follows a **microservices architecture**:

- **Services** communicate via **REST APIs**.
- **Spring Security** is used to ensure secure access control.
- **JWT** ensures stateless and scalable user authentication.
- **PostgreSQL** serves as the relational database, with **Spring Data JPA** and **Hibernate ORM** facilitating easy data management.

### Architecture Diagram:

