package generator;

import java.io.*;

public class FileGeneretor {
    private static FileGeneretor instance;
    private FileGeneretor() {
    }
    public static FileGeneretor getInstance() {
        if(instance == null) {
            instance = new FileGeneretor();
        }
        return instance;
    }
    public void generate100Files(String folder){
        int counter = 0;
        while (counter < 100) {
            String fileName = folder + "/Number" + counter+".txt";
            try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                try(FileWriter fileWriter = new FileWriter(fileName);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    ContentGenerator.getInstance().generateContentForFile(bufferedWriter);
                    System.out.println("File number" + " "+counter+" was generated successfully.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            counter++;
        }
    }
}
