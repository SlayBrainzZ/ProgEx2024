# Programming Exercises - Frankfurt University of Applied Sciences
# Sales and Customer Management System

A desktop application developed for a small handmade jewelry business to streamline the organization and management of sales, customers, and product information. This system enhances operational efficiency and provides valuable insights through comprehensive reporting.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [System Architecture](#system-architecture)

## Project Overview

The Sales and Customer Management System is designed to manage customer, product, and sales data effectively. It simplifies access to this information and generates detailed reports on customer demographics, sales patterns, and order histories, helping businesses make data-driven decisions.

## Features

- **CRUD Operations**: Manage sales, customer, product, and user data with Create, Read, Update, and Delete operations.
  - Sales: Add, update, view, or delete sales records.
  - Customers: Manage customer information efficiently.
  - Products: Add, update, or delete product data.
  - Categories: Organize products into categories.
  - User Management: Admins can manage system users and roles.
- **Admin Interface**: Only administrators can manage users and perform sensitive operations.
- **Login and Authentication**: Secure access with user authentication.
- **Reports**:
  - City-wise Customer Report
  - Orders by Month Report
  - Monthly Sales with Customers Report

## Usage

- Login: Users must authenticate through the login page.
- Sales Management: Add, update, delete, or view sales records.
- Customer Management: Maintain customer records.
- Product Management: Add and categorize products.
- Reports: Generate detailed reports on sales, customers, and product categories.


## Technologies Used

- Java: High-level programming language used for core application logic.
- Java Swing: GUI toolkit for creating the user interface.
- MS-SQL: Backend database used for data storage.
- IntelliJ IDEA: IDE used for development.
- GitHub: For version control and collaboration.


## System Architecture

The system follows a layered architecture:

- User Interface Layer: Handles all user interactions.
- Security Layer: Manages authentication and authorization.
- Business Logic and Data Access Layer: Contains the core logic for performing CRUD operations and interacting with the database.
- Database Layer: MS-SQL database where data is stored.

