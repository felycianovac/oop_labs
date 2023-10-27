# Laboratory Work 2

## File Change Detection System

This is a File Change Detection System (a simplified version of VCS) that monitors and detects changes in documents
within a specified directory. The system is designed to monitor a designated folder, offering Git-like functionality
for tracking changes in files. The system is designed to be used in a command-line environment.

## Lab Tasks

### Base Laboratory (8 Grade)

In the base laboratory, the program offers an interactive command line interface for the user to monitor changes in a folder. Users can perform three actions:

1. **commit**: Update the snapshot time to the current time to simulate  change detection since the previous snapshot. This action resets the state.
2.  **info <filename>**: Display general information about a file. Information varies based on the file type (extension). Supported file types include text, image, and program files.
3. **status**: Show changes in the files during the snapshot time and current time.

### Improved Laboratory (9 Grade)
In the improved laboratory, the program can also detect when files are added or deleted in the specified folder, in addition to changes within existing files.

### Working System (10 Grade)

In the working system, the program detects changes in real-time. It automatically runs a detection flow every 5 seconds and prints each change to the console. The user can continue to use the console to call actions without interruption.

## Usage

This program is designed for educational purposes and serves as a practical exercise in object-oriented programming, emphasizing inheritance and polymorphism.

