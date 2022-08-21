package sk.tuke.gamestones.core;

import java.util.Random;

public class Field {
    private final int[][] matrix;

    public Field(int dimension) {
        matrix = new int[dimension][dimension];
        initialize();
    }

    public int getDimension() {
        return matrix.length;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void initialize() {
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix.length; j++) {
                if (i == matrix.length - 1 && j == matrix.length - 1)
                    matrix[i][j] = 0;
                else
                    matrix[i][j] = i * matrix.length + j + 1;
            }
        }
    }

    public void ShuffleField() {
        Random random = new Random();
        int row = -1;
        int col = -1;
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }
        findNumberOfField(row, col);
        int[] numberPossible = findNumberOfField(row, col);
        int randomNumber = random.nextInt(numberPossible.length);
        int number = numberPossible[randomNumber];
        if (number > 0)
            shiftNumber(number);
    }

    private int[] findNumberOfField(int row, int col) {
        int left = 0;
        if ((row - 1) >= 0) {
            left = matrix[row - 1][col];
        }

        int right = 0;
        if ((row + 1) <= matrix.length - 1) {
            right = matrix[row + 1][col];
        }

        int up = 0;
        if ((col - 1) >= 0) {
            up = matrix[row][col - 1];
        }

        int down = 0;
        if ((col + 1) <= matrix.length - 1) {
            down = matrix[row][col];
        }
        return new int[]{right, left, up, down};
    }

    public void shiftNumber(int number) {
        try {
            int row = -1;
            int col = -1;
            for (var i = 0; i < matrix.length; i++) {
                for (var j = 0; j < matrix.length; j++) {
                    if (number == matrix[i][j]) {
                        row = i;
                        col = j;
                    }
                }
            }
            if (row == -1 || col == -1) {
                throw new IllegalArgumentException("Number not found.");
            }
            changeField(row, col);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong number.");
        }
    }

    private void changeField(int row, int col) {
        if ((row - 1) >= 0 && matrix[row - 1][col] == 0) {
            var temp = matrix[row][col];
            matrix[row][col] = matrix[row - 1][col];
            matrix[row - 1][col] = temp;
        }

        if ((row + 1) <= matrix.length - 1 && matrix[row + 1][col] == 0) {
            var temp = matrix[row][col];
            matrix[row][col] = matrix[row + 1][col];
            matrix[row + 1][col] = temp;
        }

        if ((col - 1) >= 0 && matrix[row][col - 1] == 0) {
            var temp = matrix[row][col];
            matrix[row][col] = matrix[row][col - 1];
            matrix[row][col - 1] = temp;
        }

        if ((col + 1) <= matrix.length - 1 && matrix[row][col + 1] == 0) {
            var temp = matrix[row][col];
            matrix[row][col] = matrix[row][col + 1];
            matrix[row][col + 1] = temp;
        }
    }

    public boolean isDone() {
        int value = 1;
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix.length; j++) {
                if (i == matrix.length - 1 && j == matrix.length - 1 && matrix[i][j] != 0) {
                    return false;
                } else if (!(i == matrix.length - 1 && j == matrix.length - 1) && matrix[i][j] != value) {
                    return false;
                }
                value++;
            }
        }
        return true;
    }
}