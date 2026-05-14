# 💻 IT Asset Lifecycle Management System

## 📖 Overview
The IT Asset Lifecycle Management System is a full-stack web application designed to help organizations efficiently track, manage, and maintain their physical technology assets (laptops, monitors, servers, etc.). This system replaces manual spreadsheet tracking with a secure, automated database, providing a clear audit trail from the moment an asset is purchased to when it requires maintenance.

This project was built as a Final Year Project to demonstrate proficiency in backend Java development, database management, and web application architecture.

## ✨ Key Features
* **Asset Tracking & Inventory:** View a complete, real-time list of all company hardware.
* **Asset Registration:** Easily log new equipment into the system with details like serial number, type, and purchase date.
* **Employee Assignment:** Digitally "check out" assets to specific employees to maintain accountability.
* **Maintenance Help Desk:** Log broken or malfunctioning equipment and raise maintenance requests for the IT team.
* **Automated Audit Logging:** The system automatically records a hidden history of every action taken (creation, assignment, updates) for security and tracking purposes.
* **Automated Database Generation:** Utilizes Hibernate to automatically build and manage MySQL database tables.

## 🛠️ Technology Stack
* **Backend:** Java 21, Spring Boot 3
* **Data Access:** Spring Data JPA, Hibernate
* **Database:** MySQL
* **Frontend:** HTML5, Thymeleaf (Server-side rendering), CSS
* **Build Tool:** Maven
* **IDE:** Visual Studio Code

## 🚀 How to Run Locally

### Prerequisites
* Java Development Kit (JDK) 21 or higher
* MySQL Server (running on port 3306)
* Maven

### Setup Instructions
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/AkshayaJeganathan/IT-asset-management.git](https://github.com/AkshayaJeganathan/IT-asset-management.git)
