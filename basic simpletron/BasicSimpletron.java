public class BasicSimpletron {

    //ATTRIBUTES
    private int[] memory = new int[100];
    private double accumulator = 0.0;
    private int instructionCounter = 0;
    private int operationCode = 0;
    private int operand = 0;

    //OPERATION CODES
    //Input and Output
    private final int READ = 10;
    private final int WRITE = 11;

    //Load and Store
    private final int LOAD = 20;
    private final int STORE = 21;

    //Arithmethic Operations
    private final int ADD = 30;
    private final int SUBTRACT = 31;
    private final int DIVIDE = 32;
    private final int MULTIPLY = 33;
    private final int MODULO = 34;

    //Control Operations
    private final int BRANCH = 40;
    private final int BRANCHNEG = 41;
    private final int BRANCHZERO = 42;
    private final int HALT = 43;

    //METHODS

    //Main Method
    public static void main(String[] args) {
        BasicSimpletron simpletron = new BasicSimpletron();
        System.out.println("*** Welcome to Basic Simpletron! ***");
        System.out.println("*** Please enter your program one instruction (or data word) at a time. ***");
        System.out.println("*** I will display the location number and a question mark (?). ***");
        System.out.println("*** You then type the word for that location. ***");
        System.out.println("*** Type -99999 to stop entering your program. ***");
    }

    public BasicSimpletron() {
        //Default Constructor
    }
}