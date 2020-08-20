package pl.pjatk.edu.Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello, enter 9 characters:");


        String userInput = scan.nextLine();
        char[] allCharacters = userInput.toCharArray();
        char[][] sortedChars = new char[3][3];

        System.out.println("---------");
        transform1Dto2DArray(allCharacters, sortedChars);

        Show2DArray(sortedChars);

        System.out.println("---------");
    }

    private static void Show2DArray(char[][] sortedChars) {
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 0; j < 3; j++){
                System.out.print(sortedChars[i][j] + " ");
            }
            System.out.print(" |");
            System.out.println();
        }
    }

    private static void transform1Dto2DArray(char[] allCharacters, char[][] sortedChars) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                sortedChars[i][j] = allCharacters[j%3+i*3];
            }
        }
    }
}
