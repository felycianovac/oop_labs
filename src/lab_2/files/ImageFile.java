package lab_2.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ImageFile extends Document{
    private int width;
    private int height;
    private int resolution;

    public ImageFile(String filename, Date creationDate, Date lastModified, String directoryPath) {
        super(directoryPath, filename, creationDate, lastModified);
        extractImageInfo();

    }

    public void extractImageInfo(){
        File file = new File(directoryPath+File.separator+filename);
        try{
            BufferedImage image = ImageIO.read(file);
            if(image!=null){
                width = image.getWidth();
                height = image.getHeight();
                resolution = width * height;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Resolution: " + resolution);
    }
}
