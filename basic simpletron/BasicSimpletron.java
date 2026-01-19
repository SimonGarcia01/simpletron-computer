import java.util.Scanner;

public class BasicSimpletron {

    //ATTRIBUTES
    private static double[] memory = new double[100];
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
    
    private static final int BREAK_POINT = -99999;

    //Other attributes
    private static Scanner scanner = new Scanner(System.in);

    //METHODS

    //Main Method
    public static void main(String[] args) {
        System.out.println("*** Welcome to Basic Simpletron! ***");
        System.out.println("*** Please enter your program one instruction (or data word) at a time. ***");
        System.out.println("*** I will display the location number and a question mark (?). ***");
        System.out.println("*** You then type the word for that location. ***");
        System.out.println("*** Type -99999 to stop entering your program. ***");

        //First we load the program into memory
        loadProgram();

        System.out.println("*** Program loading completed ***");
        System.out.println("*** Program execution begins  ***");

        //Now we start the actual execution of the program

        while(true){
            //Fetch the instruction
            instructionRegister = (int) memory[instructionCounter];

            //Decode the instruction
            operationCode = instructionRegister / 100;
            operand = instructionRegister % 100;

            //Now decide what to do based on the code
            executeInstruction();
        }
        
        //Close the scanner at the end
        scanner.close();
    }

    //Leaving this but I'm going to work this basic version with static methods
    public BasicSimpletron() {
        //Default Constructor
    }

    //This is where I load the program into memory
    public static void loadProgram(){
        System.out.println("*** Enter your program instructions ***");

        double input;
    
        for(int i = 0; i < memory.length; i++){
            
            //Just to make sure the input is an integer or double
            try{
                System.out.printf("%02d ? ", i);
                input = scanner.nextDouble();
            } catch (Exception e){
                System.out.println("*** Invalid input. Please enter an integer. ***");
                // consume the invalid token so the scanner can continue
                scanner.nextLine();
                i--;
                continue;
            }

            //Break if the breaking point value is entered
            if(input == BREAK_POINT){
                break;
            } 

            //Store in memory the input
            memory[i] = input;
        }
    }

    //This is where I execute the instructions
    private static void executeInstruction() {
        switch(operationCode){

            //Input and Output
            case READ -> {
                System.out.print("Enter a value: ");
                double value;

                while (true) {
                    try {
                        value = scanner.nextDouble();
                        break;
                    } catch (Exception e) {
                        System.out.println("*** Invalid input. Please enter a number. ***");
                        scanner.nextLine();
                    }
                }

                //Store the value if it is valid
                memory[operand] = value;
                //Go to the next instruction
                instructionCounter++;
            }

            case WRITE -> {
                System.out.println("Value at memory location " + operand + ": " + memory[operand]);
                
                //Go to the next instruction
                instructionCounter++;
            }
            
            //Load and store
            case LOAD -> {
                //Load the value from memoy into the accumulator
                accumulator = memory[operand];

                //Go to the next instruction
                instructionCounter++;
            }

            case STORE -> {
                //Store the value of the accumulator into memory
                memory[operand] = accumulator;

                //Go to the next instruction
                instructionCounter++;
            }

            //Arithmethic Operations
            case ADD -> {
                //Add the value from memory to the accumulator
                accumulator += memory[operand];

                //Go to the next instruction
                instructionCounter++;
            } 

            case SUBTRACT -> {
                //Substract the value from memory to the accumulator
                accumulator -= memory[operand];

                //Go to the next instruction
                instructionCounter++;
            }

            case DIVIDE -> {
                //Divide the value from memory to the accumulator
                accumulator /= memory[operand];

                //Go to the next instruction
                instructionCounter++;
            }
        

            case MULTIPLY -> {
                //Multiply the value from memory to the accumulator
                accumulator *= memory[operand];

                //Go to the next instruction
                instructionCounter++;
            }

            case MODULO -> {
                //Do the modulus operation on the value from memory to the accumulator
                accumulator %= memory[operand];

                //Go to the next instruction
                instructionCounter++;
            }

            //Control Operations
            case BRANCH -> {}
            case BRANCHNEG -> {}
            case BRANCHZERO -> {}
            case HALT -> {}
        }
    }
}