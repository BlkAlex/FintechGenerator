package HumanGenerator.generators.localGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


class FileLoader {
    ArrayList<String> getListByFileName(String fileName) {
        InputStream isr = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (isr != null) {
            return readFromBufferReader(new InputStreamReader(isr));
        } else {
            System.out.println("Resource " + fileName + " not found");
            return new ArrayList<>();
        }
    }

    private ArrayList<String> readFromBufferReader(InputStreamReader fileReader) {
        ArrayList<String> linesFromFile = new ArrayList<>();

        BufferedReader reader = new BufferedReader(fileReader);
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty())
                    linesFromFile.add(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
            return linesFromFile;
        }

        try {
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            return linesFromFile;
        }
        return linesFromFile;
    }
}
