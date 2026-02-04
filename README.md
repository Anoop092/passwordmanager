# 🔐 CLI Password Manager (Java + SQLite)

A secure, command-line–based password manager built using **Java**, **SQLite**, and **AES encryption**.  
This project demonstrates how sensitive data can be securely stored using encryption, hashing, and clean architecture principles.

> ⚠️ This project is built for **learning and demonstration purposes** and is not intended for production use.

---

## ✨ Features

- User **Signup & Login** with a master password
- **One encrypted vault per user**
- Vault is encrypted **as a whole**, not per entry
- **AES encryption** for vault protection
- **Password hashing with salt** for authentication
- Vault is **decrypted only after successful login**
- **Session-based design** (vault lives in memory during session)
- **SQLite database** for persistence
- Clean separation of:
  - UI Layer
  - Service Layer
  - Repository Layer
  - Crypto Layer
- Proper JDBC resource handling (prevents SQLite locking issues)

---


### Key Design Points

- The **vault is stored encrypted** in the database
- JSON is used **only as a temporary format** before encryption
- The vault is:
  - 🔓 Decrypted once at login
  - 🧠 Used in memory during the session
  - 🔒 Re-encrypted before saving or logout

---

## 🔒 Security Design

- Master password is **never stored**
- Passwords are **hashed with salt**
- Encryption key is derived at login and stored **only in session memory**
- Database never stores plaintext credentials

---

## 🛠️ Tech Stack

- **Java**
- **SQLite**
- **JDBC**
- **Java Cryptography API (AES)**
- **Jackson (JSON serialization)**

---

## 📂 Project Structure

src/
├── ui/ # CLI interaction
├── services/ # Business logic
├── repositories/ # JDBC + SQLite
├── crypto/ # Encryption / Decryption
├── dto/ # Data Transfer Objects
└── utils/ # Helper utilities

🧠 Author

Built as a learning project to understand backend fundamentals, encryption, and system design using Java.


