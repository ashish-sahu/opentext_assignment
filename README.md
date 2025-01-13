# OpenText Assignment

## Overview

This repository contains solutions for various programming tasks, including a Sudoku solver and a C program demonstrating common vulnerabilities. The project is structured as follows:

```
project2_vulnerabilities/
    project2.c
ExprSolver_round2.java
README.md
SudokuSolver.java
TestCase.java
```

## Wiki
For more detailed information, please visit the following wiki pages:

* [Architecture Document](https://github.com/ashish-sahu/opentext-assignment/wiki/Architecture-Document)
* [SudokuSolver Implementation Details](https://github.com/ashish-sahu/opentext-assignment/wiki/Sudoku-Solver)

## Project Structure

### 

project2.c



This C file contains several functions that demonstrate common vulnerabilities, such as buffer overflows, memory leaks, and unsafe string handling. The vulnerabilities are annotated with comments.

###

ExprSolver_round2.java

This Java file contains expression solver implementation . It evaluates the given expresssion and print values of each variable.


### 

SudokuSolver.java



This Java file contains a Sudoku solver implementation using backtracking. The solver includes methods to check the validity of number placements and to print the Sudoku board.

### 

TestCase.java



This Java file provides test cases for the Sudoku solver. It includes two sample Sudoku boards that can be used to test the solver's functionality.

## Usage

### Compiling and Running the C Program

To compile and run the C program, navigate to the 

project2_vulnerabilities

 directory and use the following commands:

```sh
gcc project2.c -o project2
./project2
```

### Running the ExprSolver_round2
```sh
javac ExprSolver_round2.java
java ExprSolver_round2
```

### Running the Sudoku Solver

To run the Sudoku solver, compile and execute the Java files:

```sh
javac SudokuSolver.java TestCase.java
java SudokuSolver
```

The program will attempt to solve the provided Sudoku boards and print the results to the console.

## Contact

For any questions or issues, please contact the repository owner.
