# MyApplications

## Description
This project is a Spring Boot application that calculates charges for vehicles based on their checkpoints. It includes a singleton implementation and uses Maven for dependency management.

## Requirements
- Java 17
- Maven 3.6+
- Spring Boot 3.4.2

## Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/Ayush087/MyApplications.git
    cd MyApplications
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

## Usage
1. Run the application:
    ```sh
    mvn spring-boot:run
    ```

2. The application will start, and you can see the output in the console.

## Classes
- `VehicleApplication`: Main class that calculates charges for vehicles.
- `VehicleDetails`: Class representing vehicle details including vehicle number, checkpoint ID, and timestamp.
- `SingletonUse`: Demonstrates the usage of a singleton pattern.
- `SingletonLazy`: Singleton class with lazy initialization.
- `MsApplication`: Main class that finds the second-highest salary among employees.

## License
This project is licensed under the MIT License.