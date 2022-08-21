package sk.tuke.gamestones.consoleui;

import sk.tuke.gamestones.core.Field;

import java.util.Scanner;

import static java.lang.Character.isDigit;

public class ConsoleUI {
    private Field field;
    private Scanner scanner = new Scanner(System.in);

    private String readLine() {
        return scanner.nextLine();
    }

    private int dimensionSelection() {
        var dimension = -1;
        do {
            System.out.println("Please enter dimension.");
            try {
                String dimensionString = readLine();
                if (dimensionString != null) {
                    for (int i = 0; i < dimensionString.length(); i++) {
                        if (isDigit(dimensionString.charAt(i))) {
                            dimension = Integer.parseInt(dimensionString);
                        } else {
                            throw new NumberFormatException();
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format.");
            }
        } while (dimension <= 1);
        return dimension;
    }

    private void printField(int[][] matrix) {

        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix.length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private int processInput() {
        var number = -1;
        do {
            System.out.println("Enter number.");
            try {
                String numberIn = readLine();
                if (numberIn != null) {
                    for (int i = 0; i < numberIn.length(); i++) {
                        if (isDigit(numberIn.charAt(i))) {
                            number = Integer.parseInt(numberIn);
                        } else {
                            throw new NumberFormatException();
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format.");
            }
        } while (number < 1);
        return number;
    }

    public void play() {
        int dimension = dimensionSelection();
        var field = new Field(dimension);
        int count = 0;
        do {
            field.ShuffleField();
            count++;
        } while (count != dimension * dimension * 10);  //for 3x3 - 2; for 4x4 - 85
        printField(field.getMatrix());
        do {
            int number = processInput();
            field.shiftNumber(number);
            printField(field.getMatrix());
        }
        while (!field.isDone());
    }
}