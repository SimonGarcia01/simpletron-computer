import java.util.Scanner;

public class BasicSimpletron {

    //ATTRIBUTES
    private static int[] memory = new int[100];
    private static double accumulator = 0.0;
    private static int instructionCounter = 0;
    private static int operationCode = 0;
    private static int operand = 0;
    private static int instructionRegister = 0;
    //OPERATION CODES
    //Input and Output
    private static final int READ = 10;
    private static final int WRITE = 11;

    //Load and Store
    private static final int LOAD = 20;
    private static final int STORE = 21;

    //Arithmethic Operations
    private static final int ADD = 30;
    private static final int SUBTRACT = 31;
    private static final int DIVIDE = 32;
    private static final int MULTIPLY = 33;
    private static final int MODULO = 34;

    //Control Operations
    private static final int BRANCH = 40;
    private static final int BRANCHNEG = 41;
    private static final int BRANCHZERO = 42;
    private static final int HALT = 43;

    //METHODS

    //Main Method
    public static void main(String[] args) {
        System.out.println("*** Welcome to Basic Simpletron! ***");
        System.out.println("*** Please enter your program one instruction (or data word) at a time. ***");
        System.out.println("*** I will display the location number and a question mark (?). ***");
        System.out.println("*** You then type the word for that location. ***");
        System.out.println("*** Type -99999 to stop entering your program. ***");


        loadProgram();

        System.out.println("*** Program loading completed ***");
        System.out.println("*** Program execution begins  ***");

        for(int i : memory){
            System.out.printf("%+05d%n", i);
        }
    }

    //Leaving this but I'm going to work this basic version with static methods
    public BasicSimpletron() {
        //Default Constructor
    }

    public static void loadProgram(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Enter your program instructions ***");
        for(int i = 0; i < memory.length; i++){
            System.out.printf("%02d ? ", i);
            int input = scanner.nextInt();

            if(input == -99999){
                break;
            } else if(input < 1000 || input > 4300){
                System.out.println("*** Invalid input. Please enter a value between -9999 and +9999 ***");
                i--;
                continue;
            }

            memory[i] = input;
        }

        scanner.close();
    }
}