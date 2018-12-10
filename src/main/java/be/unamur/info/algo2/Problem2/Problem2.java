package be.unamur.info.algo2.Problem2;

public class Problem2 {
    private Grid[] grids;

    public Problem2(String content) {
        this.readContent(content);
    }

    private void readContent(String content) {
        String[] lines = content.split("\n");
        int number_of_lines = lines.length;
        int count_of_lines = 1; // since the first line is always read to know the number of grid

        int number_of_grid = Integer.parseInt(lines[0]);
        this.grids = new Grid[number_of_grid];
        int count_of_grid = 0;

        int count_of_line_in_grid = 0;
        int number_of_line_in_grid = 0;

        boolean next_is_new_grid = true;
        int[] size = new int[2];
        String[] str_size;
        String[] line_content = new String[0];
        for(int i = 1; i < number_of_lines; i++) {
            count_of_lines++;
            if(next_is_new_grid) {
                str_size = lines[i].split(" ");
                size[0] = Integer.valueOf(str_size[0]); // line
                size[1] = Integer.valueOf(str_size[1]); // column
                next_is_new_grid = false;
                number_of_line_in_grid = size[0];
                count_of_line_in_grid = 0;
                line_content = new String[size[1]];
                continue;
            }

            line_content[count_of_line_in_grid] = lines[i];
            count_of_line_in_grid++;
            // fill the lines array
            if(count_of_line_in_grid == number_of_line_in_grid) {
                this.grids[count_of_grid] = new Grid(size, line_content);
                count_of_grid++;
                next_is_new_grid = true;
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
