// Author: Yanga Rubushe

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class State implements Comparable<State> {
    private int hValue;
    private int[] board = new int[13]; // do not use entry 0

    public State(int[] board) {
        this.board = board.clone();
        this.hValue = calculateHeuristicValue();
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.hValue, o.hValue);
    }

    public int getHValue() {
        return hValue;
    }

    public int[] getBoard() {
        return board.clone(); // Return a copy of the board array
    }

    private int calculateHeuristicValue() {
        int displacedSoldiers = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 0 && board[i] != i + 1) {
                displacedSoldiers++;
            }
        }
        return displacedSoldiers;
    }

    public List<State> generateChildStates() {
        List<State> children = new ArrayList<>();
        int emptyIndex = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                emptyIndex = i;
                break;
            }
        }

        if (emptyIndex != -1) {
            for (int i = 0; i < board.length; i++) {
                if (board[i] != 0 && isAdjacent(i, emptyIndex)) {
                    int[] newBoard = board.clone();
                    newBoard[emptyIndex] = newBoard[i];
                    newBoard[i] = 0;
                    if (isValidState(newBoard)) {
                        children.add(new State(newBoard));
                    }
                }
            }
        }
        return children;
    }

    private boolean isValidState(int[] board) {
        boolean[] occupied = new boolean[13]; // Track occupied cells
        for (int i = 0; i < board.length; i++) {
            int value = board[i];
            if (value < 0 || value > 9) {
                return false; // Invalid value (not between 1 and 9)
            }
            if (value != 0 && occupied[value]) {
                return false; // Duplicate non-zero value
            }
            occupied[value] = true;
        }
        return true;
    }

    private boolean isAdjacent(int i, int j) {
        int[] cellPositions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int iPos = Arrays.binarySearch(cellPositions, i);
        int jPos = Arrays.binarySearch(cellPositions, j);

        if (iPos >= 0 && jPos >= 0) {
            int diff = Math.abs(iPos - jPos);
            return diff == 1 || (diff == 2 && (iPos == 4 || jPos == 4 || iPos == 8 || jPos == 8));
        }

        return false;
    }

    public void printState(PrintStream out) {
        out.println("h = " + hValue);
        for (int i = 0; i < board.length; i++) {
            out.print(board[i] + " ");
        }
        out.println();
    }
}
