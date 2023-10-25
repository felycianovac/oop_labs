package lab_2.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class ProgramFile extends Document{
    private int lineCount;
    private int classCount;
    private int methodCount;

    public ProgramFile(String filePath, String filename, Date creationDate, Date lastModified) {
        super(filePath,filename, creationDate, lastModified);
        extractProgramInfo();

    }

    private void extractProgramInfo() {
        //TODO: implement the same for .py
        File file = new File(filePath + File.separator + filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean inCommentBlock = false;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                //TODO: improve class count
                if (!inCommentBlock) {
                    if (line.trim().contains("class")) {
                        classCount++;
                    } else if (line.trim().startsWith("public") || (line.trim().startsWith("private")) && line.contains("(") && line.contains(")")) {
                        methodCount++;
                    }
                }

                // Check for multi-line comments
                if (line.contains("/*")) {
                    inCommentBlock = true;
                }
                if (line.contains("*/")) {
                    inCommentBlock = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Line count: " + lineCount);
        System.out.println("Class count: " + classCount);
        System.out.println("Method count: " + methodCount);
    }
}
