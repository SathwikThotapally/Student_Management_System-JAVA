````markdown
# 🎓 Student Management System

An efficient **Student Management System** built using **Java**, **JDBC**, and **MySQL/SQL**. This project allows users to perform all basic CRUD (Create, Read, Update, Delete) operations on student data via a console-based interface.

---

## 🚀 Features

- Add new student records
- View all student details
- Search for a student by ID, Email, Phone Number
- Fetch Total No of Students
- Update student information
- Delete student records
- JDBC-based interaction with MySQL database
- Console-based UI for simplicity

---

## 🛠️ Technologies Used

- **Java** - Core programming language
- **JDBC** - For database connectivity
- **MySQL** - Relational database for storing student data
- **SQL** - Query language for DB operations

---

## 🗃️ Database Schema

```sql
CREATE DATABASE students;

USE students;

CREATE TABLE StudentData (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(10) NOT NULL UNNQIE,
    branch VARCHAR(50) NOT NULL
);
````

---

## 📦 How to Run

1. **Clone the Repository**

```bash
git clone https://github.com/your-username/student-management-system.git
cd student-management-system
```

2. **Set Up the Database**

* Install MySQL and create the database and table using the schema above.
* Update your database credentials in the Java file (usually in a `DBConnection.java` or similar file).

```java
// Example DB connection setup
String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "root";
String password = "yourpassword";
```

3. **Compile and Run the Project**

```bash
javac *.java
java Main
```

---

## 📸 Screenshots

<img width="1911" height="841" alt="Screenshot 2025-10-15 005557" src="https://github.com/user-attachments/assets/d1b8d548-647c-4c75-9a97-5705da7819a7" />
<img width="1919" height="370" alt="Screenshot 2025-10-15 005628" src="https://github.com/user-attachments/assets/e3c986f4-7118-44f4-afd5-6e103f07305a" />


---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change or add.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👤 Author

* **Sathwik Thotapally**
* Email: [sathwikthotapally@gmail.com](mailto:your.sathwikthotapally@gmail.com)

```

---

### ✅ Next Steps

If you'd like help generating the `LICENSE`, Java files (`Main.java`, `DBConnection.java`, etc.), or anything else related to this project, just let me know!
```
