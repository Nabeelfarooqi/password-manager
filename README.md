# Password Manager



A Java Swing desktop application that generates cryptographically secure passwords and stores them locally using SQLite.



## Features

- Generates secure 12-character passwords using SecureRandom

- Makes sure there is at least one uppercase, lowercase, number, and special character

- Save passwords with site and username

- View all saved entries in a password.db file

- Local storage in SQLite



## How to Run

Either clone the entire repo or open the project into your personal IDE. Make sure to add sqlite-jdbc from Maven. Run `GeneratePasswordButton.java`



## Files

- `src/PasswordGenerator.java` — core password generation logic

- `src/GeneratePasswordButton.java` — Swing GUI + database integration

- `src/DatabaseManager.java` — SQLite connection and queries

- `passwords.db` — auto-created on first run