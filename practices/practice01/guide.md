# Practices 1
## Overview
In these practices, you explore your environment to develop Java applications. In Practice 1-1, you verify the Java Development Kit (JDK) installation and locate the practices folder structure. In Practice 1-2, you create, compile, and execute your first Java program.

```
- JDK_HOME
   - bin
      - java
      - javac
   - conf
   - include
   - jmods
   - legal
   - lib
```

Java Development Kit (JDK) folder structure: In this course environment, JDK_HOME refers to the `/usr/java/jdk-11.0.4` folder. 

Practices folder structure:
`/home/oracle/labs/practice1/sources`
`/home/oracle/labs/practice1/classes`

## Practice 1-1: Verify the JDK Installation
### Overview
In this practice, you verify the Java Development Kit (JDK) installation and practices setup. 

### Assumptions
- JDK 11 is installed. 
- JDK executables path is configured.
- The practices folder structure is created. 

### Tasks
1. Verify that JDK is installed and the jdk bin folder is added to the path.
   1. Open Terminal
   2. Invoke Java Runtime and test the installed Java version: java -version.
   3. Invoke Java compiler: javac.
2. Navigate to the course practices folder in the terminal window.
   1. Change directory to the course practices folder: cd labs/practice1.
   2. List the contents of the first lesson practice folder ls -al.

Notes
- JDK has already been installed on this computer. However, if you want to download and install JDK in your own environment, it is available from: https://www.oracle.com/technetwork/java/javase/downloads/index.html
- JDK 11 is not the latest version of Java SE available. However, it is marked as (LTS) Long Term Support, which means it is going to be supported by Oracle longer than other versions (12, 10, and 9).
- To invoke java or javac executables, your JDK_HOME/bin folder should be included in the PATH variable. This has already been configured on your practices machine.

## Practice 1-2: Create, Compile, and execute a Java Application
### Overview
In this practice, you create, compile, and execute a Java application

### Assumptions
You have successfully competed Practice 1-1.

### Tasks
1. Create the Java class `HelloWold` in the labs package in the sources folder:
    1. Verity your current path in the terminal window using: `pwd` **Note:** Your current folder should be `/home/oracle/labs/practicel`. Change directory to this folder if this is not the case already.
    2. Create the Java class `HelloWorld` in the labs package in the sources folder: `gedit /sources/labs/HelloWorld.java &`.
    3. Define the class structure of the `HelloWorld` class, as a member of the labs package in the editor: package labs; public class HelloWorld
// methods of this class will be added here
Note: The full path to the file containing the source code of the HelloWorld class is /home/oracle/labs/practicel/source/labs/HelloWorld.java. The first occurrence of the word "labs is just a folder name that contains the entire set of practices for this course, while the second occurrence of the word "abs" is a package name. One is not in any way linked to the other. This package name is not following the reverse-dns naming convention usually used for packages. d. Create a method to representa default entry point within HelloWorld class: public static void main(String[] args) {
1/ logic of the method will be added here
0. Add code to the main method to print a message "Belle
concatenated with the

