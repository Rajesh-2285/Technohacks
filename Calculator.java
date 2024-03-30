import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome to the Calculator!");
            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();
            
            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();
            
            System.out.println("Select operation (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            
            double result;
            switch(operator) {
                case '+':
                    result = num1 + num2;
                    System.out.println("Result: " + result);
                    break;
                case '-':
                    result = num1 - num2;
                    System.out.println("Result: " + result);
                    break;
                case '*':
                    result = num1 * num2;
                    System.out.println("Result: " + result);
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error! Division by zero is not allowed.");
                    }
                    break;
                default:
                    System.out.println("Error! Invalid operation.");
            }
            
            System.out.println("Do you want to perform another calculation? (yes/no)");
            String choice = scanner.next();
            if (!choice.equalsIgnoreCase("yes")) {
                System.out.println("Goodbye!");
                break;
            }
        }
        
        scanner.close();
    }
}
