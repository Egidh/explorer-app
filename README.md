# Explorer App

This app is a project aiming to replicate the Unix terminal file explorer experience. It allows users to navigate through directories, add files and folders, and see the structure of their file system in a terminal-like interface.

## Features
- Navigate through directories using commands like `cd`:
    - `cd <folder_name>`: Change to a specified folder.
- Add files and folders using commands like `touch` and `mkdir`:
    - `touch <file_name>`: Create a new file in the current directory.
    - `mkdir <folder_name>`: Create a new folder in the current directory.
    - `touch <file_name> <file_name> ...`: Create multiple files at once.
    - `mkdir <folder_name> <folder_name> ...`: Create multiple folders at once
- Display the current directory structure using the `ls` command:
    - `ls`: List all files and folders in the current directory.
    - `ls <path>`: List all files and folders in the specified path.
- Exit the application using the `exit` command.

## General Information
As this project is a demonstration, it start with this architecture:
```.
/
├── tmp/ 
├── home/
    ├── hello.txt

```
As it is not possible to add content to files, all files willl have a randomly generated size.
Folders will have the number of items they contain as their size.

## Compile and Run
To compile and run the Explorer App you can either use the `compile_and_run.sh` script or follow the steps below:
1. Compile the Java files:
   ```bash
   javac -d build/ $(find "./src" -name "*.java")
   ```
2. Run the application:
   ```bash
   java -cp build/ explorer.ExplorerApp
   ```
Please be sure to have Java, JDK and its compiler installed on your machine.