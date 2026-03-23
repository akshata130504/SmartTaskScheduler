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
