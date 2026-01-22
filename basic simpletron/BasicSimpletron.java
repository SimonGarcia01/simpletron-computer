import java.util.Scanner;

public class BasicSimpletron {

    //ATTRIBUTES
    //Extending the memory to 1000 locations
    private static double[] memory = new double[1000];
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
    private static final int EXPONENTIATION = 35;

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

            try{
                //Eventhough the arithmetic exceptions are not checked,
                //I wanted to handle it specifically to show the error message and practice exception handling
                executeInstruction();
            } catch (ArithmeticException e){
                System.out.println(e.getMessage());
                System.out.println("*** Simpletron execution terminated ***");
                //Close the scanner at the end regardless heh
                scanner.close();
                //End the program
                System.exit(0);
            }
        }
    }

    //Leaving this but I'm going to work this basic version with static methods
    public BasicSimpletron() {
        //Default Constructor
    }

    //This is where I load the program into memory
    public static void loadProgram(){
        System.out.println("*** Enter your program instructions ***");
        System.out.println("*** Remember to enter instructions from +1000 to +4399");
        System.out.println("*** To stop entering the program, type -99999 ***");

        double input;
    
        for(int i = 0; i < memory.length; i++){
            
            //Just to make sure the input is an integer or double
            try{
                System.out.printf("%02d ? ", i);
                input = scanner.nextDouble();

                //Break if the breaking point value is entered
                if(input == BREAK_POINT){
                    break;
                } 

                if(input < 1000 || input > 4399) {
                    System.out.println("*** Invalid input. Please enter a value between +1000 and +4399. ***");
                    //Consume inalid number so the scanner can continue
                    scanner.nextLine();
                    i--;
                    continue;
                }

            } catch (Exception e){
                System.out.println("*** Invalid input. Please enter an integer. ***");
                // consume the invalid token so the scanner can continue
                scanner.nextLine();
                i--;
                continue;
            }

            //Store in memory the input
            memory[i] = input;
        }
    }

    //This is where I execute the instructions
    private static void executeInstruction() throws ArithmeticException {
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
                //Check if dividing by zero first
                if(memory[operand] == 0){
                    throw new ArithmeticException("*** Error: Division by zero ***");
                }
                
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

            case EXPONENTIATION -> {
                //Raise the accumulator to the power of the value from memory
                accumulator = Math.pow(accumulator, memory[operand]);

                //Go to the next instruction
                instructionCounter++;
            }

            //Control Operations
            case BRANCH -> {
                //Change the instruction counter to the operand value to change location
                instructionCounter = operand;

                //No need to increment instruction counter
                // since we are changing its value directly
            }

            case BRANCHNEG -> {
                //Branch somewhere if the accumulator is < 0
                if(accumulator <0){
                    //Go to the operand location
                    instructionCounter = operand;
                } else {
                    //Go to the next instruction normally
                    instructionCounter++;
                }
            }

            case BRANCHZERO -> {
                //Branch somewhere if the accumulator is == 0
                if(accumulator == 0){
                    //Go to the operand location
                    instructionCounter = operand;
                } else {
                    //Go to the next instruction normally
                    instructionCounter++;
                }
            }
            case HALT -> {
                //Halt the program completely
                System.out.println("*** Simpletron execution terminated ***");

                //Close the scanner at the end
                scanner.close();

                //Show the final state of the machine
                System.out.println("REGISTERS:");
                System.out.println("accumulator          : " + String.format("%+05.2f", accumulator));
                System.out.println("instructionCounter   : " + String.format("%02d", instructionCounter));
                System.out.println("instructionRegister  : " + String.format("%+05d", instructionRegister));
                System.out.println("operationCode        : " + String.format("%02d", operationCode));

                //Display memory contents
                System.out.println("\nMEMORY CONTENTS:");
                System.out.println("*************************************************************");
                for(int i = 0; i < 10; i++){
                    System.out.printf("%6d", i);
                }

                System.out.println();

                for(int i = 0; i < memory.length; i++){
                    //to print the line numbers on the left of every 10 memory locations
                    if(i % 10 == 0){
                        System.out.printf("%02d ", i);
                    }
                    //To print the memory contents
                    System.out.printf(" %+05.2f", memory[i]);
                    
                    //Add a little spacing between every row of 10 locations
                    if((i + 1) % 10 == 0){
                        System.out.println();
                    }
                }

                System.out.println("*************************************************************");
                //Stop the program
                System.exit(0);
            }
        }
    }
}