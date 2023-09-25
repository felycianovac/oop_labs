package lab_0;
public class Ball {
    public boolean isBouncing;
    private int speed;
    private String color;

    public Ball(boolean isBouncing, int speed, String color) {
        this.isBouncing = isBouncing;
        this.speed = speed;
        this.color = color;
    }

    public void informState() {
        String state = isBouncing ? "bouncing" : "standing";
        String speedInfo = isBouncing ? " with a speed of " + speed + "m/s" : "";
        System.out.println("The ball is " + state + speedInfo + " and has color " + color);
    }

    public void separateActions(){
        System.out.println("----------------------------------------");
    }

}
