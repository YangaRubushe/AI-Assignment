// Authors Surname: Rubushe
// Authors Name: Yanga
// File Description: "NineSoldiers" reads an initial state from "inputD.txt," performs Best-First Search with a heuristic, and outputs the sequence of expanded nodes and the solution state to "OutputB.txt," displaying the number of moves taken to reach the goal state. It uses a parameterized input file, output file, and initial moves to provide flexibility for testing different scenarios.
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NineSoldiers {
    private static final int[] GOAL_STATE = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0};

    public static void main(String[] args) {
        try {
            // Read the start state from a file
            Scanner scanner = new Scanner(new File("inputC.txt"));//replace inputC.txt with your input file path
            int[] initialBoard = new int[13];
            for (int i = 0; i < initialBoard.length; i++) {
                initialBoard[i] = scanner.nextInt();
            }
            scanner.close();

            // Initialize the priority queue with the start state
            PriorityQueue<State> frontier = new PriorityQueue<>();
            State initialState = new State(initialBoard);
            frontier.offer(initialState);

            // Initialize the set to store explored states
            Set<List<Integer>> explored = new HashSet<>();

            // Open output file
            PrintStream output = new PrintStream(new FileOutputStream("OutputB.txt"));//replace OutputB with your OutputB.txt file path

            int moves = 0;
            while (!frontier.isEmpty()) {
                State current = frontier.poll();
                if (Arrays.equals(current.getBoard(), GOAL_STATE)) {
                    // Goal state found
                    current.printState(output);
                    output.println("Goal state reached in " + moves + " moves.");
                    break;
                }

                explored.add(Arrays.stream(current.getBoard()).boxed().collect(Collectors.toList()));
                output.println("h=" + current.getHValue());
                printBoard(current.getBoard(), output);

                List<State> children = current.generateChildStates();
                for (State child : children) {
                    if (!explored.contains(Arrays.stream(child.getBoard()).boxed().collect(Collectors.toList()))) {
                        frontier.offer(child);
                    }
                }
                moves++;
            }

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printBoard(int[] board, PrintStream out) {
        for (int i = 0; i < board.length; i++) {
            out.print(board[i] + " ");
        }
        out.println();
    }
}
