import java.util.*;

class guessnum {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int maxLimit = 10;
        System.out.println("Highest number of attempts are: " + maxLimit);
        int rand = r.nextInt(100) + 1;
        // Uncomment below line to see the randomly generated number for debugging
        // System.out.println(rand);
        
        System.out.println("\nGuess the number:");
        int n;
        int j = 1;
        
        while (j <= maxLimit) {
            n = sc.nextInt();
            if (n < rand) {
                System.out.println("The guessed number is lower than the random number.");
            } else if (n > rand) {
                System.out.println("The guessed number is higher than the random number.");
            } else {
                System.out.println("The guessed number is correct: " + n);
                if (j <= 1) {
                    System.out.println("That was quick! You scored: 100 marks.");
                } else if (j <= 4) {
                    System.out.println("That is good! You scored: 75 marks.");
                } else if (j <= 6) {
                    System.out.println("Not bad! You scored: 50 marks.");
                } else {
                    System.out.println("Congratulations!");
                }
                sc.close();
                return; // Exit the program correctly
            }
            System.out.println("The number of chances completed are: " + j);
            j++;
        }
        
        System.out.println("Chances are over. The correct number was " + rand + ".");
        sc.close();
    }
}
