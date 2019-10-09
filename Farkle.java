import java.util.*;

public class Farkle {

    static Random rand;
    static Scanner scan;

    public static void main(String[] args) {
        
        rand = new Random();
        scan = new Scanner(System.in);

        int player1Score = 0;
        int player2Score = 0;
        int turn = 0;

        System.out.println("Roll who sees to go first!");
        int[] player1FirstRoll = roll(1);
        int[] player2FirstRoll = roll(1);

        System.out.println("Player 1 rolled: " + player1FirstRoll[0]);
        System.out.println("Player 2 rolled: " + player2FirstRoll[0]);
        
        
        while (player1FirstRoll[0] == player2FirstRoll[0]) {
            
            System.out.println("You both rolled " + player1FirstRoll[0] + ". Re-rolling!");
            
            player1FirstRoll = roll(1);
            player2FirstRoll = roll(1);

            System.out.println("Player 1 rolled: " + player1FirstRoll[0]);
            System.out.println("Player 2 rolled: " + player2FirstRoll[0]);

        }

        if (player1FirstRoll[0] > player2FirstRoll[0]) {
            System.out.println("\nPlayer 1 wins! They go first!");
        } else {
            System.out.println("\nPlayer 2 wins! They go first!");
            turn++;
        }


        while (player1Score < 10000 && player2Score < 10000) {
            
            int[] rolls = roll(6); //Initial roll

            if (turn % 2 == 0) { //Player 1's Turn

                player1Score += organizeTurn(1, rolls);
                
            } else { //Player 2's Turn
                        
                player2Score += organizeTurn(2, rolls);

            }

            turn++;

            System.out.println("\nCurrent Score:");
            System.out.println("Player 1:" + player1Score);
            System.out.println("Player 2:" + player2Score);

        }

        if (player1Score > player2Score) {
            System.out.println("Player 1 wins with a score of " + player1Score);
        } else {
            System.out.println("Player 2 wins with a score of " + player2Score);
        }

    }


    public static int[] roll(int dice) {

        int[] rolls = new int[dice];

        for (int i = 0; i < dice; i++) {

            rolls[i] = rand.nextInt(6) + 1;
            
        }

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
        // System.out.println(Arrays.toString(sortedRolls));

        int dice = rolls.length;
        int score = 0;

        int[] scoreAndDice = new int[2];

        for (int i = 2; i < rolls.length; i++) { //Find Three of a Kind, there can be 2 if there are 6 dice.
            if (sortedRolls[i] == sortedRolls[i - 1] && sortedRolls[i] == sortedRolls[i - 2] && sortedRolls.length >= 3) {

                threeOfAKind = true;
                if (sortedRolls[sortedRolls.length - 1] == sortedRolls[0]) { //If you roll 6 of the same number.
                    threeOfAKindGroups[0] = i;
                    threeOfAKindGroups[1] = i;
                } else if (threeOfAKindGroups[0] == 0) {
                    threeOfAKindGroups[0] = sortedRolls[i];
                } else if (threeOfAKindGroups[0] != sortedRolls[i] && threeOfAKindGroups[0] != 0) {
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
                if (threeOfAKindGroups[1] != 0) {
                    System.out.println("You have 2 threes of kind of " + threeOfAKindGroups[0] + " and " + threeOfAKindGroups[1] + "\n");
                } else {
                    System.out.println("You have 1 three of a kind of " + threeOfAKindGroups[0] + "\n");
                }
            } else {
                System.out.println("You have no three of kind.\n");
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
                        System.out.println("How many 5s to cash out?");
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
                int groupAmount;

                if (threeOfAKindGroups[1] != 0) {
                    groupAmount = 2;
                } else {
                    groupAmount = 1;
                }

                if (cashInThreesOfAKind == 1) {
                    
                    while (groupAmount > 0) {
                        System.out.println("Which three of a kind do you want to cash in? Enter number: ");
                        int groupNumber = scan.nextInt();
                        if (threeOfAKindGroups[0] == groupNumber) {
                            score += threeOfAKindScore(groupNumber);
                            groupAmount--;
                            dice -= 3;
                        } else if (threeOfAKindGroups[1] == groupNumber) { 
                            score += threeOfAKindScore(groupNumber);
                            groupAmount--;
                            dice -= 3;
                        } else {
                            System.out.println("Invalid input.");
                        }
                    }

                }
            }

            scoreAndDice[0] = score;
            scoreAndDice[1] = dice;

        } else {

            scoreAndDice[0] = 0;
            scoreAndDice[1] = -1;

        }

        return scoreAndDice;

    }


    public static int threeOfAKindScore (int group) {

        int score;

        if (group == 1) { 
            score = 1000;
        } else {
            score = (group * 100);
        }

        return score;

    }



    public static int organizeTurn(int turn, int[] rolls) {

        int turnScore = 0;
        int rollAgain = 1;

        if (turn == 1) { //Player 1
            System.out.println("\nPlayer 1's Rolls:");
        } else { //Player 2
            System.out.println("\nPlayer 2's Rolls:");
        }
        System.out.println(Arrays.toString(rolls));
        
        int[] newRolls = evaluateTurn(rolls);
        turnScore += newRolls[0];

        if (newRolls[0] > 0) {
            System.out.println("Roll again? (1 for yes 0 for no)");
            rollAgain = scan.nextInt();
        }

        while (rollAgain != 0 && rollAgain != 1) {

            System.out.println("Invalid input. Try again.");
            rollAgain = scan.nextInt();

        }

        while (newRolls[1] > 0 && rollAgain == 1) {

            System.out.println("Current turn score: " + turnScore);

            int newRollAmount = newRolls[1];
            int[] nextRoll = roll(newRollAmount);

            System.out.println("New rolls:");
            System.out.println(Arrays.toString(nextRoll));

            newRolls = evaluateTurn(nextRoll);
            turnScore += newRolls[0];

            if (newRolls[1] > 0) {

                System.out.println("Roll again? (1 for yes 0 for no)");
                rollAgain = scan.nextInt();

                while (rollAgain != 0 && rollAgain != 1) {

                    System.out.println("Invalid input. Try again.");
                    rollAgain = scan.nextInt();

                }

            } else if (newRolls[1] == 0) {

                System.out.println("Out of dice! Turn over!");
                rollAgain = 0;

            } else {

                System.out.println("Farkle rolled! Turn over with 0 points.");
                rollAgain = 0;
                turnScore = 0;

            }

        }
        
        return turnScore;
        
    }










}