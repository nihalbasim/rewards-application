# Rewards Application - Dockerized Setup

## Project Setup

This project is a Spring Boot application that processes receipts and awards points based on the rules specified. The application is containerized using Docker for easy deployment and testing.

### **Prerequisites**
Before running the application, ensure the following:

- **Docker**: Make sure Docker is installed and running on your machine.

---

##  Steps to Run the Application

### **Step 1: Clone the repository**

Clone the repository to your local machine:

```bash
git clone https://github.com/nihalbasim/rewards-application.git
cd rewards-application
```

### Step 2: Build the Docker Image
Run the following command in the project root directory to build the Docker image:
```bash
docker build -t receipt-processor .
```

### Step 3: Run the Docker Container
Start the container by running the following command:
```bash
docker run -p 8080:8080 receipt-processor
```

### Step 4: Verify the Application Health
To ensure the application is up and running, hit the following health endpoint:
```bash
curl http://localhost:8080/receipts/health
```

### Step 5: Process a Receipt to Generate ID(POST Request)
To generate a receipt ID, send a POST request with a valid receipt JSON body. Here's an example using curl:
```bash
curl -X POST http://localhost:8080/receipts/process \
-H "Content-Type: application/json" \
-d '{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {"shortDescription": "Mountain Dew 12PK", "price": "6.49"},
    {"shortDescription": "Emils Cheese Pizza", "price": "12.25"},
    {"shortDescription": "Knorr Creamy Chicken", "price": "1.26"},
    {"shortDescription": "Doritos Nacho Cheese", "price": "3.35"},
    {"shortDescription": "Klarbrunn 12-PK 12 FL OZ", "price": "12.00"}
  ],
  "total": "35.35"
}'
```
You should receive a response with a unique receipt ID:
```
{
  "id": "7fb1377b-b223-49d9-a31a-5a02701dd310"
}
```
### Step 6: Get Points for the Processed Receipt(GET Request)
Using the generated receipt ID, hit the endpoint to get the calculated points:
```
curl http://localhost:8080/receipts/7fb1377b-b223-49d9-a31a-5a02701dd310/points
```
The response will contain the calculated points for the receipt:
```
{
  "points": 28
}
```

---
## How it works
Endpoint 1: /receipts/process (POST):
This endpoint accepts a receipt JSON, processes it, and returns a unique receipt ID.

Endpoint 2: /receipts/{id}/points (GET):
This endpoint takes the receipt ID and returns the number of points awarded based on the rules.


