import java.util.Scanner;
import java.lang.Math;

public class ScientificCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("┌──────────────────────────────┐");
        System.out.println("│   Scientific Calculator      │");
        System.out.println("└──────────────────────────────┘");
        
        while (running) {
            displayMenu();
            System.out.print("\nEnter choice (0-20): ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }
            
            if (choice == 0) {
                running = false;
                System.out.println("\nThank you for using the calculator. Goodbye!");
                continue;
            }
            
            try {
                performCalculation(choice, scanner);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n┌─────────────── Operations ───────────────┐");
        System.out.println("│ 1. Addition          11. Log₁₀           │");
        System.out.println("│ 2. Subtraction       12. Logₑ (ln)       │");
        System.out.println("│ 3. Multiplication    13. Log base(x)     │");
        System.out.println("│ 4. Division          14. 10^x            │");
        System.out.println("│ 5. Power (xʸ)        15. e^x             │");
        System.out.println("│ 6. Square Root       16. Sin (degrees)   │");
        System.out.println("│ 7. Cube Root         17. Cos (degrees)   │");
        System.out.println("│ 8. Factorial         18. Tan (degrees)   │");
        System.out.println("│ 9. Percentage (%)    19. Sinh            │");
        System.out.println("│10. Modulus (%)      20. Memory functions │");
        System.out.println("│ 0. Exit                                     │");
        System.out.println("└─────────────────────────────────────────────┘");
    }
    
    private static void performCalculation(int choice, Scanner scanner) throws Exception {
        double num1 = 0, num2 = 0;
        double result = 0;
        
        if (choice >= 1 && choice <= 14) {
            System.out.print("Enter number" + (choice <= 10 || choice == 13 ? "s" : "") + ": ");
            
            if (choice >= 1 && choice <= 5 || choice == 13) {
                System.out.print("First number  → ");
                num1 = Double.parseDouble(scanner.nextLine());
                System.out.print("Second number → ");
                num2 = Double.parseDouble(scanner.nextLine());
            } else {
                System.out.print("→ ");
                num1 = Double.parseDouble(scanner.nextLine());
            }
        } else if (choice >= 16 && choice <= 18) {
            System.out.print("Enter angle in degrees → ");
            num1 = Double.parseDouble(scanner.nextLine());
        } else if (choice >= 19) {
            System.out.print("Enter number → ");
            num1 = Double.parseDouble(scanner.nextLine());
        }
        
        switch (choice) {
            // Basic operations
            case 1: result = num1 + num2; break;
            case 2: result = num1 - num2; break;
            case 3: result = num1 * num2; break;
            case 4: 
                if (num2 == 0) throw new Exception("Division by zero!");
                result = num1 / num2; 
                break;
            case 5: result = Math.pow(num1, num2); break;
            
            // Roots
            case 6: 
                if (num1 < 0) throw new Exception("Cannot calculate square root of negative number");
                result = Math.sqrt(num1); 
                break;
            case 7: result = Math.cbrt(num1); break;
            
            // Others
            case 8: result = factorial((long)num1); break;
            case 9: result = num1 * num2 / 100; break;
            case 10: result = num1 % num2; break;
            
            // Logarithms & Exponentials
            case 11: result = Math.log10(num1); break;
            case 12: result = Math.log(num1); break;
            case 13: result = Math.log(num1) / Math.log(num2); break;
            case 14: result = Math.pow(10, num1); break;
            case 15: result = Math.exp(num1); break;
            
            // Trigonometry (in degrees)
            case 16: result = Math.sin(Math.toRadians(num1)); break;
            case 17: result = Math.cos(Math.toRadians(num1)); break;
            case 18: result = Math.tan(Math.toRadians(num1)); break;
            
            // Hyperbolic
            case 19: result = Math.sinh(num1); break;
            
            case 20:
                System.out.println("Memory functions not implemented in this version.");
                return;
                
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        // Nice output formatting
        if (choice >= 16 && choice <= 18) {
            System.out.printf("Result: %.6f%n", result);
        } else if (choice == 8) {
            System.out.printf("%,d! = %,d%n", (long)num1, (long)result);
        } else {
            System.out.printf("Result: %s%n", formatResult(result));
        }
    }
    
    private static long factorial(long n) {
        if (n < 0) throw new IllegalArgumentException("Factorial of negative number is undefined");
        if (n > 20) throw new IllegalArgumentException("Number too large for long factorial");
        
        long fact = 1;
        for (long i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    
    private static String formatResult(double value) {
        if (value == (long)value) {
            return String.format("%,d", (long)value);
        }
        return String.format("%,.8f", value)
                .replaceAll("\\.?0+$", "");
    }
}