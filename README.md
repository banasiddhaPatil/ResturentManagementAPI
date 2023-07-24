# RestaurantManagementAPI

- This project is a simple Food Ordering System that allows customers to sign up, sign in, view food items, schedule orders, and cancel orders. The system uses Spring Boot for the backend, with H2 database for data storage. The following sections provide an overview of the project structure, functionality, and usage.

## Project Structure

- The project is organized into several classes and packages to manage different components. Here is a brief overview of the main components:

1. **Entities**: Contains classes representing the data models (Admin, AuthenticationToken, Customer, FoodItem, Orders).

2. **DTO**: Contains Data Transfer Objects used for communication between frontend and backend (SignInInput, SignUpOutput).

3. **Controllers**: Each controller handles HTTP requests and manages API endpoints (AdminController, CustomerController, FoodItemController, OrderController).

4. **Repositories**: Contains interfaces that define data access methods (IAdminRepo, IAuthTokenRepo, ICustomerRepo, IFoodItemRepo, IOrderRepo).

5. **Services**: Contains business logic and acts as a bridge between the controllers and repositories (AdminService, AuthTokenService, CustomerService, FoodItemService, OrderService).

6. **Utilities**: Contains utility classes (EmailHandler, PasswordEncryptor) for sending emails and password encryption.

## Functionality

- **Sign Up**: Customers can sign up using their name, email, password, and address. The password is encrypted before storing it in the database.

- **Sign In**: Customers can sign in using their email and password. If the credentials match, an authentication token is generated and sent to the customer's email.

- **View Food Items**: Customers can view a list of available food items along with their titles, descriptions, and prices.

- **Schedule Order**: Authenticated customers can schedule an order by selecting a food item and specifying the quantity and desired schedule time.

- **Cancel Order**: Authenticated customers can cancel their scheduled orders.

- **View Orders**: Administrators and authenticated customers can view a list of all orders.

## Usage

- To run the Food Ordering System, follow these steps:

1. Clone the repository to your local machine.

2. Import the project into your preferred Java IDE (e.g., IntelliJ, Eclipse).

3. Build the project to resolve dependencies.

4. Run the application using Spring Boot.

5. The application will start, and you can access the endpoints using tools like Postman or through a frontend application.

- Note: The project uses an H2 in-memory database, so the data will be reset every time the application restarts.

## Dependencies

- The main dependencies for this project are:

- Spring Boot: For building the backend application.

- H2 Database: For in-memory data storage.

- Spring Data JPA: For data access and manipulation.

- Java Mail API: For sending emails.

- JSON: For handling JSON data.

- Spring Web: For creating RESTful APIs.

## Conclusion

- This Food Ordering System provides essential functionality for customers to sign up, sign in, view food items, schedule orders, and cancel orders. You can extend the project by adding more features like payment integration, order status updates, or different user roles. Feel free to explore and enhance the system to meet your specific requirements. Happy coding!

## Contributing
- If you'd like to contribute to this project, please fork the repository, make your changes, and submit a pull request.

## License
- This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.
