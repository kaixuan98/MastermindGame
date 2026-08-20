public class Evaluator {
    private final int maxAttempts;
    public Evaluator(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public EvaluatorResult eval(MastermindColor[] guess, MastermindColor[] secretCode){
        int black = 0;
        int white = 0;
        boolean[] secretUsed = new boolean[maxAttempts];
        boolean[] guessMatched = new boolean[maxAttempts];

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

        return new EvaluatorResult(black,white);
    }
}
