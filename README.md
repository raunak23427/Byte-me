# Byte Me - E-Commerce College Project
## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [File Structure](#file-structure)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
## Project Overview
Byte Me is a comprehensive e-commerce application developed as a college project. The
application provides a robust platform for users to interact with an online store, supporting both
customer and administrative functionalities.
## Features
- *User Authentication*
- Secure user registration and login system
- Separate access for Customers and Admins
- *Inventory Management*
- Admin can add, update, and remove items
- Real-time inventory tracking
- Out-of-stock item management
- *Shopping Experience*
- Browse available items
- Add/remove items from cart
- Modify cart item quantities
- Place and manage orders
- *Order System*
- Create and track orders
- View order status
- Cancel pending orders
- Admin order status management
- *Data Persistence*
- Serialization of user, item, and order data
- Data retention between application sessions
## Technologies Used
- *Programming Language*: Java (JDK 21+)
- *Testing Framework*: JUnit 5
- *GUI Framework*: Java Swing
- *Data Storage*: Java Serialization
## Installation
### Prerequisites
- Java Development Kit (JDK) 21 or higher
- Git
- Integrated Development Environment (IDE)
### Steps
1. Clone the repository
bash
git clone https://github.com/yourusername/byte-me.git
cd byte-me
2. Open the project in your preferred Java IDE
3. Build the project using the IDE's build tools
4. Run the application by executing the ByteMeApp class
## Usage
### User Roles
- *Customer*
- Register/Login
- Browse items
- Manage shopping cart
- Place and track orders
- *Admin*
- Manage inventory
- View and update order statuses
- Add/remove/modify items
### Basic Workflow
1. Launch the application
2. Choose to register or login
3. Navigate through available functionalities based on user role
## File Structure
byte-me/
├── src/
│ ├── Admin.java
│ ├── ByteMeApp.java
│ ├── Customer.java
│ ├── Item.java
│ ├── ItemContainer.java
│ ├── Order.java
│ ├── OrderContainer.java
│ ├── User.java
│ ├── UserManager.java
│ └── tests/
│ └── CustomerTest.java
├── out/
├── Orders.ser
├── Items.ser
└── Users.ser
## Testing
### Unit Tests
The project includes comprehensive unit tests in CustomerTest.java, covering:
- Out-of-stock item ordering scenarios
- Login validation
- User interaction edge cases
### Running Tests
- Use your IDE's built-in test runner
- Alternatively, run tests via command line with JUnit
## Contributing
1. Fork the repository
2. Create a feature branch
bash
git checkout -b feature/your-feature-name
3. Commit your changes
bash
git commit -m 'Add some feature'
4. Push to the branch
bash
git push origin feature/your-feature-name
5. Open a pull request
Name - raunak kuamar giri 2023427
