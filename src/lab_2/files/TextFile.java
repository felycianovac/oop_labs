package lab_2.files;

import java.util.Date;

public class TextFile extends Document {
    private int lineCount;
    private int characterCount;
    private int wordCount;
    private String content;

    public TextFile(String filename, Date creationDate, Date lastModified,
                    boolean changed, String extension, int lineCount,
                    int characterCount, int wordCount, String filePath) {
        super(filename, creationDate, lastModified, changed, extension, filePath);
        this.lineCount = lineCount;
        this.characterCount = characterCount;
        this.wordCount = wordCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Line count: " + lineCount);
        System.out.println("Character count: " + characterCount);
        System.out.println("Word count: " + wordCount);
    }
}
