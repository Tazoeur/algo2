package be.unamur.info.algo2.Problem2;


public class Problem2 {
    private Grid[] grids;
    int number_of_grid = 0;

    public Problem2(String content) throws Exception {
        this.readContent(content);
    }

    private void readContent(String content) throws Exception {
        if(content.equals("") || content.equals("0")) {
            throw new Exception("Empty content given");
        }
        
    	String[] lines = content.split("\n");
        int number_of_lines = lines.length;
        if(number_of_lines < 3) {
            throw new Exception("Not enough line for most trivial case");
        }

        // check that first line is positive integer
        if(!lines[0].matches("^\\d+$")) {
            throw new Exception("Information must be an integer");
        }
        try {
            number_of_grid = Integer.valueOf(lines[0]);
        } catch (Exception e) {
        	throw new Exception("not a parsable number of grid");
        }
        if (number_of_grid <= 0 ) {
            throw new Exception("Not a valid number of grid");
        }

        //
        this.grids = new Grid[number_of_grid];
        int count_of_grid = 0;

        int count_of_line_in_grid = 0;
        int number_of_line_in_grid = 0;
        int number_of_char_in_line = 0;

        boolean next_is_new_grid = true;
        int[] size = new int[2];
        String[] str_size;
        String[] line_content = new String[0];
        for(int i = 1; i < number_of_lines; i++) {
            if(next_is_new_grid) {
                if(!lines[i].matches("^\\d+\\s\\d+$")) {
                    throw new Exception("Grid does not respect size definition");
                }
                str_size = lines[i].split(" ");
                try {
                	size[0] = Integer.valueOf(str_size[0]); // line
                	size[1] = Integer.valueOf(str_size[1]); // column
                } catch (Exception e) {
                	throw new Exception("not a parsable number of grid line or col");
                } 
                if (size[0] <= 0 || size[1] <= 0) {
                    throw new Exception("Grid "+count_of_grid+" is not a valid Grid");
                }
                next_is_new_grid = false;
                number_of_line_in_grid = size[0];
                count_of_line_in_grid = 0;
                number_of_char_in_line = size[1];
                line_content = new String[number_of_line_in_grid];
                continue;
            }
            if(!lines[i].matches("^(0|T|#)+$")) {
                throw new Exception("Line " + i + " contains illegal characters (" + lines[i] + ")");
            }
            if(lines[i].toCharArray().length != number_of_char_in_line) {
                throw new Exception("Line " + i + " does not respect the characters count");
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
