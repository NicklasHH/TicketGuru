# Test Documentation for TicketGuru

## 1. Introduction
This document provides an overview of the testing processes and results for ticketgurus ticket sale system.

## 2. Test Environment
- **Technologies and Tools**: Spring Boot, JUnit
- **Configuration**:
  - Spring Boot version: (3.0.1)
  - Database configuration:
```spring.datasource.url=jdbc:mariadb://localhost:3306/ticketguru
spring.datasource.username=root
spring.datasource.password=root```

  - Security settings: Use MockMvc for API test
    
## 3. Testing Strategy
### Unit Tests
- Tests individual methods and class functionalities using JUnit.
- Example: Testing CRUD operations for all Roles class.
### Integration Tests
- Tests API calls and the interaction between the database and the reservation system.
- Example: Testing ticket retrieval by ID and authentication and authorization functionalities.

### End-to-End Tests (E2E)
- Simulates scenarios performed by the end-user, testing complete user journeys.
- Example: Testing the ticket checking process, including error handling and successful ticket verification.
  
## 4. Test Case Details
- **Test Case: Successful Ticket Purchase Path**
  - **Steps**: 
    1. User press "Lipun osto" button.
    2. User selects an event and chooses a ticket type.
    3. User completes payment.
    4. System generates a ticket.
  - **Expected Result**: Ticket is successfully purchased and delivered to the user.

## 5. Sample Test Code
```
    @Test
    public void testFindKorvatunturi() throws Exception {
        mockMvc.perform(get("/api/postalcodes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.postalcode == '99999')].postOffice").value("Korvatunturi"));
    }
```

## 6. Test Results and Analysis
- **Results**: The tests successfully demonstrated that the postal code API correctly returns "Korvatunturi" for postal code 99999, and the ticket purchasing process works as expected.
- **Analysis**: If all tested passes that means Postalcode api is working correctly and user can buy a ticket
  
## 7. Conclusions and Next Steps
- **Conclusions**: The current testing phase has successfully verified the essential functionalities of the TicketGuru system, ensuring that key features like role management, ticket purhace works as expected
- **Recommendations**: Test all API endpoints and CRUD classes.
  
## 8. Appendices and References
**GitHub Repository and source code**: 
https://github.com/NicklasHH/TicketGuru
---
