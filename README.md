# Project Name

## Introduction

* Blind Auction Platform

## How to Run the Servers

Follow the steps below to run both servers:

### Prerequisites

Ensure you have the following prerequisites installed:

- Git
- Java 8 or above
- Gradle

### Steps

1. Clone the project repository to your local machine using git command: `git clone <repository_url>`.
2. Navigate to the root directory of the cloned repository. You should see 'user-server' and 'auction-server'
   directories.
3. We have a Shell Script to start the servers automatically. Run the shell script as follows:

ShellScript ./start.sh

This shell script does the following:

- Kills any processes currently running on ports 9002 and 9007.
- Navigates to `user-server` and runs the Spring Boot server on port 9002.
- Navigates to `auction-server` and runs the Spring Boot server on port 9007.

4. To run the integration tests, execute the `integration-test.sh` shell script using the following command:

### Details

The 'user-server' and 'auction-server' directories contain the server code for the user and auction functionalities
respectively.

- `user-server` runs at `http://localhost:9002/`
- `auction-server` runs at `http://localhost:9007/`
