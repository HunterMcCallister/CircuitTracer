****************
* CircuitTracer
* CS 221
* 12/05/2024
* Hunter McCallister
****************

# Analysis:
I. The stack organizes path states using a Last-In-First-Out (LIFO) structure. As shown in class the most recently found path is explored next and that one is then removed from the call stack. Essentially it will complete one path before moving onto others.
The queue uses a First-In-First-Out (FIFO) structure. The call stack will balloon with every possible move scenario before shrinking down and solving the circuit. The stack more efficiently uses memory because it has less scenarios waiting to be finished.

II. The total number of search states explored is the same regardless of whether a stack or queue is used. The only difference is the sequence of exploration changes.

III. Neither storage can guarantee a best path faster. The speed depends on the board layout and how the search algorithm's logic is done.

IV.  No it does not matter which storage structure you are using. there is no way to guarantee the first solution is the shortest path.

V. Memory usage differs between configurations. A queue may hold all potential paths at the same time, consuming more memory. A stack replaces old paths once they are found to not be the solution. The Queue holds the maximum number of moves possible on the board.

VI. The runtime order, from my understanding after explination in class today, is 3^n and n is the size of the board. That is the worst case scenario because there is three directions it can go. There was another example that if the board was linear it would be O(n) and n is the size of the board because there is
no direction other than straight.

# OVERVIEW:
This program reads a grid from an input file, validates its structure and finds the shortest path between the start and finish (`1` and `2`) using a brute-force search algorithm. Solutions are printed to the console with paths represented by `T` characters. there is an optional GUI that is not implemented.

# INCLUDED FILES:
* CircuitTracer.java - Main driver class for input validation, pathfinding, and output.
* CircuitBoard.java - Validates and represents the circuit board layout.
* Storage.java - Implements stack/queue for storing search states.
* TraceState.java - Represents a single search state. (No modifications allowed)
* InvalidFileFormatException.java - Handles invalid file formats.
* OccupiedPositionException.java - Handles invalid board positions.
* README - This file, documenting the project and its processes.

# COMPILING AND RUNNING:
1. Ensure all included files are in the same directory.
2. In the terminal navigate to the directory containing all files.
3. From the terminal run the command $javac CircuitTracer to compile the files.
4. From the terminal run $java CircuitTracer -s|-q -c|-g filename
 -s for stack or -q for queue
 -c for console or -g for GUI (GUI is not implemented)
5. Output will display the completed circuit or an error if the file is not valid.

# PROGRAM DESIGN AND IMPORTANT CONCEPTS:
1. **CircuitBoard Class:**
   Validates the input file, ensuring:
   - The first line specifies the correct number of rows and columns. The files first two lines must be integers ex.(5, 6) matching the number of rows and columns.
   - The file contains exactly one `1` (start point) and one `2` (end point).
   - All other characters are either `O` (open space) or `X` (occupied position).
   Invalid files throw appropriate exceptions.

2. **CircuitTracer Class:**
   - Uses the `CircuitBoard` object to initialize pathfinding.
   - Implements a recursive search to find all optimal paths and checking valid moves (up, down, left, right).
   - Outputs the shortest paths to the console or GUI (not implemented).

3. **Storage Class:**
   - Implements both stack (LIFO) and queue (FIFO) configurations, allowing the user to choose at runtime.
   - Stack prioritizes exploring the most recent path; queue explores paths in discovery order.

4. **Recursive Search:**
   - Base case: Path ends at a position adjacent to `2`.
   - Recursively explores all valid moves from the current position.
   - Updates the best paths list when a shorter path is found.

# TESTING:
The program was tested using the provided `CircuitTracerTester`. steps I took were:
1. Checking the `CircuitBoard` class took input files and were parsed. I also implemented all the check to make sure errors were handled correctly.
2. After It was taking the files I went down the list of all of the failing test cases and started to debug them one at a time. I used the debugger to step through the reading of the files to find my errors.
3. One thing that really held me up was that I was unaware you had to compile your program in order to get some of the tests to pass. I do not think I would have ever figured it out if it was not for someone telling me.
4. I worked with the TAs to talk through what my code was supposed to be doing and what it was actually doing.

# DISCUSSION:
The issues I encountered was more of a lack of direction. I was not entirely sure what logic we were supposed to be doing. I underestimated the project because the way it was portrayed made it seem like a fairly easy assignment that we should be able to do. 
My main issues were debugging and trying to figure out why certain errors were being thrown when in my code I thought it was taking care of it. I am thankful for the TAs because they were able to point out the flaws in my logic and help me understand why I was wrong.
There was no "clicked" moment on this project as I did not have as much time to really engage in it because I was allocating most of my time preparing for finals. I took it as more as something I need to get out of the way so I can continue studying for finals.
Other than that it is a neat concept and something I plan on revisiting once things are not as busy. 
