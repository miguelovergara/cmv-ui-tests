#Automation Strategy for CMV Los Robles

##Project Structure

The suite is organized into a standard Maven project structure:

* `src/main/java`: Contains Page Objects and Base Setup.
* `src/test/java`: Contains Test Classes and Data Providers.
* `pom.xml`: Dependency management (Selenium, TestNG, WebDriverManager).

##Deployment Options

* Local: Run via `mvn test`.
* CI/CD (GitHub Actions/Jenkins): Integrated into a pipeline using the provided `headless` mode configuration.
* Docker: A `Dockerfile` can wrap the Maven image to ensure a consistent execution environment (Chrome/Firefox drivers included).

### Execution and Reporting Steps

1. **Clone/Set up:** Save the files into a standard Maven folder structure.
2. **Run Tests:** Use the terminal to execute `mvn clean test`
3.  **Generate Allure Report (Optional):**
If you have Allure installed, run:
allure serve allure-results
4.  **CI/CD Recommendation:**
Use a `Jenkinsfile` or `.github/workflows/main.yml` to trigger `mvn test` on every push to your repository. Since the site uses some dynamic loading, the `WebDriverWait` (implicit in the `BaseTest`) ensures stability against network latency.

##Reporting

* Allure Report: Highly recommended for experts. It provides screenshots on failure, step-by-step logs, and historical trends.
* TestNG HTML: Native, lightweight report generated in the 'target/surefire-reports' folder.

##Architectural Overview

* Language: Java (fits your expertise in Maven/Java/Groovy).
* Pattern: Page Object Model with Page Factory for clean element management.
* Reporting: Allure Reports (modern, interactive) or standard TestNG reports.
* Deployment: Containerized via Docker for CI/CD portability.

##Tech

Java, Selenium, Maven, and TestNG
