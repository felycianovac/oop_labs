public class Main {
    public static void main(String[] args) {
        GameObject obj1 = new GameObject(true, 10, "red");
        GameObject obj2 = new GameObject(false, 0, "blue");

        obj1.informState();
        obj2.informState();
    }
}
