# 🧠 EasyKanban Java Application
### PROG5121 POE Part 3 – Programming 1A

This project is a **Java-based task management system** developed for the **Programming 1A (PROG5121)** module. It follows a Kanban-style workflow and was built in **three sprints**, applying core programming concepts such as classes, arrays, loops, conditionals, and unit testing.

---

## 📁 Project Structure

POE Part 3/
├── .vscode/ # VS Code settings
├── lib/ # External libraries (e.g., JUnit)
├── src/
│ └── java/ # Source code: Login, Task and Main
├── tests/
│ └── java/ # Unit tests for Login and Task classes


---

## 💡 Features

### ✅ Sprint 1 – Registration and Login
- User registration with:
  - Username validation (must include `_` and ≤ 5 characters)
  - Password complexity check (min 8 characters, 1 capital, number, and special char)
- Login verification with appropriate success/error messages
- JUnit tests for:
  - `checkUserName()`
  - `checkPasswordComplexity()`
  - `loginUser()`
  - `returnLoginStatus()`

### ✅ Sprint 2 – Task Management
- Add multiple tasks with:
  - Auto-generated Task ID (e.g. `AD:1:THA`)
  - Description validation (≤ 50 characters)
  - Task fields: Name, Number, Developer, Duration, Status
- Menu system using `JOptionPane`
- Task details printed after entry
- Total duration calculated
- JUnit tests for:
  - Task ID
  - Description check
  - Total duration
- Automated testing with GitHub Actions

### ✅ Sprint 3 – Array-Based Reporting
- Parallel arrays for:
  - Developers, Task Names, IDs, Durations, Statuses
- Features:
  - Show all "Done" tasks
  - Show longest-duration task
  - Search by task name or developer
  - Delete tasks
  - Display full task report
- More JUnit tests for array operations

---

## 🧪 Tools and Technologies

- Java (JDK 17+)
- JUnit 5 for unit testing
- Git & GitHub for version control
- GitHub Actions for CI (test automation)
- VS Code or NetBeans (IDE)

---

## 🚀 How to Run

1. Clone the repository
2. Open in your IDE
3. Run `Main.java` in `src/java/`
4. Use the menu to interact with the program

To run tests:
- Make sure JUnit is installed in your project
- Run test files in `tests/java/` using your IDE or `mvn test` if using Maven

---

## 📝 Author

**Jemimah Muronda**  
Student Number: **ST10446684**  
Module: Programming 1A  
Assessment: POE Part 3

---

## 🔒 Notes

- This project follows The IIE’s academic integrity policies.
- All test data and implementation follow the official POE brief.
