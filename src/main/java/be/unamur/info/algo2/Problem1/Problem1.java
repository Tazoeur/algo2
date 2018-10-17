package be.unamur.info.algo2.Problem1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    private Sequence[] sequences;

    public void readFile(String file_path) {
        List<String> list = new ArrayList<String>();
        File file = new File(file_path);
        BufferedReader reader = null;
        String line = null;
        int numberOfLines = 0;
        int numberOfShareHolder = 0;
        int numberOfSequence = 0;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

    }

    public Sequence[] getSequences() {
        return this.sequences;
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
