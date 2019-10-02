import java.util.*;

public class Farkle {

    static Random rand;
    static Scanner scan;

    public static void main(String[] args) {
        
        rand = new Random();
        scan = new Scanner(System.in);

        int[] player1Turn = roll(6);

    }


    public static int[] roll(int dice) {

        int[] rolls = new int[dice];

        for (int i = 0; i < dice; i++) {

            rolls[i] = rand.nextInt(6) + 1;
            
        }

        System.out.println("Your rolls: " + Arrays.toString(rolls));

        return rolls;

    }


    public static int[] evaluateTurn(int[] rolls) {

        boolean farkle = false;
        boolean threeOfAKind = false;
        int fiveCount = 0;
        int oneCount = 0;
        int[] threeOfAKindGroups = new int[2];
        int[] sortedRolls = rolls;
        Arrays.sort(sortedRolls);

        int dice = rolls.length;
        int score = 0;

        int[] scoreAndDice = new int[2];

        for (int i = 2; i < rolls.length; i++) { //Find Three of a Kind, there can be 2 if there are 6 dice.
            if (sortedRolls[i] == sortedRolls[i - 1] && sortedRolls[i] == sortedRolls[i - 2]) {

                threeOfAKind = true;
                if (threeOfAKindGroups[0] == 0) {
                    threeOfAKindGroups[0] = sortedRolls[i];
                } else if (threeOfAKindGroups[0] != i) {
                    threeOfAKindGroups[1] = sortedRolls[i];
                }

            }
        }
        
        for (int i = 0; i < rolls.length; i++) { //Count fives and threes;

            if (rolls[i] == 1) {
                oneCount++;
            }
            if (rolls[i] == 5) {
                fiveCount++;
            }

        }

        if (!threeOfAKind && fiveCount == 0 && oneCount == 0) { //Farkle if no 0s, 5s, 3 of a kind
            farkle = true;
        }

        if (!farkle) { // All these are options as long as you didn't get a farkle.

            System.out.println("You have " + oneCount + " ones."); // Tell player what they have.
            System.out.println("You have " + fiveCount + " fives.");
            if (threeOfAKind) {
                if (threeOfAKindGroups[0] != 0) {
                    System.out.println("You have 2 threes of kind of " + threeOfAKindGroups[0] + " and " + threeOfAKindGroups[1]);
                } else {
                    System.out.println("You have 1 three of a kind of " + threeOfAKindGroups[0]);
                }
            } else {
                System.out.println("You have no three of kind.");
            }


            if (oneCount > 0) { //If you have 1s
                System.out.println("Do you want to cash in 1s? (1 for yes, 0 for no)"); //Cash in 1s
                int cashInOnes = scan.nextInt();
    
                if (cashInOnes == 1) {
                    boolean successCashInOnes = false;
                    while (!successCashInOnes) { // Keep doing this until a correct amount is cashed out.
                        System.out.println("How many 1s to cash out?");
                        int cashInOnesAmount = scan.nextInt();
                        if (cashInOnesAmount <= oneCount) {
                            dice -= cashInOnesAmount; // Take away those 1s.
                            score += (cashInOnesAmount * 100); // 100 points for each 1;
                            if (oneCount >= 3) { //If 1s is a three of a kind;
                                oneCount -= cashInOnesAmount;
                                if (oneCount < 3) { //If it's no longer a three of a kind;
                                    for (int i = 0; i < threeOfAKindGroups.length; i++) {
                                        if (threeOfAKindGroups[i] == 1) {
                                            threeOfAKindGroups[i] = 0; //Get rid of three of kind;
                                        }
                                    }
                                }
                            }
                            successCashInOnes = true;
                        } else {
                            System.out.println("Invalid amount. Trying again.");
                        }
                    }
                }
            }


            if (fiveCount > 0) {
                System.out.println("Do you want to cash in 5s? (1 for yes, 0 for no)"); //Cash in 5s
                int cashInFives = scan.nextInt();
    
                if (cashInFives == 1) {
                    boolean successCashInFives = false;
                    while (!successCashInFives) { // Keep doing this until a correct amount is cashed out.
                        System.out.println("How many 1s to cash out?");
                        int cashInFivesAmount = scan.nextInt();
                        if (cashInFivesAmount <= fiveCount) {
                            dice -= cashInFivesAmount; // Take away those 5s.
                            score += (cashInFivesAmount * 50); // 50 points for each 5;
                            if (fiveCount >= 3) { //If 5s is a three of a kind;
                                fiveCount -= cashInFivesAmount;
                                if (fiveCount < 3) { //If it's no longer a three of a kind;
                                    for (int i = 0; i < threeOfAKindGroups.length; i++) {
                                        if (threeOfAKindGroups[i] == 5) {
                                            threeOfAKindGroups[i] = 0; //Get rid of three of kind;
                                        }
                                    }
                                }
                            }
                            successCashInFives = true;
                        } else {
                            System.out.println("Invalid amount. Trying again.");
                        }
                    }
                }
            }


            if (threeOfAKind) {
                System.out.println("Do you want to cash in threes of a kind? (1 for yes, 0 for no)");
                int cashInThreesOfAKind = scan.nextInt();

                if (cashInThreesOfAKind == 1) {
                    System.out.println("Which three of a kind do you want to cash in? Enter number: ");
                    int groupNumber = scan.nextInt();
                }
            }


        } else {
            scoreAndDice[0] = 0;
            scoreAndDice[1] = 0;
        }

        return scoreAndDice;

    }


}