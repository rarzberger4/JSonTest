package TestingVarCode;

import java.io.FileWriter;
import java.io.IOException;

public class WriteCsv {

    public static void writeCSV() throws IOException {
        FileWriter writer = new FileWriter("test.csv");

        writer.append("player1");
        writer.append(",");
        writer.append((char) 14);
        writer.append(System.lineSeparator());

        writer.flush();
        writer.close();
    }


    public static void main(String[] args) throws IOException {
        writeCSV();
    }

}
