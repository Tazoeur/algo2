package be.unamur.info.algo2.Problem1;

public class Problem1 {
    private Sequence[] sequences;

    public Problem1(String content) {
        this.readContent(content);
    }

    private void readContent(String content) {
        String[] lines = content.split("\n");

        int numberOfLines = 0;
        int numberOfShareHolder = 0;
        int numberOfSequence = 0;

        int line_number = lines.length; // to avoid the calculus of lines.length at each iteration
        for(int i = 0; i< line_number; i++) {
            String line = lines[i];
            numberOfLines++;

            // here we are at line 1, we can get the number of sequences that are present in the given file
            if(numberOfLines==1) {
                this.sequences = new Sequence[Integer.valueOf(line)];
                continue;
            }

            // here we have to get the number of shareHolders that compose the next Sequence
            if(numberOfLines%2 == 0) {
                numberOfShareHolder = Integer.valueOf(line);
                continue;
            }

            // here we get the shareHolders
            if(numberOfLines%2 == 1) {
                try {
                    this.sequences[numberOfSequence] = new Sequence(numberOfShareHolder, line.split(" "));
                    numberOfSequence++; // have to keep track of sequences to add new Sequence at right index
                } catch (Exception e) {
                    System.out.println("Error while adding a Sequence (" + line + ")");
                }
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
            result[i] = this.sequences[i].getMajorityShareHolder();
        }
        return result;
    }

}
