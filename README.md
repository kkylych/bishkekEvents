OnlineStore
OnlineStore is a Java Spring Boot project that implements an online store application for managing orders, users, and products. This README provides an overview of the project structure and usage instructions.

Project Structure
The project is structured as follows:

entities: Contains the entity classes representing the database tables.

Order.java: Entity class representing an booking.
Product.java: Entity class representing a product.
Customer.java: Entity class representing a user.
dtos: Contains Data Transfer Object (DTO) classes.

Includes request and response DTOs used for communication between the client and server.
mappers: Contains classes responsible for mapping between entities and DTOs.

OrderMapper.java: Mapper interface for mapping booking entities to DTOs and vice versa.
repositories: Contains repository interfaces for interacting with the database.

Repository interfaces for entities, such as OrderRepository, ProductRepository, and CustomerRepository.
controllers: Contains REST controllers for handling HTTP requests.

Controllers for orders, products, and users.
services: Contains service classes for business logic.

OrderService.java: Service interface for managing orders.
Implementation of OrderService, ProductService, and CustomerService.
tests: Contains unit tests for testing various components of the application.

Test classes for entities, controllers, services, and mappers.
Usage
To use the OnlineStore project, follow these steps:

Clone the repository to your local machine:

sh
Copy code
git clone https://github.com/your-username/onlinestore.git
Set up your database configuration in application.properties.

Build the project using Maven:

sh
Copy code
mvn clean package
Run the application:

sh
Copy code
java -jar target/onlinestore.jar
Access the application through the provided endpoints.

Dependencies
The project uses the following dependencies:

Spring Boot
Spring Data JPA
Lombok
Ensure that these dependencies are installed and configured properly in your environment.

Contributing
Contributions to the OnlineStore project are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request on GitHub.

