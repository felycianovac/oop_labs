package lab_0;
public class Main {
    public static void main(String[] args) {
        Ball obj1 = new Ball(true, 2, "red");
        Ball obj2 = new Ball(false, 0, "blue");

        Player player1 = new Player("Ana");
        Player player2 = new Player("Maxim");


        obj1.informState();
        obj2.informState();
        obj2.separateActions();

        player1.countBounces(10);
        player2.countBounces(0);

        player1.displayScore();
        player2.displayScore();
    }
}
