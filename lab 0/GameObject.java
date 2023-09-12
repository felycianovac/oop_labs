public class GameObject {
    private boolean isMoving;
    private int speed;
    private String color;

    public GameObject(boolean isMoving, int speed, String color) {
        this.isMoving = isMoving;
        this.speed = speed;
        this.color = color;
    }

    public void informState() {
        String state = isMoving ? "moving" : "standing";
        System.out.println("The object is " + state + " with a speed of " + speed + " and color " + color);
    }


}
