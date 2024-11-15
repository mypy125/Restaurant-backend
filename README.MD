# Restaurant Project

## Project Description

**Restaurant.am** is a web application for ordering food. Users can browse the menu, select dishes, place orders, and pay online. Administrators can manage the menu, add new dishes, manage orders, and handle user accounts.

**Location:** Yerevan  
**Date:** 04/2024

**Developer:**  
Web Development in Java  
**Name:** Gagikovich Gor Mkhitaryan

**Project URL:** [https://github.com/mypy125/Restaurant.am-backend](https://github.com/mypy125/Restaurant.am-backend)

## Inspiration

I researched and analyzed many trending products relevant to our market. I decided to create an application that helps people order food without leaving their homes. The name "Restaurant" was inspired by Craig Walls' book "Spring in Action," where the examples involved creating a taco-cloud. Initially, the book helped me, but then I added more functionality, more services, and evolved it into what it is now, dividing it into microservices.

## Technology Stack

- **Spring Web** for web component development
- **PostgreSQL** for the database
- **Spring Data JPA** for database interaction
- **Hibernate** as ORM (Object-Relational Mapping)
- **Spring Security** for ensuring security
- **JWT Library** for token encryption
- **strip Library** for payment system

## Application Benefits

- Convenient and easy online food ordering
- Wide selection of restaurants and dishes
- Secure transactions and user data processing
- Flexible microservices architecture for scalability and development

## Functional Features

- Search for restaurants and dishes
- View menu and place orders
- Payment and order management
- User authentication and security
- Account and favorites management

## Entities Used

The application utilizes 13 entities:

- **User, Role, ContactInformation:** Related to authentication and user management.
- **Address, Category:** Address pertains to location information, and Category to food categories.
- **Food, IngredientCategory, IngredientItem:** Related to products and ingredients.
- **Cart, CartItem, Order, OrderItem:** Related to order processing and shopping cart.
- **Restaurant:** Related to restaurants.

In the future, each of these services will have its own database containing only the necessary tables for its operation. This will ensure a more modular and scalable architecture for my project.
