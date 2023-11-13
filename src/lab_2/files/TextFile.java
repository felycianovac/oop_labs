package lab_2.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class TextFile extends Document {
    private int lineCount;
    private int characterCount;
    private int wordCount;

    public TextFile(String directoryPath, String filename, Date creationDate, Date lastModified) {
        super(directoryPath, filename, creationDate, lastModified);
        extractTxtInfo();
    }

    private void extractTxtInfo(){
        File file = new File(directoryPath+File.separator+filename);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                lineCount++;
                characterCount += line.length();
                String[] wordsInLine = line.split("\\s+");
                wordCount += wordsInLine.length;
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Line count: " + lineCount);
        System.out.println("Character count: " + characterCount);
        System.out.println("Word count: " + wordCount);
    }
}
