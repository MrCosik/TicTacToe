package pl.pjatk.edu.Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, enter 9 characters:");

        int finalNumber = 0;

        String userInput = scan.nextLine();

        //Change user input into array of char, also change all _ to spaces
        char[] allCharacters = userInput.replaceAll("_", " ").toCharArray();
        char[][] sortedChars = new char[3][3];


        //change 1d array to 2d
        transform1Dto2DArray(allCharacters, sortedChars);
        //show this array
        Show2DArray(sortedChars);


        if (!CheckIfPossible(allCharacters)){
            CheckIfSomebodyWon(sortedChars,allCharacters);
        }

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
        boolean win = false;

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
            if (rowSum == 264 || diagonalSum == 264 || diagonalSumRev == 264 || colSum == 264) {
                System.out.println("X wins");
                win = true;
            } else if (rowSum == 237 || diagonalSum == 237 || diagonalSumRev == 237 || colSum == 237) {
                System.out.println("O wins");
                win = true;
            } else {
                rowSum = 0;
                colSum = 0;
            }


        }
        if(!win){
            System.out.println("Draw");
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


