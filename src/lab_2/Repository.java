package lab_2;

import lab_2.files.Document;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private List<Document> documents;
    private String filePath;
    public Repository(String filePath, List<Document> documents){
        this.documents = documents;

        this.filePath = filePath;
    }

    public void info(String filename) {

        List<Document> matchingDocs = documents.stream()
                .filter(document -> document.getFilename().equals(filename))
                .collect(Collectors.toList());

        if(matchingDocs.isEmpty()){
            System.out.println("File not found!");
        } else {
            for(Document document: matchingDocs){
                document.info();
            }
        }
    }

}
