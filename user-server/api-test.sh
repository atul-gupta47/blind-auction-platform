#!/bin/bash

BASE_URL="http://localhost:8080"
#USER_ID=""  # Replace with an actual user ID
TOKEN=""    # Replace with an actual token

# User API Endpoints
echo "Get All Users:"
USERS_RESPONSE=$(curl -s -X GET "${BASE_URL}/users")
USER_ID=$(echo "${USERS_RESPONSE}" | jq -r '.[0].id')

echo -e "\nGet User by ID:"
curl -X GET "${BASE_URL}/users/${USER_ID}"

echo -e "\nCreate User:"
curl -X POST -H "Content-Type: application/json" -d '{"username": "newuser"}' "${BASE_URL}/users"

#echo -e "\nUpdate User:"
#curl -X PUT -H "Content-Type: application/json" -d '{"username": "updateduser"}' "${BASE_URL}/users/${USER_ID}"

#echo -e "\nDelete User:"
#curl -X DELETE "${BASE_URL}/users/${USER_ID}"

echo "${USER_ID}"
# Token API Endpoints
echo -e "\nGenerate Token:"
TOKEN=$(curl -s -X GET "${BASE_URL}/users/generate-token/${USER_ID}")

#
#echo "${TOKEN}"
#echo -e "\nValidate Token:"
#curl -X POST -H "Content-Type: application/json" -d '{"userId": "'${USER_ID}'", "token": "'${TOKEN}'"}' "${BASE_URL}/users/validate-token"
