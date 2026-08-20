

public class CodeGenerator {
    private final MastermindColor[] secretCode = new MastermindColor[4];

    public CodeGenerator() {

    }

    public MastermindColor[] generate(){
        return new MastermindColor[]{MastermindColor.RED, MastermindColor.GREEN, MastermindColor.BLUE, MastermindColor.ORANGE};
    }
}
