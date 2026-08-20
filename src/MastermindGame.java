import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MastermindGame {
    private static final int MAX_ATTEMPTS = 12;
    private static final int MAX_CODE_LENGTH = 4;
    private MastermindColor[] secretCode = new MastermindColor[MAX_CODE_LENGTH];
    private String playerName = "";
    private int gameMode = 0;
    private int attempts = 0;
    private final Scanner scanner;


    public MastermindGame(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start(){
        displayWelcome();
        playerName = getPlayerName();
        gameMode = selectGameMode();
        generateCode();
        String gameStatus = "playing";

        while(attempts < MAX_ATTEMPTS && gameStatus.equals("playing")){
            System.out.print("\nEnter your guess:");
            String rawGuess = scanner.nextLine();
            MastermindColor[] formatedGuess = formateGuess(rawGuess);
            EvalutorResult result = eval(formatedGuess);

            if(result.black == 4){
                gameStatus = "win";
            }else{
                String feedback = displayResult(result);
                System.out.print(feedback);
            }
            attempts++;
            }

            if(gameStatus.equals("playing")){
                gameStatus="lose";
            }
            end(gameStatus);
    }

    private void displayWelcome(){
        System.out.println("""
        ========================================
                WELCOME TO MASTERMIND!
        ========================================
        
        Can you crack the secret code?
        """);
        System.out.println("You have 12 attempts to guess the 4-color code.");
        System.out.println();
        System.out.println("Available Colors:");
        System.out.println("RED, BLUE, GREEN, YELLOW, PURPLE, ORANGE");
        System.out.println();
        System.out.println("Enter your guess as comma-separated colors.");
        System.out.println("Example: RED,BLUE,GREEN,YELLOW");
        System.out.println();
        System.out.println("Good Luck!");
    }

    private String getPlayerName(){
        System.out.print("Enter your name:");
        return scanner.nextLine().trim();
    }

    private int selectGameMode(){
        System.out.println("All game mode");
        System.out.println("1. Normal Mode (no duplicates) - enter 1");
        System.out.println("2. Hard Mode (allow duplicates) - enter 2");
        System.out.print("Enter your game mode:");
        int gameMode = 0;

        while(gameMode < 1 || gameMode > 2 ) {
            String modeStr = scanner.nextLine().trim();
            try {
                gameMode = Integer.parseInt(modeStr);
                    if (gameMode > 2) {
                        System.out.printf("Not valid game mode %d. Please only select 1 or 2.\n", gameMode);
                        System.out.print("Enter your game mode:");
                    }
            } catch (NumberFormatException e) {
                System.out.printf("Not valid game mode: %s. Please only select 1 or 2.\n", modeStr);
                System.out.print("Enter your game mode:");
            }
        }
        return gameMode;
    }

    private void generateCode(){
        Random random = new Random();
        int upperBound = MastermindColor.values().length;
        int[] codeIdx = new int[MAX_CODE_LENGTH];

        if(gameMode == 2){
            for (int i = 0; i < MAX_CODE_LENGTH ; i++) {
                int randomIdx = random.nextInt(0,upperBound);
                codeIdx[i]  = randomIdx;
            }
        }

        if(gameMode == 1){
            for (int i = 0; i < MAX_CODE_LENGTH ; i++) {
                int[] searchSpace = Arrays.copyOfRange(codeIdx, 0,i);
                int randomIdx = random.nextInt(0,upperBound);
                while(contains(searchSpace, randomIdx)){
                    randomIdx = random.nextInt(0,upperBound);
                }
                codeIdx[i] = randomIdx;
            }
        }

        for (int i = 0; i < MAX_CODE_LENGTH; i++) {
            secretCode[i] = MastermindColor.values()[codeIdx[i]];
        }
    }

    private MastermindColor[] formateGuess(String rawGuess){
        String[] splitGuess = rawGuess.split(",");
        MastermindColor[] formatedGuess = new MastermindColor[4];

        for(int i = 0 ; i < splitGuess.length; i++){
            MastermindColor color = MastermindColor.valueOf(splitGuess[i].trim().toUpperCase());
            formatedGuess[i] = color;
        }
        return formatedGuess;
    }

    private EvalutorResult eval(MastermindColor[] guess){
        int black = 0;
        int white = 0;
        boolean[] secretUsed = new boolean[4];
        boolean[] guessMatched = new boolean[4];

        // find black first
        for (int i = 0; i < guess.length; i++) {
            if(guess[i].equals(secretCode[i])){
                black++;
                secretUsed[i] = true;
                guessMatched[i] = true;
            }
        }

        // find the white
        for (int i = 0; i < guess.length; i++) {
            if(!guessMatched[i]) { // only check the unused guess
                for (int j = 0; j < secretCode.length; j++) {
                    if(!secretUsed[j]){ // check only the unsued secret
                        if(guess[i].equals(secretCode[j])){
                            white++;
                            secretUsed[j] = true;
                            guessMatched[i] = true;
                            break;
                        }
                    }
                }
            }
        }

        return new EvalutorResult(black,white);
    }

    private String displayResult(EvalutorResult result){
        return "Black: " + result.black + "\nWhite: " + result.white;
    }

    private boolean contains(int[] arr, int target){
        for (int j : arr) {
            if (j == target) {
                return true;
            }
        }
        return false;
    }

    private void end(String gameStatus){
        if(gameStatus.equals("lose")){
            System.out.printf("Good game, %s !\n", playerName);
            System.out.printf("The code is %s\n", Arrays.toString(secretCode));
        }else if(gameStatus.equals("win")){
            System.out.printf("You win. Total attempts:%d", attempts);
        }
    }







}
