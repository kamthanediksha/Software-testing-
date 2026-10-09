# JavaScript and Test Automation Learning Repository

This repository contains hands-on notes and examples for learning prompt engineering, JavaScript fundamentals, and browser test automation. Most JavaScript examples are standalone files that can be run individually.

## Prerequisites

- [Node.js](https://nodejs.org/) to run the JavaScript examples.
- Java 17 and [Apache Maven](https://maven.apache.org/) to run the Selenium tests.
- Google Chrome for the Selenium browser tests.

## Contents

- `00_Chapter_Prompt_Eng/` — prompt engineering notes and templates, including RICE-POT, problem statements, and hallucination prevention.
- `00_Chapter_Prompt_Eng/Selenium Framework/` — a Maven-based Selenium and TestNG suite covering Salesforce login scenarios.
- `01_Chapter_JS_Basics/` — introductory JavaScript examples and DOM notes.
- `02_Chapter_JS_Keywords_Identifiers/` — JavaScript engine, keyword, comment, and identifier examples, plus [Keywords&Identifirs.md](02_Chapter_JS_Keywords_Identifiers/Keywords%26Identifirs.md) with identifier rules and naming conventions.
- `03_Chapter_JS_Literals/` — examples of JavaScript string, boolean, null, undefined, and numeric literals, including number bases, exponential notation, numeric separators, BigInt, `Infinity`, and `NaN`.
- `04_Chapter_JS_Operators/` — arithmetic, assignment, comparison, and logical operator examples, including loose-versus-strict equality and surprising type coercion.

The keywords and identifiers lessons cover reserved words, case sensitivity, valid identifier starting characters (`a-z`, `A-Z`, `_`, and `$`), and common naming styles such as camelCase and PascalCase.

The literals lessons demonstrate JavaScript literal syntax and use `typeof` to inspect the resulting value types.

## Run a JavaScript example

From the repository root, run an individual file with Node.js:

```sh
node 01_Chapter_JS_Basics/01_HelloWorld.js
```

For example, the identifier exercises can be run with:

```sh
node 02_Chapter_JS_Keywords_Identifiers/09_IQ.js
```

Run a literals example with:

```sh
node 03_Chapter_JS_Literals/10_Literals.js
```

Run an operators example with:

```sh
node 04_Chapter_JS_Operators/24_Confusing_Comparsion.js
```

The operators examples demonstrate arithmetic results, assignment, boolean comparisons, logical operators, and why loose equality (`==`) can produce surprising results compared with strict equality (`===`).

## Run the Selenium tests

Run the TestNG suite from the repository root:

```sh
mvn -f "00_Chapter_Prompt_Eng/Selenium Framework/pom.xml" test
```

The suite uses headless Chrome by default. To run Chrome visibly, pass `-Dheadless=false`:

```sh
mvn -f "00_Chapter_Prompt_Eng/Selenium Framework/pom.xml" test -Dheadless=false
```

The valid-login test runs only when `SALESFORCE_USERNAME` and `SALESFORCE_PASSWORD` are set in the environment; otherwise, TestNG skips that test. The invalid-login and remember-me scenarios exercise the live Salesforce login page, so they require network access and may be affected by changes to that external site.
