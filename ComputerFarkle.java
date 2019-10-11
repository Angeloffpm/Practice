import java.util.*;

public class ComputerFarkle {

    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        
        rand = new Random();
        scan = new Scanner(System.in);

        int amyScore = 0;
        int bobScore = 0;

        int amyGamesWon = 0;
        int bobGamesWon = 0;

        double amyRerollChance = 0.33;
        double bobRerollChance = 0.66;

        int turn = 0;

        while (amyGamesWon + bobGamesWon < 1000) {
            
            while (amyScore < 10000 && bobScore < 10000) {
    
    
                if (turn % 2 == 0) { //Amy's Turn
    

    
                } else { //Bob's Turn
    

    
                }
    
                turn++;
    
            }

            if (amyScore >= 10000) { //If Amy wins give her +1 win
                amyGamesWon++;
            } else { //If Bob wins give her +1 win
                bobGamesWon++;
            }

        }

        System.out.println("Amy won " + amyGamesWon + " games.");
        System.out.println("Bob won " + bobGamesWon + " games.");

    }



    public static int[] roll(int dice) {

        int[] rolls = new int[dice];

        for (int i = 0; i < dice; i++) {

            rolls[i] = rand.nextInt(6) + 1;
            
        }

        return rolls;

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


    public static int[] turn( int[] rolls ) {

        int score = 0;
        int dice = rolls.length;
        int[] scoreAndDice = new int[2];

        boolean farkle = false;
        boolean threeOfAKind = false;
        int fiveCount = 0;
        int oneCount = 0;
        int[] threeOfAKindGroups = new int[2];
        int[] sortedRolls = rolls;
        Arrays.sort(sortedRolls);

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

        if (!farkle) {
            
            if (threeOfAKind) { //Computer always takes three of a kind.

                score += threeOfAKindScore(threeOfAKindGroups[0]);
                dice -= 3;

                if (threeOfAKindGroups[0] == 1) {
                    oneCount -= 3;
                } else if (threeOfAKindGroups[0] == 5) {
                    fiveCount -= 3;
                }

                if (threeOfAKindGroups[1] != 0) {
                    score += threeOfAKindScore(threeOfAKindGroups[1]);
                    dice -= 3;

                    if (threeOfAKindGroups[1] == 1) {
                        oneCount -= 3;
                    } else if (threeOfAKindGroups[1] == 5) {
                        fiveCount -= 3;
                    }
                }

                for (int i = 0; i < sortedRolls.length; i++) {

                    if (sortedRolls[i] == threeOfAKindGroups[0] || sortedRolls[i] == threeOfAKindGroups[1]) {

                        sortedRolls[i] = 0; //Set cashed in dice to 0.

                    }

                }

            }

            if (oneCount > 0 || fiveCount > 0) {

                for (int i = 0; i < sortedRolls.length; i++) {

                    if (sortedRolls[i] == 1) {

                        score += 100;
                        dice--;
                        sortedRolls[i] = 0;

                    } else if (sortedRolls[i] == 5) {

                        score += 50;
                        dice--;
                        sortedRolls[i] = 0;

                    }

                }

            }

        } else {

            score = 0;
            dice = -1;

        }

        scoreAndDice[0] = score;
        scoreAndDice[1] = dice;

        return scoreAndDice;

    }


}