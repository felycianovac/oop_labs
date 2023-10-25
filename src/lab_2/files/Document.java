package lab_2.files;

import java.io.File;
import java.util.Date;

public class Document {
    protected  String filePath;

    protected String filename;
    protected Date creationDate;
    protected Date lastModified;


    public Document(String filePath, String filename,  Date creationDate, Date lastModified) {
        this.filePath = filePath;
        this.filename = filename;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
    }

    public Document(){

    }

    public static Document createDocument(String filePath,String filename,
                                           Date creationDate, Date lastModified){
        String extension = getFileExtension(filename);
        if(extension.equals("txt")){
            return new TextFile(filePath,filename,creationDate,lastModified);
        } else if(extension.equals("png") || extension.equals("jpg")){
            return new ImageFile(filename,creationDate,lastModified,filePath);
        } else if(extension.equals("java") || extension.equals("py")){
            return new ProgramFile(filePath,filename,creationDate,lastModified);
        }

        return null;
    }

    public String getFilename() {
        return filename;
    }

    public static String getFileExtension(String filename){
        int index = filename.lastIndexOf(".");
        if(index>0){
            return filename.substring(index+1);
        }
        return "";
    }

    public void info(){
        System.out.println("File Information");
        System.out.println("Filename: " + filename);
        System.out.println("Creation date: " + creationDate);
        System.out.println("Last modified: " + lastModified);
    }

}
