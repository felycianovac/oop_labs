public class Player {
    private String name;
    private int bounces;

    public Player(String name){
        this.name = name;
        this.bounces = 0;
    }

    public void countBounces(int bounce){
        this.bounces += bounce;
    }

    public void displayScore(){
        System.out.println("Player " + name + " has bounced a ball " + bounces + " times");
    }
}
