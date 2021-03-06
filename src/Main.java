import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] array = new char[3][3];
        char[] xRow = {'X', 'X', 'X'};
        char[] oRow = {'O', 'O', 'O'};
        int yCoord;
        int xCoord;
        int moveCount = 0;
        boolean hasEmptyCells = false;
        boolean xWins = false;
        boolean oWins = false;

        // fills table with empty spaces
        for (char[] row : array) {
            Arrays.fill(row, ' ');
        }

        printGameState(array);

        while (true) {
            System.out.print("Enter the coordinates: ");

            // checks input correctness
            try {
                yCoord = scanner.nextInt();
                xCoord = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }

            if ((yCoord >= 1 && yCoord <= 3) && (xCoord >= 1 && xCoord <= 3)) {
                if (array[yCoord - 1][xCoord - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (moveCount % 2 == 0) {
                        array[yCoord - 1][xCoord - 1] = 'X';
                    } else {
                        array[yCoord - 1][xCoord - 1] = 'O';
                    }
                    moveCount++;

                    hasEmptyCells = checkEmptyCells(array, hasEmptyCells);

                    // checks winning condition on rows
                    for (char[] row : array) {
                        if (Arrays.equals(row, xRow)) {
                            xWins = true;
                        }
                        if (Arrays.equals(row, oRow)) {
                            oWins = true;
                        }
                    }

                    // checks winning condition on columns
                    for (int j = 0; j < array.length; j++) {
                        int i = 1;
                        if (array[i][j] == array[i - 1][j] && array[i][j] == array[i + 1][j]) {
                            if (array[i][j] == 'X') {
                                xWins = true;
                            } else if (array[i][j] == 'O') {
                                oWins = true;
                            }
                        }
                    }

                    // checks winning condition on 'top left to bottom right' diagonal
                    if (array[0][0] == array[1][1] && array[1][1] == array[2][2]) {
                        if (array[1][1] == 'X') {
                            xWins = true;
                        } else if (array[1][1] == 'O') {
                            oWins = true;
                        }
                    }

                    // checks winning condition on 'top right to bottom left' diagonal
                    if (array[0][2] == array[1][1] && array[1][1] == array[2][0]) {
                        if (array[1][1] == 'X') {
                            xWins = true;
                        } else if (array[1][1] == 'O') {
                            oWins = true;
                        }
                    }

                    printGameState(array);

                    if (checkGameOverConditions(hasEmptyCells, xWins, oWins)) {
                        break;
                    }
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }

    // prints game state
    public static void printGameState(char[][] array) {
        System.out.println("---------");
        for (char[] row : array) {
            System.out.print("| ");
            for (int j = 0; j < array.length; j++) {
                System.out.print(row[j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    // checks if there are empty cells
    public static boolean checkEmptyCells(char[][] array, boolean hasEmptyCells) {
        for (char[] row : array) {
            for (int j = 0; j < array.length; j++) {
                if (row[j] == ' ') {
                    hasEmptyCells = true;
                    break;
                } else {
                    hasEmptyCells = false;
                }
            }
            if (hasEmptyCells) {
                break;
            }
        }

        return hasEmptyCells;
    }

    // checks whether game over conditions are met
    public static boolean checkGameOverConditions(boolean hasEmptyCells, boolean xWins, boolean oWins) {
        if (!hasEmptyCells && !xWins && !oWins) {
            System.out.println("Draw");
            return true;
        } else if (xWins) {
            System.out.println("X wins");
            return true;
        } else if (oWins) {
            System.out.println("O wins");
            return true;
        }

        return false;
    }
}
