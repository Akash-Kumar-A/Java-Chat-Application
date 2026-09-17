# Java Group Chat Application

A simple server-based group chat application written in core Java, using multi-threaded sockets for real-time, multi-client text communication over a network.

**Authors:** Akash Kumar A (22PD03), Sujan S (22PD35)

## Overview

This project demonstrates multi-threaded socket programming to build a real-time group chat system. A central `ChatServer` accepts connections from multiple `ChatClient` instances and broadcasts every message it receives to all connected clients, enabling a shared group chat room over the network.

## Features

- **Real-time group messaging** — messages are broadcast instantly to every connected client
- **Multi-threading** — the server handles multiple simultaneous client connections using a thread pool (`ExecutorService`)
- **Usernames** — each client provides a name that is prefixed to their messages
- **Concurrent client handling** — a dedicated `ClientHandler` thread manages each client connection, with thread-safe access to the shared set of client writers
- **Graceful connect/disconnect handling** — client sockets and writers are cleaned up on disconnect
- **Command-line interface** — simple text-based console client

## Project Structure

```
JAVA PACKAGE/
├── JAVA CHAT APPLICATION.docx     # Project report (abstract, features, specifications, conclusion)
└── Project file/
    ├── src/
    │   ├── ChatServer.java        # Multi-threaded chat server
    │   └── ChatClient.java        # Console-based chat client
    ├── bin/                       # Compiled .class files
    │   ├── ChatServer.class
    │   ├── ChatServer$ClientHandler.class
    │   └── ChatClient.class
    └── java                       # Duplicate copy of ChatServer.java source
```

> Note: `javapro.java` (a `BarChartServlet` using JFreeChart/Servlets) is also present in the archive but is unrelated to the chat application — it appears to be an unrelated/leftover file.

## How It Works

### Server (`ChatServer.java`)
- Listens on **port 12345** via a `ServerSocket`.
- Uses a fixed thread pool (`Executors.newFixedThreadPool(10)`) to handle up to 10 concurrent clients.
- Each accepted connection is wrapped in a `ClientHandler` (`Runnable`) that:
  - Registers the client's `PrintWriter` in a thread-safe shared `Set`.
  - Reads incoming messages line-by-line and broadcasts them to all connected clients.
  - Removes the client and closes its socket when the connection ends.

### Client (`ChatClient.java`)
- Prompts the user for a display name.
- Connects to the server at `localhost:12345`.
- Spawns a background thread to continuously listen for and print incoming messages.
- Reads user input from the console and sends `name: message` to the server.

## Requirements

- **Java Development Kit (JDK) 8+**
- No external libraries needed — built entirely with `java.io`, `java.net`, `java.util`, and `java.util.concurrent`.

**Hardware (per project report):**
- Hard Disk: 2 GB minimum
- RAM: 256 MB or more

## Getting Started

### 1. Compile

```bash
cd "JAVA PACKAGE/Project file/src"
javac ChatServer.java ChatClient.java
```

### 2. Start the server

```bash
java ChatServer
```

You should see:
```
Chat Server is running...
```

### 3. Start one or more clients

In separate terminal windows:

```bash
java ChatClient
```

Each client will be prompted to enter a name, after which you can type messages that are broadcast to every connected client in real time. Run multiple client instances to simulate a group chat.

## Limitations / Future Improvements

As noted in the project report, this is a foundational implementation. Potential extensions include:

- Message persistence (chat history / logging)
- User authentication
- A graphical user interface (GUI) instead of the command-line client
- Private/direct messaging between users
- Configurable host/port instead of hardcoded `localhost:12345`

## License

No license specified.
