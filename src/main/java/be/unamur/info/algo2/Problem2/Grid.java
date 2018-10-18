package be.unamur.info.algo2.Problem2;

public class Grid {
    private char[][] matrix;

    public Grid(int[] sizeOfGrid, String[] gridLines) {
        this.matrix = new char[sizeOfGrid[0]][sizeOfGrid[1]];
        for(int i = 0; i < sizeOfGrid[0]; i++) {
            this.matrix[i] = gridLines[i].toCharArray();
        }
    }

    public int getBestPath() {
        return 1;
    }

    public int getNaiveBestPath() {
        return 2;
    }
}
