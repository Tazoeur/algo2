package be.unamur.info.algo2.Problem1;

public class Problem1 {
    private Sequence[] sequences;

    public Problem1(String content) throws Exception {
        this.readContent(content);
    }

    private void readContent(String content) throws Exception {
        if(content.equals("")) {
            throw new Exception("Empty content given");
        }
        String[] lines = content.split("\n");
        int line_number = lines.length; // to avoid the calculus of lines.length at each iteration
        if(line_number < 3) {
            throw new Exception("Not enough line for most trivial case");
        }
        int numberOfLines = 0;
        int numberOfShareHolder = 0;
        int numberOfSequence = 0;

        // here we are at line 1, we can get the number of sequences that are present in the given file
        if(!lines[0].matches("\\d+")) {
            throw new Exception("Information must be an integer");
        }
        this.sequences = new Sequence[Integer.valueOf(lines[0])];

        for(int i = 1; i< line_number; i++) {
            String line = lines[i];
            if(line.equals("")) {
                throw new Exception("Empty line");
            }
            numberOfLines++;

            // here we have to get the number of shareHolders that compose the next Sequence
            if(numberOfLines%2 == 0) {
                if(!line.matches("\\d+")) {
                    throw new Exception("Information must be a positive integer");
                }
                numberOfShareHolder = Integer.valueOf(line);
                continue;
            }

            // here we get the shareHolders
            if(numberOfLines%2 == 1) {
                this.sequences[numberOfSequence] = new Sequence(numberOfShareHolder, line.split(" "));
                numberOfSequence++; // have to keep track of sequences to add new Sequence at right index
                numberOfShareHolder = 0; // reset count of shareHolders
            }
        }
    }

    public Sequence[] getSequences() {
        return this.sequences;
    }

    /**
     * Solveur naif
     *
     * @return
     */
    public String[] solveNaive() {
        int length = this.sequences.length;
        String[] result = new String[length];
        for(int i = 0; i < length; i++) {
            result[i] = this.sequences[i].getNaiveMajorityShareHolder();
        }
        return result;
    }

    public String[] solve() {
        int length = this.sequences.length;
        String[] result = new String[length];
        for(int i = 0; i < length; i++) {
            result[i] = this.sequences[i].getMajorityShareHolder(0, sequences[i].getSize()-1);
        }
        return result;
    }

}
