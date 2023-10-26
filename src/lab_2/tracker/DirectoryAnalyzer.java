package lab_2.tracker;

import lab_2.files.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirectoryAnalyzer {
    private String DIRECTORY_PATH;

    public DirectoryAnalyzer(String DIRECTORY_PATH){
        this.DIRECTORY_PATH = DIRECTORY_PATH;
    }

    public List<Document> extractDirectoryFiles(){

        List<Document> documents = new ArrayList<>();
        File directory = new File(DIRECTORY_PATH);
        if(directory.exists() && directory.isDirectory()){
            File[] files = directory.listFiles();
            if(files!=null){
                for(File file:files){
                    if(file.isFile()){
                        Path filePath = file.toPath();
                        try{
                            BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
                            Date creationDate = new Date(attributes.creationTime().toMillis());
                            Date lastModified = new Date(attributes.lastModifiedTime().toMillis());
                            String filename = file.getName();
                            Document document=Document.createDocument(DIRECTORY_PATH,file.getName(),creationDate,lastModified);
                            documents.add(document);
                        } catch(IOException e){
                            e.printStackTrace();

                        }
                    }
                }
            }
        }
        return documents;
    }
}