package lab_2.files;

import java.io.*;
import java.util.Date;

public class ProgramFile extends Document implements Serializable {
    private int lineCount;
    private int classCount;
    private int methodCount;

    public ProgramFile(String directoryPath, String filename, Date creationDate, Date lastModified) {
        super(directoryPath,filename, creationDate, lastModified);
        extractProgramInfo();

    }

    private void extractProgramInfo() {
        String extension = getFileExtension(filename);
        File file = new File(directoryPath + File.separator + filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean inSingleLineComment = false;
            boolean inMultiLineComment = false;
            boolean inCommentBlock = false;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                if(extension.equals("py")){
                    if(!inMultiLineComment && !inSingleLineComment) {
                        if (line.startsWith("class")) {
                            classCount++;
                        } else if (line.contains("def")) {
                            methodCount++;
                        }
                    }
                }
                else if(extension.equals("java")) {
                    if(!inCommentBlock) {
                        if (line.trim().startsWith("public class") || line.trim().startsWith("class")) {
                            classCount++;
                        } else if (line.trim().startsWith("public") || (line.trim().startsWith("private")) && line.contains("(") && line.contains(")")) {
                            methodCount++;
                        }
                    }
                }

                if(line.contains("#")){
                    inSingleLineComment = true;
                }
                if (line.matches("^[\\s]*['\"]{3}.*['\"]{3}.*")) {
                    inMultiLineComment = !inMultiLineComment;
                }
                if (line.contains("/*" )){
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
