# Java-Chat-Application

## Project Overview
The Java Chat Application is a real-time messaging system built using Java Socket Programming and Swing GUI. The application follows a client-server architecture where multiple clients can connect to a server and exchange messages instantly.

The system supports group messaging, private messaging, and user join/leave notifications.


## Objective
The objective of this project is to demonstrate networking concepts in Java by implementing a client-server chat system that enables real-time communication between multiple users.


## Technologies Used

- Java
- Socket Programming
- Multithreading
- Java Swing (GUI)
- Eclipse IDE


## Features

- Multiple clients can connect to the server
- Real-time message broadcasting
- Private messaging between users
- User join and leave notifications
- Graphical chat interface
- Multithreaded server handling multiple clients


## Project Structure

```
ChatApplication
│
├── Server.java
├── ClientHandler.java
├── ChatClientGUI.java
└── README.md
```

## How It Works

1. The server starts and listens for incoming client connections.
2. Clients connect to the server using sockets.
3. Each connected client is handled using a separate thread.
4. Messages sent by one client are broadcast to all connected clients.
5. Private messages can be sent using the `@username` format.

Example:

```
@Rahul Hello
```

This message is only delivered to Rahul.


## How to Run the Project

### Step 1: Start the Server

Run:

```
Server.java
```

Output:

```
Server started on port 5000
```

---

### Step 2: Start Chat Clients

Run:

```
ChatClientGUI.java
```

Enter your username when prompted.


### Step 3: Chat with Other Users

Example chat session:

```
Akshu: Hello everyone
Rahul: Hi Akshu!
```

Private message example:

```
@Rahul Are you free?
```

Rahul receives:

```
(Private) Akshu: Are you free?
```

---

## Skills Demonstrated

- Java Networking
- Socket Programming
- Multithreading
- Client-Server Architecture
- GUI Development using Swing


# File-Compressor-and-Decompressor

## Project Overview
The File Compressor and Decompressor is a Java-based desktop application that allows users to compress files into ZIP format and decompress ZIP files back to their original form. The application provides a simple graphical user interface using Java Swing and uses the built-in Java `java.util.zip` package for file compression and extraction.

## Objective
The objective of this project is to develop a tool that reduces file size by compressing files and allows users to extract compressed files when needed.


## Technologies Used

- Java
- Java Swing (GUI)
- java.util.zip package
- Eclipse IDE


## Features

- Compress files into ZIP format
- Decompress ZIP files
- Simple graphical interface
- File selection using file chooser
- Efficient file handling using buffers


## Project Structure

```
FileCompressor
│
├── FileCompressorGUI.java
└── README.md
```


## How It Works

1. The user selects a file using the **Compress File** button.
2. The application reads the selected file.
3. The file is compressed using `ZipOutputStream`.
4. The compressed `.zip` file is saved in the same directory.

For decompression:

1. The user selects a `.zip` file.
2. The application extracts the contents using `ZipInputStream`.
3. The original file is restored in the same folder.


## How to Run the Project

1. Open the project in Eclipse or any Java IDE.
2. Run the file:

```
FileCompressorGUI.java
```

3. Click **Compress File** to compress any file.
4. Click **Decompress File** to extract a ZIP file.


## Example

Original file:

```
report.docx
```

Compressed file:

```
report.docx.zip
```

After decompression:

```
report.docx
```


## Skills Demonstrated

- File handling in Java
- Data compression using ZIP streams
- GUI development using Swing
- Exception handling




# SmartTaskScheduler

## 🧠 Smart Task Scheduler with Priority Queue

## 📌 Project Overview

The Smart Task Scheduler is a desktop-based Java application designed to manage and prioritize daily tasks efficiently. The system uses the PriorityQueue data structure to automatically sort tasks based on urgency (priority) and deadline.

The application provides a graphical user interface built using Java Swing, allowing users to add, edit, delete, and mark tasks as completed. It also includes filtering options and a reminder system.

## 🎯 Objective

To build a task management system that:

Prioritizes tasks based on urgency

Automatically sorts tasks using PriorityQueue

Provides a user-friendly graphical interface

Saves and loads tasks using file persistence

Implements reminder functionality

## 🛠 Technologies Used

Java (Core Java)

Swing (GUI Development)

PriorityQueue (Java Collections Framework)

Java Timer & TimerTask (Reminders)

ObjectOutputStream & ObjectInputStream (File Handling)

Eclipse IDE

## ✨ Features

✔ Add new tasks

✔ Edit existing tasks

✔ Delete tasks

✔ Mark tasks as completed

✔ View today’s tasks

✔ Filter high priority tasks

✔ Show all tasks

✔ Automatic task prioritization

✔ Reminder notifications in console

✔ Data persistence using serialization

## 🏗 Project Structure

SmartTaskScheduler
│
├── Task.java
├── TaskManager.java
└── TaskSchedulerUI.java
🔄 How It Works

## A Task class is created with properties:

Title

Description

Priority

Deadline

Completion status

Tasks are stored in a PriorityQueue, which automatically sorts them using the compareTo() method.

## The TaskManager class handles:

Add

Edit

Delete

Mark Completed

Filtering

Saving and Loading tasks

## The GUI (Swing) allows users to interact with the system.

A reminder system runs periodically using Timer and TimerTask.

## 💾 Data Storage

Tasks are stored locally in a file named:

tasks.dat

This file is automatically created when tasks are added.

## 🚀 How to Run the Application

Option 1: Using Eclipse

Open project in Eclipse

Run TaskSchedulerUI.java

The application window will open

Option 2: Using Executable JAR

If exported as a .jar file:

java -jar SmartTaskScheduler.jar

## 🎓 Concepts Demonstrated

Object-Oriented Programming (OOP)

Java Collections Framework

Comparable Interface

File Serialization

Multithreading (Timer)

Event Handling in Swing

GUI Development

# RESTful-Bookstore-API

## Project Overview
The RESTful Bookstore API is a backend application developed using **Spring Boot** that allows users to manage books through REST API endpoints. The system supports operations such as adding new books, retrieving book details, deleting books, and filtering books by author. The application uses **Spring Data JPA** for database interaction and **H2 database** for storage.

This project demonstrates the implementation of RESTful web services, database connectivity, and backend architecture using Java.

## Objective
The objective of this project is to build a RESTful API that can manage bookstore data and allow users to perform CRUD operations using HTTP requests.

## Technologies Used

- Java  
- Spring Boot  
- Spring Data JPA  
- H2 Database  
- Maven  
- Postman (API Testing)  
- Swagger UI (API Documentation)  
- Eclipse IDE  

## Features

- Add a new book  
- Retrieve all books  
- Delete a book by ID  
- Filter books by author  
- Pagination and sorting support  
- API testing using Postman  
- Interactive API documentation using Swagger UI  

## API Endpoints

| Method | Endpoint | Description |
|------|------|------|
| GET | `/books` | Retrieve all books |
| POST | `/books` | Add a new book |
| DELETE | `/books/{id}` | Delete book by ID |
| GET | `/books/author/{author}` | Filter books by author |
| GET | `/books?page=0&size=5&sortBy=price` | Pagination and sorting |


## Example JSON Request

### POST `/books`

```json
{
  "title": "Spring Boot Guide",
  "author": "John Smith",
  "price": 600
}
```


## Swagger API Documentation

After running the application, open:

```
http://localhost:8080/swagger-ui/index.html
```

This page provides an interactive interface to test the API endpoints.


## Postman Collection

API requests were tested using Postman.

Collection Link:

```
https://restless-eclipse-21381.postman.co/workspace/My-Workspace~bc31eaf0-f707-4e51-808d-8decbea03f68/collection/42883219-a01d73c0-a870-4839-b8c8-9b917662a649
```

---

## How to Run the Project

1. Clone or download the project.
2. Open the project in **Eclipse or any Java IDE**.
3. Run the main class:

```
BookstoreApplication.java
```

4. The application will start on:

```
http://localhost:8080
```

5. Test APIs using **Postman** or **Swagger UI**.

