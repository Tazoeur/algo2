package be.unamur.info.algo2.Problem2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Problem2 {
    private Grid[] grids;

    public Problem2(String file_path) {
        this.readFile(file_path);
    }

    private void readFile(String file_path) {
        List<String> list = new ArrayList<String>();
        File file = new File(file_path);
        BufferedReader reader = null;
        String line = null;
        int numberOfLines = 0;
        int numberOfGrid = 0;
        int[] sizeOfGrid = new int[2];
        String[] gridLines = null;
        int countGridLines = 0;

        Boolean nextIsGridSize = true;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                numberOfLines++;

                // here we are at line 1, we can get the number of different grid that are present in the given file
                if(numberOfLines==1) {
                    this.grids = new Grid[Integer.valueOf(line)];
                    continue;
                }

                // here we have to get the size of the next Grid
                if(nextIsGridSize) {
                    for(int i = 0; i< 2; i++) {
                        // have to tweak like this because in the test file, there is no space in the last grid case
                        sizeOfGrid[i] = Character.getNumericValue(line.replaceAll(" ", "").toCharArray()[i]);
                    }
                    gridLines = new String[sizeOfGrid[0]];
                    nextIsGridSize = false;
                    continue;
                }

                // fill the gridLines and increment counter
                gridLines[countGridLines] = line;
                countGridLines++;

                // reset the counter & the stored lines
                if(countGridLines == gridLines.length) {
                    this.grids[numberOfGrid] = new Grid(sizeOfGrid, gridLines);
                    countGridLines = 0;
                    nextIsGridSize = true;
                    gridLines = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }

    }

    public String[] solve() {
        int length = this.grids.length;
        String[] result = new String[length];
        for(int i = 0; i < length; i++) {
            result[i] = String.valueOf(this.grids[i].getBestPath());
        }
        return result;
    }

    public String[] solveNaive() {
        int length = this.grids.length;
        String[] result = new String[length];
        for(int i = 0; i < length; i++) {
            result[i] = String.valueOf(this.grids[i].getNaiveBestPath());
        }
        return result;
    }

    public Grid[] getGrids() {
        return this.grids;
    }
}
