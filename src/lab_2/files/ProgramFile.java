package lab_2.files;

import java.util.Date;

public class ProgramFile extends Document{
    private int lineCount;
    private int classCount;
    private int methodCount;

    public ProgramFile(String filename, Date creationDate, Date lastModified,
                       boolean changed, String extension, int lineCount,
                       int classCount, int methodCount, String filePath) {
        super(filename, creationDate, lastModified, changed, extension, filePath);
        this.lineCount = lineCount;
        this.classCount = classCount;
        this.methodCount = methodCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getClassCount() {
        return classCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Line count: " + lineCount);
        System.out.println("Class count: " + classCount);
        System.out.println("Method count: " + methodCount);
    }
}
