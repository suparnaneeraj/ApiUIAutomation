# TRELLO TESTS

This is a simple Maven project to automate the basic features of Trello task management through both UI and API.The suite includes testing the basic functionalities like Creating board,creating a list in the board,creating cards,editing a card and moving the cards.
This project is designed to follow a Behaviour Driven Development Approach (BDD) using Cucumber , the browser interactions are simulated using Selenium WebDriver ,the scripting is done using Java programming language and the testing framework used is JUnit.

## Project folder and structure

- The test cases or features are stored in the features folder.
- The configs folder contains the properties file which includes few parameters to run the test.
- The test implementation(step definition file) and the runner class are defined in src/test/java/trello/boards
- All the locators and its operations are included in the respective classes defined in src/test/java/trello/pages
- The webdriver operations and configuration class are stored in src/test/java/utilities folder
- The API methods are specified in src/test/java/utilities folder
- Reporting is done using inbuilt Cucumber options .The html test report is saved in target/cucumber folder

## How to run the tests

### Pre-requisites

- Eclipse IDE or any suitable IDE with Cucumber plugin installed
- JDK 17
- Apache maven 3.8.1
- Ensure system variables JAVA_HOME and MAVEN_HOME are correctly configured.
- The credentials to login to Trello application , the api key as well as the token should be specified in the properties file `propertiesFile.properties` inside `configs` folder.
- The user should have a workspace created.

## Running the tests

- Clone and checkout the git repository.
- Import the project as `Maven` (Import->Existing Maven project) in Eclipse or any suitable IDE.
- Right click on the file `BoardFunctionalityTests.java` inside `test/java/trello/boards` and Choose Run as JUnit Test, if JUnit Test is already configured or else, choose `Run as -> RunConfiguration -> Select JUnit -> Under option Run all tests in selected folder` , choose the folder containing tests -> Apply -> Run .
- Each test is designed to run independently , therefore it is also possible to run any particular test using `tags`.

## Improvements

- Currently the test is verified only in the Chrome browser. The test is partially implemented to support Firefox and Edge browser but is not functional.
- `Thread.sleep()` is used in a couple of areas as explicit `wait` didn't work as expected. This can be replaced with much better options.
- Refactoring and Code scalability
- Reusability can be improved especially in the API automation methods.
- Currently priority has been given to testing the positive flow of the basic functionalities through UI and API. More tests including negative scenarios can be included.
- Currently when a board is clicked during a test run , intermittent errors like privacy error or connection timeout are observed in the browser. Connection error was also observed when the application is used in a normal browser. Therefore further investigation is required to handle this issue. But for the time being in order to continue the test run , `driver.navigate().refresh()` is used .

