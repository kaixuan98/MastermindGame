import java.util.Arrays;
import java.util.Random;

public class CodeGenerator {
    private final int maxCodeLength;


    public CodeGenerator(int maxCodeLength) {
        this.maxCodeLength = maxCodeLength;
    }

    public MastermindColor[] generate(int gameMode){
        MastermindColor[] secretCode = new MastermindColor[maxCodeLength];
        Random random = new Random();
        int upperBound = MastermindColor.values().length;
        int[] codeIdx = new int[maxCodeLength];

        if(gameMode == 2){
            for (int i = 0; i < maxCodeLength ; i++) {
                int randomIdx = random.nextInt(0,upperBound);
                codeIdx[i]  = randomIdx;
            }
        }

        if(gameMode == 1){
            for (int i = 0; i < maxCodeLength ; i++) {
                int[] searchSpace = Arrays.copyOfRange(codeIdx, 0,i);
                int randomIdx = random.nextInt(0,upperBound);
                while(contains(searchSpace, randomIdx)){
                    randomIdx = random.nextInt(0,upperBound);
                }
                codeIdx[i] = randomIdx;
            }
        }

        for (int i = 0; i < maxCodeLength; i++) {
            secretCode[i] = MastermindColor.values()[codeIdx[i]];
        }

        return secretCode;
    }

    private boolean contains(int[] arr, int target){
        for (int j : arr) {
            if (j == target) {
                return true;
            }
        }
        return false;
    }


}
