package pl.pjatk.edu.Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, enter 9 characters:");
        String userInput = "         ";

        //Change user input into array of char
        char[] allCharacters = userInput.toCharArray();
        char[][] sortedChars = new char[3][3];
        char currentMove;
        int moveCounter = 0;

        //change 1d array to 2d
        transform1Dto2DArray(allCharacters, sortedChars);
        //show this array

        boolean again = true;
        do {
            currentMove = moveCounter%2 == 0 ? 'X' : 'O';

            Show2DArray(sortedChars);
            System.out.print("Enter the coordinates: ");
            int x = scan.nextInt() - 1;
            int y = scan.nextInt() - 1;
            if (x > 2 || y > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                if (!PlayerMove(sortedChars, x, y, currentMove)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    switch (CheckIfSomebodyWon(sortedChars, allCharacters)) {
                        case "X wins" -> {
                            Show2DArray(sortedChars);
                            System.out.println("X wins");
                            again = false;
                        }
                        case "O wins" -> {
                            Show2DArray(sortedChars);
                            System.out.println("O wins");
                            again = false;
                        }
                        case "Draw" -> {
                            Show2DArray(sortedChars);
                            System.out.println("Draw");
                            again = false;
                        }
                        default -> {
                            moveCounter++;
                            again = true;
                        }
                    }
                }
            }
        } while (again);
    }

    private static boolean PlayerMove(char[][] sortedChars, int x, int y, char currentMove) {
        if (sortedChars[Math.abs(2 - y)][x] == ' ') {
            sortedChars[Math.abs(2 - y)][x] = currentMove;
            return true;
        }
        return false;
    }

    private static String CheckIfSomebodyWon(char[][] sortedChars, char[] allCharacters) {
        int rowSum = 0;
        int colSum = 0;
        int diagonalSum = 0;
        int diagonalSumRev = 0;
        int blankSpaces = 0;
        boolean winX = false;
        boolean winO = false;
        String finalResault;

        for (char[] blank : sortedChars) {
            for (char c : blank) {
                if (c == ' ') {
                    blankSpaces++;
                }
            }
        }

        for (int i = 0; i < sortedChars.length; i++) {
            for (int j = 0; j < sortedChars.length; j++) {
                //we add char on rows, columns and diagonal and then check with the value of 3 X's or 3 O's
                rowSum += sortedChars[i][j];
                colSum += sortedChars[j][i];
                if (i == j) {
                    diagonalSum += sortedChars[i][j];
                }
                if (i + j == 2) {
                    diagonalSumRev += sortedChars[i][j];
                }
            }
            //now we check what is the value on every variable to confirm if there is a win on any of it
            if (rowSum == 264 || diagonalSum == 264 || diagonalSumRev == 264 || colSum == 264) {
                winX = true;
            } else if (rowSum == 237 || diagonalSum == 237 || diagonalSumRev == 237 || colSum == 237) {
                winO = true;
            }
            rowSum = 0;
            colSum = 0;
        }
        //whoever won we sing the value to a string to check it with switch later
        //if its blank the game loop will continue
        if (winX) {
            finalResault = "X wins";
        } else if (winO) {
            finalResault = "O wins";
        } else if (blankSpaces == 0) {
            finalResault = "Draw";
        } else {
            finalResault = "";
        }
        return finalResault;
    }

    private static void Show2DArray(char[][] sortedChars) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(sortedChars[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    private static void transform1Dto2DArray(char[] allCharacters, char[][] sortedChars) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sortedChars[i][j] = allCharacters[j % 3 + i * 3];
            }
        }
    }
}


