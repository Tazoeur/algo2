package be.unamur.info.algo2.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String file_content;

    public Reader(String file_path) {
        try {
            this.readFile(file_path);
        } catch (IOException e) {
            this.file_content = null;
        }
    }

    public String getContent () {
        return this.file_content;
    }

    /**
     * snippet from https://cht.sh/java/read+file
     * slightly adapted to suit our needs
     *
     * @param file_path the path where the reader needs to read the file
     */
    private void readFile(String file_path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            this.file_content = sb.toString();
        } finally {
            br.close();
        }
    }
}
