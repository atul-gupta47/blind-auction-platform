#!/bin/bash

# Function to kill a process running on a specific port
kill_process_on_port() {
    local port=$1
    local process_id=$(lsof -t -i :$port)

    if [ -n "$process_id" ]; then
        echo "Killing process on port $port (PID: $process_id)..."
        kill -9 $process_id
        sleep 2  # Wait for the process to terminate
    else
        echo "No process found on port $port."
    fi
}

# Function to start the Spring Boot servers
start_servers() {
    echo "Starting Spring Boot servers..."

    # Kill processes on specified ports before starting servers
    kill_process_on_port 9002
    kill_process_on_port 9007

    # Start your first Spring Boot server
    cd /user-server
    ./gradlew bootRun --args='--server.port=9002' &
    SERVER1_PID=$!

    # Start your second Spring Boot server
    cd /auction-server
    ./gradlew bootRun --args='--server.port=9007' &
    SERVER2_PID=$!

    sleep 15  # Adjust the sleep time based on your servers' startup time
}

# Function to stop the Spring Boot servers
stop_servers() {
    echo "Stopping Spring Boot servers..."

    # Stop the first Spring Boot server
    kill -9 $SERVER1_PID

    # Stop the second Spring Boot server
    kill -9 $SERVER2_PID
}

# Navigate to the project directory

# Start the servers
start_servers

