package lab_2.files;

import java.util.Date;

public class Document {
    private  String filePath;
    private String filename;
    private String extension;
    private boolean changed;
    private Date creationDate;
    private Date lastModified;

    public Document(String filename, Date creationDate,
                    Date lastModified, boolean changed, String extension
                    ,String filePath){
        this.filename = filename;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
        this.changed = changed;
        this.extension = extension;
        this.filePath = filePath;
    }
    public Document(){

    }


    public String getFilename() {
        return filename;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isChanged() {
        return changed;
    }

    public String getFilePath(){
        return filePath;
    }

    public void info(){
        System.out.println("File Information");
        System.out.println("Filename: " + filename);
        System.out.println("Extension: " + extension);
        System.out.println("Changed: " + (changed ? "Yes" : "No"));
        System.out.println("Creation date: " + creationDate);
        System.out.println("Last modified: " + lastModified);
    }
}
