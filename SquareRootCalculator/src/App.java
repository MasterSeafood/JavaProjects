import java.util.Scanner;
/**
 * SquareRootCalculator
 * 
 * This program calculates the square root of a number in the form a + b√c if possible
 */

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int a, b, c; //holds the 3 variables

        int x, y; //hold the primitive values of solution 

        //variables used to solve quadratic equation
        double qX; 
        int qA, qB, qC;
        
        //INTRODUCTION 
        System.out.println("Welcome to the SquareRootCalculator!");
        System.out.println("This calculator calculates the square root of the sum of integers and radicals in the form:");
        System.out.println("sqrt(a + bsqrt(c))");
        System.out.println("");
        System.out.println("Please enter integers for the following variables:");


        //PROMPTS
        System.out.print("a = ");
        a = input.nextInt();

        System.out.print("b = ");
        b = input.nextInt();
        
        System.out.print("c = ");
        c = input.nextInt();

        input.close();

        //SOLVING

        //check for any errors (a must be positive and c cannot be negative)
        if(a <= 0){
            System.out.println("Error:");
            System.out.println("a must be greater than 0");
            return;
        }else if(c < 0){
            System.out.println("Error:");
            System.out.println("c cannot be negative");
            return;
        }


        //assign quadratic coefficients 
        qA = 4;
        qB = -4*a;
        qC = b*b*c;

        //check discriminant if quadratic has real roots
        if(qB*qB - 4 * qA * qC < 0){
            System.out.println("Cannot solve. There are no solutions.");
            return;
        }

        //apply quadratic formula to find root, use other root if root is negative
        qX = (-(double)qB + Math.sqrt((double)qB*qB-4.0*qA*qC))/(2.0*qA);
        if(qX < 0){
            qX =(-(double)qB - Math.sqrt((double)qB*qB-4.0*qA*qC))/(2.0*qA);
        }

        //check if root is an integer
        if((int)qX == qX){
            x = (int)qX; //assign root to x
        }else{
            System.out.println("No integer solutions.");
            return;
        }


        //find y
        y = a - x;

        //pass primitive solution to be formatted, also pass a boolean based on the sign of b
        formatAndPrintSolution(x, y, b > 0);


        
    }

    public static void formatAndPrintSolution(int x, int y, boolean isAddRootY){
        //format the solution
        int iX = 1, rX = x, iY = 1, rY = y;

        //loop through biggest perfect squares from x to 2 to check if the radical can be simplified
        for (int i = (int) Math.sqrt(x); i >= 2; i--) {
            if(x % (i*i) == 0){
                iX = i;
                rX = x / (i*i);
                break;
            }
        }

        //loop through biggest perfect squares from y to 2 to check if the radical can be simplified
        for(int i = (int)Math.sqrt(y); i >= 2; i--) {
            if (y % (i*i) == 0){
                iY = i;
                rY = y / (i*i);
                break;
            }
        }

        //print out the solution
        System.out.println("Solution:");
        if(x != 0){ //if x is not 0
            if(rX == 1){ //if the radicand is 1, just print out the integer part in front of the radical
                System.out.print(iX); 
            }else{ //otherwise, print out the radical, not printing out the 1 if it's in front of the radical
                System.out.print((iX == 1) ? "√" + rX  : iX + "√" + rX); 

            }
        }


        if(y != 0){ //if y is not 0
            if(x != 0){ // if there was an x component, print a +/- depending on sign of b
                if(isAddRootY)
                    System.out.print(" + ");
                else
                    System.out.print(" - ");
                
            }
            if(rY == 1){ //if radicand is 1, don't need to print out radical
                System.out.print(iY);
            }else{ //otherwise, print out radical
                System.out.print((iY == 1) ? "√" + rY  : iY + "√" + rY);  

            }
        }

        //if both x and y are 0, print 0
        if(y == 0 && x == 0){
            System.out.print("0");
        }

        System.out.println("");        

        
    }

    
}
