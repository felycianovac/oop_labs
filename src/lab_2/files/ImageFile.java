package lab_2.files;

import java.util.Date;

public class ImageFile extends Document{
    private int width;
    private int height;
    private int resolution;

    public ImageFile(String filename, Date creationDate, Date lastModified,
                     boolean changed, String extension, int width,
                     int height, int resolution, String filePath) {
        super(filename, creationDate, lastModified, changed, extension, filePath);
        this.width = width;
        this.height = height;
        this.resolution = resolution;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Resolution: " + resolution);
    }
}
