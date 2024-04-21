# Nine Little Soldiers Puzzle Solver

This Java program aims to solve the Nine Little Soldiers puzzle, where 9 numbered soldiers are trapped in a bomb shelter with 13 cells. The goal is to move the soldiers to their corresponding numbered cells, ensuring soldier 1 is in cell 1, soldier 2 in cell 2, and so on until soldier 9 is in cell 9.

## Task Formulation

### State
- Each state represents the arrangement of soldiers in the bomb shelter cells.
- The state includes an array with 14 entries, where each index represents a cell, and the value represents the soldier occupying that cell.
- An additional attribute stores the heuristic value (h value) of the state.

### Goal State
- The goal state is achieved when all soldiers are in their correct cells.

### Moves (Actions)
- A soldier may move into an adjacent empty cell.

## State Space Exploration

The first three levels of the state space are explored, starting from the initial state depicted in the figure provided. The Displaced Soldiers heuristic is utilized, indicating the number of soldiers not in their correct cells for each state.

## Best First Search Algorithm Implementation

The Java implementation of the Best First Search algorithm is provided in the `NineSoldiers.java` file. The program reads the contents of a start state from a text file, performs the search algorithm, and outputs the sequence of expanded nodes.

### Class Structure
- The `State` class represents a state in the puzzle.
  - It includes an array to represent the cells and soldiers' positions, along with methods for moves and state evaluation.
- The `NineSoldiers` class contains the main implementation of the Best First Search algorithm.
  - It utilizes a PriorityQueue to store the frontier list of expanded states, sorted by heuristic value.

### Output
- The output includes the sequence of nodes expanded during the search, with each state's heuristic value and cell contents displayed.
- The program also shows the number of moves taken to reach the goal state.
- Output is written to both an output file and displayed on the screen.

**Author:**
- Yanga Rubushe
