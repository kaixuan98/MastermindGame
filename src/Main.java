import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MastermindGame game = new MastermindGame(scanner);
        game.start();




    }
}