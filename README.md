# 🔧 REST API Automation Framework (Rest Assured + Java)

## 🔍 Project Overview

This repository contains a **REST API automation framework** built using **Rest Assured and Java**. It is designed to automate API testing with a scalable structure, robust validations, and detailed reporting.

---

## 🚀 Key Features

* 🔹 Built API automation framework using **Rest Assured**
* 🔄 Automated **CRUD (Create, Read, Update, Delete)** operations
* 📦 Implemented **POJO-based payloads** for request body handling
* 🔍 Performed request and response validation using **JSON Path**
* 📊 Integrated **TestNG** with **Allure Reports** and **Extent Reports**
* ⚙️ Managed build and dependencies using **Maven**
* 🗂️ Version controlled using **GitHub**

---

## 📁 Project Structure

```id="project-structure"
├── src/test/                # Test classes and API test cases
├── testdata/                # Test data files
├── allure-results/          # Allure raw results
├── reports/                 # Extent Reports output
├── logs/                    # Execution logs
├── test-output/             # TestNG default reports
├── target/                  # Maven build output
├── pom.xml                  # Maven configuration
├── testng.xml               # TestNG suite configuration
└── README.md                # Project documentation
```

---

## 🛠️ Tools & Technologies

* **Java** – Programming language
* **Rest Assured** – API automation library
* **TestNG** – Test execution framework
* **Maven** – Build and dependency management
* **Allure Reports** – Advanced reporting
* **Extent Reports** – Custom HTML reporting
* **JSON Path** – Response parsing and validation

---

## 🧪 Test Coverage

* ✅ CRUD operations validation
* ✅ Request payload validation using POJOs
* ✅ Response body validation using JSON Path
* ✅ Status code and header verification
* ✅ End-to-end API workflows

---

## ▶️ How to Run Tests

### Prerequisites

* Java (JDK 8 or higher)
* Maven installed

### Run Tests via Maven

```bash id="run-maven"
mvn clean test
```

---

## 📊 Reporting

### Allure Report

```bash id="allure-report"
allure serve allure-results
```

### Extent Report

* Generated inside the `reports/` folder after test execution

---

## 🔄 CI/CD & Version Control

* Integrated with **Maven** for build lifecycle
* Code managed and version controlled using **GitHub**
* Easily extendable for CI/CD integration (Jenkins/GitHub Actions)

---

## 🎯 Highlights

* Scalable and maintainable framework design
* Separation of test logic, data, and payloads
* Rich reporting for better test analysis
* Reusable components for faster test development

---

## 📌 Conclusion

This framework provides a complete solution for REST API automation using modern tools and best practices, ensuring reliability, maintainability, and clear reporting of test results.

---
