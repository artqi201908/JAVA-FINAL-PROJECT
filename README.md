# High-Level Design Document

## 1. Version History

- **Version 1.0**: Authored by Phuong Nguyen, Danni Hu, Wenqi Tang, and Feng Qi on March 11, 2024. This initial version outlines the foundational aspects of the Food Waste Reduction Platform.
- **Version 1.1**: Revised on March 15, 2024, incorporating enhancements based on initial reviews and further development insights.
- **Version 1.2**:  Completed on March 20, 2024, focusing on file organization and system optimization. 

## 2. Introduction

This document serves as a comprehensive high-level overview of the Food Waste Reduction Platform (FWRP), an innovative solution designed to address the critical issue of food waste. By leveraging technology, FWRP aims to facilitate the efficient redistribution of surplus food, connecting retailers with excess inventory to consumers and charitable organizations in need. The platform encompasses a suite of functionalities including inventory management for retailers, a matching algorithm to ensure timely distribution of food items, and a user-friendly interface for all stakeholders. Our project is spearheaded by a team consisting of Phuong Nguyen, Danni Hu, Wenqi Tang, and Feng Qi, each bringing specialized expertise to the project. Together, we are committed to delivering a useful and efficient platform that not only mitigates food waste but also fosters community engagement and support.

## 3. Targeted Audience

The intended recipients of this document include the project development team, stakeholders, charitable organizations, and system administrators. This comprehensive audience encompasses:

- **Project Development Team**: This team, made up of software developers, database designers, UI/UX designers, and testers, plays an important role in the creation and refinement of the FWRP.
- **Stakeholders**: This includes the sponsors funding the program and the food retailers participating in the platform, who are the main users of the system.
- **Charitable Organizations**: These non-profit entities engaged in the redistribution of surplus food use the platform to improve operational efficiency and are the main users of the system.
- **System Administrator**: Responsible for operating system and security management, ensuring daily operation of the platform, authority management, and technical support.

## 4. Scope

### In Scope:

- Application architecture and its components, detailing the structural design of the FWRP.
- Business processes and use cases, illustrating the operational flow and interactions within the platform.
- Data models and database schema, outlining the organization and management of data.
- Security and deployment strategies, specifying the measures for protecting data and the methodology for deploying the platform.
- Testing methodologies, describing the approaches and practices for ensuring the functionality and reliability of the platform.

### Out of Scope:

- Detailed source code documentation, which falls beyond the purview of this high-level overview.
- Specifics of the user interface design, which are addressed in dedicated design documents.
- Detailed hardware requirements, which are contingent upon the deployment environment and scale of implementation.

## 5. Application Architecture

### 5.1 Overview of the System Architecture

The Food Waste Reduction Platform leverages a meticulously designed modular architecture, emphasizing the separation of concerns to bolster maintainability and scalability. This architecture is organized into distinct layers, each tasked with a specific facet of the application’s functionality, ensuring a robust and efficient system. These layers are:

- **Web Layer**: Serves as the primary interface for user requests, handling all HTTP interactions.
- **Business Layer**: This is the heart of the application, where core business logic resides. It processes inputs from users, enforces business rules, executes calculations, and makes critical decisions.
- **Data Access Layer (DAL)**: Facilitates all interactions with the database or any other form of persistence storage. It encompasses operations such as CRUD (Create, Read, Update, Delete) and manages database connections, abstracting the complexities of database operations from the rest of the application.
- **View Layer**: Responsible for data presentation. It generates dynamic content based on user permissions and displays corresponding information in an accessible format.
<img width="468" alt="diagram of system" src="https://github.com/artqi201908/JAVA-FINAL-PROJECT/assets/134665097/8aa809c3-092c-4390-ad63-64750a16c731">

### 5.2 Main Components

The platform is composed of several essential components that collectively aim to minimize food waste through effective user interaction, data analysis, and dissemination:

- **User Management Module**: Manages user-centric operations such as registration, authentication, and profile upkeep. Ensures secure access and personal information management.
- **Inventory Tracking Module**: Enables tracking of food inventory for entities like restaurants and supermarkets, crucial for identifying and mitigating potential sources of food waste.
- **Waste Analytics Module**: Utilizes inventory tracking data to identify waste patterns and trends, providing actionable insights for waste reduction.
- **Donation Coordination Module**: Facilitates connections between food donors and recipients, streamlining food donation processes with effective matching and logistical support.
- **Reporting and Message Boards Module**: Provides users and administrators with message boards and reports on waste metrics and reduction strategy effectiveness, aiding informed decision-making.
- **Notification System**: Engages users with alerts and reminders about food waste reduction opportunities and updates through email, SMS, and in-app notifications

## 6. Business Architecture

### 6.1 Use Case Diagrams

The Use Case Diagram for the Food Waste Reduction Platform illustrates the interactions between the system's users: retailers, consumers, charitable organizations, and the system itself.
![FWRP](https://github.com/artqi201908/JAVA-FINAL-PROJECT/assets/134665097/e0f0ad2d-2ca4-4269-9d12-9d75ca9557f1)

### 6.2 Description

Key Actors:

- **Retailers**: Businesses that sell food products.
- **Consumers**: Individuals looking to purchase or claim food items.
- **Charitable Organizations**: Non-profits seeking food donations for distribution.

Use Cases:

- **User Registration**: Actors (Retailers, Consumers, Charitable Organizations) register to access platform features.
- **Inventory Management**: Actor (Retailers) manage their inventory on the platform.
- **Surplus Food Listing**: Actor (Retailers) list surplus food items for donation or sale.
- **Claiming/Purchasing of Food Items**: Actors (Consumers, Charitable Organizations) claim or purchase listed food items.
- **Donation Coordination**: Actors (Retailers, Charitable Organizations) participate in the food donation process.
- **Waste Analytics Reporting**: Actors (Retailers, Charitable Organizations, Platform Administrators) access reports and analytics on food waste patterns.

## 7. Class Diagram

![class diagram](https://github.com/artqi201908/JAVA-FINAL-PROJECT/assets/134665097/d9aeee36-04a1-433f-8e00-dc40b013a325)


## 8. Database Structures

![database-structure](https://github.com/artqi201908/JAVA-FINAL-PROJECT/assets/134665097/b416546e-143d-4531-9699-154309f96afc)


## 9. Security Architecture

### 9.1 Password Security

- Utilize a basic hash function like SHA-256 for password storage to ensure that even if the data is accessed, the actual passwords are not exposed.

### 9.2 User Roles and Permissions

- Implement basic access level controls, where retailers, consumers, and charitable organizations have clearly defined permissions that restrict their actions within the system.

## 10. Deployment Architecture

### 10.1 Cloud Services

- Use a simple cloud hosting service like Heroku for deploying the application, allowing for straightforward setup and scalability options if required.

### 10.2 Single-instance Deployment

- Deploy the application on a single instance (one server) to simplify the deployment process, focusing on the monolithic architecture without immediate scaling concerns.

## 11. Testing Model

### 11.1 JUnit

#### Model Layer Tests

- `UserTest.java`: Tests for the User model validations and business logic.
- `FoodItemTest.java`: Tests for the FoodItem model validations and business logic.
- `InventoryTest.java`: Tests for the Inventory model validations and business logic.
- `TransactionTest.java`: Tests for the Transaction model validations and business logic.

#### Repository Layer Tests

- `UserRepositoryTest.java`: Tests for user-related data access operations.
- `FoodItemRepositoryTest.java`: Tests for food item-related data access operations.
- `InventoryRepositoryTest.java`: Tests for inventory-related data access operations.
- `TransactionRepositoryTest.java`: Tests for transaction-related data access operations.

#### Service Layer Tests

- `UserServiceTest.java`: Tests for business logic in user management.
- `FoodItemServiceTest.java`: Tests for business logic in managing food items.
- `InventoryServiceTest.java`: Tests for business logic in inventory management.
- `TransactionServiceTest.java`: Tests for business logic in handling transactions.

#### Controller Layer Tests

- `UserControllerTest.java`: Tests for user-related web endpoints.
- `FoodItemControllerTest.java`: Tests for food item-related web endpoints.
- `InventoryControllerTest.java`: Tests for inventory-related web endpoints.
- `TransactionControllerTest.java`: Tests for transaction-related web endpoints.

#### Security and Utils Tests

- `SecurityServiceTest.java`: Tests for authentication and authorization services.
- `JwtTokenUtilTest.java`: Tests for JWT token generation and validation.

#### Integration Tests

- `ApplicationIntegrationTest.java`: A broad test covering the interaction between multiple components, possibly simulating a typical user scenario.
