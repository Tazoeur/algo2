package be.unamur.info.algo2.Problem3;

public class Problem3 {
    private Graph[] graphs;

    public Problem3(String content) throws Exception {
        this.readContent(content);
    }

    private void readContent(String content) throws Exception {
        String[] lines = content.split("\n");

        // check minimum line requirement
        if(lines.length < 3) {
            throw new Exception("Not enough lines for most trivial case");
        }

        // here we are at line 1, we can get the number of cases that are present in the given file
        if(!lines[0].matches("^\\d+$")) {
            throw new Exception("Information must be an integer");
        }
        int nbr_cases = Integer.valueOf(lines[0]);

        // check that the number of line correspond to the number of cases
        if(nbr_cases != (lines.length -1) /2) {
            throw  new Exception("There is an inconsistency between the file's line's count and the first line number");
        }

        this.graphs = new Graph[nbr_cases];
        int count_cases = 0;

        int nbr_degrees = 0;
        for(int i = 1; i < lines.length; i++) {
            // even lines gives the number of degrees that we will fetch
            if(i%2 != 0) {
                if(!lines[i].matches("^\\d+$")) {
                    throw new Exception("Information must be an integer (line "+ String.valueOf(i+1) +")");
                }
                nbr_degrees = Integer.valueOf(lines[i]);
            } else {
                String[] line = lines[i].split(" ");
                // check count of informations
                if(nbr_degrees != line.length) {
                    throw new Exception("The line " + String.valueOf(i) + " does not match the announced number of degrees (" + String.valueOf(nbr_degrees) + ")");
                }

                // create a Graph with the given degrees values
                int temp_graph[] = new int[nbr_degrees];
                for(int j = 0; j < line.length; j++) {
                    if(!line[j].matches("^\\d+$")) {
                        throw new Exception("Information must be an integer (line "+ String.valueOf(i+1) +", column " + String.valueOf(j+1) +")");
                    }
                    temp_graph[j] = Integer.valueOf(line[j]);
                }
                this.graphs[count_cases] = new Graph(temp_graph);
                count_cases++;
            }
        }
    }

    public String[] solveNaive() {
        int length = this.graphs.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = String.valueOf(this.graphs[i].isNaiveGraphic(this.graphs[i].getDegree()));
        }
        return result;
    }


    public String[] solve() {
        int length = this.graphs.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = String.valueOf(this.graphs[i].solveEG(this.graphs[i].getDegreeSize()));
        }
        return result;
    }

}
