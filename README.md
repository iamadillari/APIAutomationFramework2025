# APIAutomationFramework2025

## 🚀 Getting Started

### Navigate to the project directory:
```bash
cd APIAutomationFramework2025
```

### Build the project using Maven:
```bash
mvn clean install
```

---

## 🧪 Usage

### Running Tests

1. Open the `testng-regression.xml` file located in:
   ```
   src/test/resources/testRunners
   ```
2. Execute the test suite using Maven:
   ```bash
   mvn test -DsuiteXmlFile=src/test/resources/testRunners/testng-regression.xml
   ```


## Overview

APIAutomationFramework2025 is a Java-based, Maven-managed automation framework designed for comprehensive REST API testing. It leverages TestNG for test orchestration and RestAssured for HTTP request handling, supporting robust, scalable, and maintainable API test automation. The framework is built to simplify API testing workflows, handle authentication, and validate API responses efficiently.

## Features

- **Modular Test Structure:** Organized test classes for GET, POST, PUT, and DELETE operations.
- **TestNG Integration:** XML-based test suite management and parallel execution.
- **RestAssured Support:** Fluent API for HTTP requests and response validation.
- **Authentication Handling:** Supports Bearer Token and other authentication types.
- **Reusable Utilities:** Common base classes and utilities for request/response handling.
- **Extensible Design:** Easily add new endpoints and test scenarios.
- **JSONPath Validation:** Utility for extracting and validating JSON data.
- **Deserialization Support:** Handles complex JSON structures for multiple users.



---

## ➕ Adding New Tests

- Create a new test class in the appropriate package under `src/test/java`.
- Extend the `BaseTest` class to inherit common setup and utilities.
- Define test methods using the `@Test` annotation and implement API calls using **RestAssured**.

---

## 🧩 Key Components

### 🔐 Authentication
- Supports **Bearer Token** authentication.
- Can be extended to include **Basic Auth**, **OAuth2**, and other types.

### 🛠️ Utilities
- **ConfigManager**: Centralized configuration management for API endpoints, credentials, and tokens.
- **JSONPathValidatorUtil**: Simplifies JSONPath-based data extraction and validation.

---

## 🧪 TestNG Integration

- Uses **TestNG** for test orchestration.
- Enables **parallel execution** and **XML-based test suite management**.

---

## 🚧 Future Enhancements

- **Deserialization**: Add support for handling complex JSON structures with arrays.
- **Enhanced Validation**: Improve JSONPath utility for more robust data validation.
- **TestNG Runner**: Implement advanced TestNG XML configurations for dynamic test execution.
- **Reporting**, **Jenkins Integration** **and few AI related Utilities**, 

---

## 🤝 Contributing

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Submit a pull request with a detailed description of your changes.


---

## 📬 Contact

For questions or support, contact **iamadillari**.
