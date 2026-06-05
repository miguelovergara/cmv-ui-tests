# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Run all tests locally
mvn clean test

# Run tests with explicit browser
mvn clean test -Dbrowser=chrome

# Run tests in headless mode (without CI env var)
mvn clean test -Dbrowser=chrome -Dheadless=true

# View Allure report after a test run
allure serve allure-results
```

## Architecture

Selenium/TestNG UI test suite for `https://cmvlosrobles.cl`. Java 11, Maven build, Allure reporting.

**Package layout:**
- `cl.cmvlosrobles.qa.pages` — Page Objects (src/main/java)
- `cl.cmvlosrobles.qa.base` — BaseTest + TestListener (src/test/java)
- `cl.cmvlosrobles.qa.tests` — Test classes (src/test/java)

**Key patterns:**

- **Page Object Model** — page classes live in `src/main/java`, use `@FindBy` + `PageFactory.initElements()`, expose clean methods to tests.
- **BaseTest** — `@BeforeMethod` spins up Chrome via WebDriverManager (no manual driver setup), navigates to base URL, sets 10s implicit wait. `@AfterMethod` tears down. Detects CI automatically via `GITHUB_ACTIONS` env var or `-Dheadless=true` and applies appropriate `ChromeOptions` (headless + sandbox flags for CI, maximized for local).
- **TestListener** — `ITestListener` that captures a screenshot on failure and attaches it to Allure via `@Attachment`.
- **testng.xml** — Suite entry point; registers both `AllureTestNg` and `TestListener` as listeners. Parallel execution is commented out (can enable `parallel="methods" thread-count="2"`).

## CI/CD

GitHub Actions workflows in `.github/workflows/`:

| File | Trigger | Purpose |
|------|---------|---------|
| `cron_smoke.yml` | Every Monday at 12:34 UTC | Scheduled full suite run |
| `manual_run.yml` | `workflow_dispatch` (Actions tab → "Run workflow") | On-demand full suite run |

All workflows upload `target/surefire-reports/` as artifacts. No extra flags needed — BaseTest auto-applies headless/sandbox options when it detects the `GITHUB_ACTIONS` environment variable.
