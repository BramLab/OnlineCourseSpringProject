# IntelliJ HTTP Client - Automated Testing Guide

## Overview

IntelliJ IDEA's HTTP Client supports automated testing using JavaScript response handlers. This allows you to write comprehensive integration tests directly in `.http` files.

## Features

### 1. **Response Handlers**
```javascript
> {%
    client.test("Test name", function() {
        client.assert(condition, "Error message");
    });
%}
```

### 2. **Available Test Methods**

- `client.test(name, callback)` - Define a test
- `client.assert(condition, message)` - Assert a condition
- `client.log(message)` - Log to console
- `client.global.set(key, value)` - Save global variable
- `client.global.get(key)` - Retrieve global variable
- `client.global.clear(key)` - Clear global variable

### 3. **Response Object Properties**

- `response.status` - HTTP status code (200, 404, etc.)
- `response.body` - Parsed JSON response body
- `response.headers` - Response headers
- `response.contentType` - Content type

## Running Tests

### Method 1: Run Individual Tests
1. Click the green arrow next to any request
2. View results in the "Run" tool window
3. Pass/fail status shown for each test

### Method 2: Run All Tests in File
1. Right-click on the `.http` file
2. Select "Run All Requests in File"
3. Tests run sequentially
4. View comprehensive results

### Method 3: Command Line (CI/CD)
```bash
# Using IntelliJ command line runner
idea httpClient -e dev automated-tests.http
```

## Test Results

### Success Output
```
✓ Test Suite Started
✓ Test user created: automated_test_user_1234
✓ Duplicate registration correctly rejected
✓ Admin login successful, token captured
✓ Invalid login correctly rejected
...
✓ Test Suite Completed
```

### Failure Output
```
✗ Login: Status is 200
  Expected status 200 but got 401
✗ Login: Response has token
  Token should be present
```

## Test Suite Structure

### 1. Setup Phase
```http
### SETUP: Clear any existing tokens
< {%
    client.global.clear("auth_token");
    client.log("=== Test Suite Started ===");
%}
```

### 2. Authentication Tests
- User registration (success/failure)
- Login (valid/invalid credentials)
- Token capture and validation

### 3. Public Endpoint Tests
- Get all courses (no auth)
- Get course by ID
- Error handling (404, etc.)

### 4. Authenticated Endpoint Tests
- Create/update/delete with different roles
- Authorization checks (ADMIN, INSTRUCTOR, STUDENT)
- Permission validation

### 5. Cleanup Phase
- Delete test data
- Clear global variables

## Best Practices

### 1. **Use Unique Test Data**
```http
{
  "userName": "test_user_{{$random.integer(1000, 9999)}}",
  "email": "test_{{$random.integer(1000, 9999)}}@example.com"
}
```

### 2. **Chain Tests with Variables**
```javascript
> {%
    // Save ID from creation
    client.global.set("test_course_id", response.body.id);
%}
```
```http
### Use saved ID in next test
DELETE http://localhost:8080/api/courses/{{test_course_id}}
```

### 3. **Comprehensive Assertions**
```javascript
client.test("Response structure", function() {
    client.assert(response.body.id !== undefined, "Should have ID");
    client.assert(response.body.title !== undefined, "Should have title");
    client.assert(response.body.password === undefined, "Should not expose password");
});
```

### 4. **Test Both Success and Failure**
```javascript
// Success case
client.test("Valid request: Status is 200", function() {
    client.assert(response.status === 200, "Expected 200");
});

// Failure case
client.test("Invalid request: Status is 400", function() {
    client.assert(response.status === 400, "Expected 400");
});
```

### 5. **Cleanup After Tests**
```http
### Cleanup: Delete test data
DELETE http://localhost:8080/api/admin/users/{{test_user_id}}
Authorization: Bearer {{auth_token}}

> {%
    client.log("✓ Cleanup completed");
%}
```

## Advanced Features

### 1. **Dynamic Variables**
```http
# Random integers
{{$random.integer(min, max)}}

# Random UUID
{{$random.uuid}}

# Random email
{{$random.email}}

# Timestamp
{{$timestamp}}

# ISO 8601 datetime
{{$isoTimestamp}}
```

### 2. **Environment Variables**
Create `http-client.env.json`:
```json
{
  "dev": {
    "baseUrl": "http://localhost:8080",
    "adminUsername": "user_admin1",
    "adminPassword": "a1"
  },
  "prod": {
    "baseUrl": "https://api.production.com"
  }
}
```

Use in requests:
```http
POST {{baseUrl}}/api/auth/login
{
  "username": "{{adminUsername}}",
  "password": "{{adminPassword}}"
}
```

### 3. **Conditional Logic**
```javascript
> {%
    if (response.status === 200) {
        client.global.set("auth_token", response.body.token);
        client.log("✓ Token saved");
    } else {
        client.log("✗ Login failed");
    }
%}
```

### 4. **Loop Through Response Arrays**
```javascript
> {%
    client.test("All courses have required fields", function() {
        response.body.forEach(course => {
            client.assert(course.id !== undefined, "Course missing ID");
            client.assert(course.title !== undefined, "Course missing title");
        });
    });
%}
```

## Common Test Patterns

### 1. **Authentication Flow**
```http
### 1. Login
POST {{baseUrl}}/api/auth/login
> {% client.global.set("token", response.body.token); %}

### 2. Use Token
GET {{baseUrl}}/api/admin/users
Authorization: Bearer {{token}}
```

### 2. **CRUD Operations**
```http
### Create
POST {{baseUrl}}/api/courses
> {% client.global.set("courseId", response.body.id); %}

### Read
GET {{baseUrl}}/api/courses/{{courseId}}

### Update
PUT {{baseUrl}}/api/courses/{{courseId}}

### Delete
DELETE {{baseUrl}}/api/courses/{{courseId}}
```

### 3. **Permission Testing**
```http
### Login as Student
POST {{baseUrl}}/api/auth/login
{"username": "student", "password": "pass"}
> {% client.global.set("studentToken", response.body.token); %}

### Try Admin Operation (should fail)
GET {{baseUrl}}/api/admin/users
Authorization: Bearer {{studentToken}}
> {%
    client.test("Should deny access", function() {
        client.assert(response.status === 403, "Expected 403");
    });
%}
```

## Integration with CI/CD

### GitHub Actions Example
```yaml
name: API Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      
      - name: Start Application
        run: ./mvnw spring-boot:run &
        
      - name: Wait for API
        run: sleep 30
        
      - name: Run HTTP Tests
        run: idea httpClient -e dev automated-tests.http
        
      - name: Upload Results
        uses: actions/upload-artifact@v2
        with:
          name: test-results
          path: test-results/
```

## Debugging Tests

### 1. **Enable Verbose Logging**
```javascript
> {%
    client.log("Request URL: " + request.url);
    client.log("Response Status: " + response.status);
    client.log("Response Body: " + JSON.stringify(response.body, null, 2));
%}
```

### 2. **Inspect Variables**
```javascript
> {%
    client.log("Current token: " + client.global.get("auth_token"));
    client.log("Test user ID: " + client.global.get("test_user_id"));
%}
```

### 3. **Use Try-Catch**
```javascript
> {%
    try {
        client.assert(response.body.token !== undefined, "Token missing");
        client.global.set("auth_token", response.body.token);
    } catch (e) {
        client.log("Error: " + e.message);
    }
%}
```

## Tips for Success

1. **Run tests in order** - Some tests depend on previous ones
2. **Use unique data** - Avoid conflicts with existing data
3. **Clean up after tests** - Delete test data to keep DB clean
4. **Test edge cases** - Invalid inputs, missing fields, etc.
5. **Check permissions** - Test with different user roles
6. **Verify error messages** - Don't just check status codes
7. **Use meaningful test names** - Makes debugging easier

## Limitations

- Cannot run tests in parallel
- Limited to sequential execution
- No test framework features (beforeEach, afterEach)
- JavaScript execution is sandboxed
- Cannot import external libraries

## Alternatives

For more complex testing needs, consider:
- **JUnit + REST Assured** - Full Java test framework
- **Postman/Newman** - CI/CD integration
- **Spring Boot Test** - Integration tests with @SpringBootTest
- **Cucumber** - BDD-style testing

## Summary

IntelliJ HTTP Client testing is perfect for:
- ✅ Quick integration tests
- ✅ Manual testing workflows
- ✅ Documentation with examples
- ✅ Simple CI/CD pipelines

Not ideal for:
- ❌ Complex test suites with many dependencies
- ❌ Parallel test execution
- ❌ Advanced mocking/stubbing
- ❌ Performance testing