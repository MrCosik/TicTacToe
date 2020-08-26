package pl.pjatk.edu.Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, enter 9 characters:");

        int finalNumber = 0;

        String userInput = scan.nextLine();

        //Change user input into array of char
        char[] allCharacters = userInput.toCharArray();
        char[][] sortedChars = new char[3][3];

        //change 1d array to 2d
        transform1Dto2DArray(allCharacters, sortedChars);
        //show this array
        Show2DArray(sortedChars);

        CheckIfSomebodyWon(sortedChars,allCharacters);
    }

    //private static boolean CheckIfFinished()

    private static boolean CheckIfPossible(char[] allCharacters) {
        int numberOfXs = 0;
        int numberOfOs = 0;

        for(char chars : allCharacters){
            if(chars == 'X'){
                numberOfXs++;
            }else if(chars == 'O'){
                numberOfOs++;
            }
        }
        return Math.abs(numberOfXs - numberOfOs) <= 1;
    }


    private static void CheckIfSomebodyWon(char[][] sortedChars, char[] allCharacters) {
        int rowSum = 0;
        int colSum = 0;
        int diagonalSum = 0;
        int diagonalSumRev = 0;
        int blankSpaces = 0;
        boolean winX = false;
        boolean winO = false;

        for(char blank : allCharacters){
            if (blank == '_') {
                blankSpaces++;
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
        //first we check if the number of X's on board is not bigger than O's by more than 1
        //then we check if there are not two winners at the same time
        //finally who wins or if it's draw, game is not finished if there are blank spaces left and nobody won
        if(CheckIfPossible(allCharacters)){
            if(winX && winO){
                System.out.println("Impossible");
            }else if(winX){
                System.out.println("X wins");
            } else if(winO){
                System.out.println("O wins");
            } else if(blankSpaces > 0){
                System.out.println("Game not finished");
            }else{
              System.out.println("Draw");
            }
        } else {
            System.out.println("Impossible");
        }
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


