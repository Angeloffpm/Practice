import java.util.Scanner;

public class SignUp {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        String[] existingUsernames = {"admin", "person", "jimbob234", "skater_gurl17", "gary"};

        System.out.println("Sign up.");

        System.out.println("Username: ");
        String username = scan.nextLine();
        boolean goodUsername = true;

        for (int i = 0; i < existingUsernames.length; i++) {
        if (username.equals(existingUsernames[i])) {
                goodUsername = false;
            }
        }

        int userLength = username.length();



        if (goodUsername) {

            System.out.println("Password: ");
            String password = scan.nextLine();

            char[] passwordChars = password.toCharArray();

            char[] vowels = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};

            boolean vowelCheck = false;
            boolean charCheck = false;
            boolean userCheck = true;
            boolean numberCheck = true;

            boolean goodPassword = true;


            for (int v = 0; v < vowels.length; v++) {
                if (passwordChars[0] == vowels[v]) {
                    vowelCheck = true;
                }
            }


            char[] requiredChars = {'#', '!', '$', '&', '*'};

            for (int i = 0; i < passwordChars.length; i++) {
                for (int x = 0; x < requiredChars.length; x++) {
                    if (passwordChars[i] == requiredChars[x]) {
                        charCheck = true;
                    }
                }
            }


            if (password.length() < userLength) {
                userCheck = true;
            }else{
                for (int i = 0; i < (password.length() - userLength); i++) {
                    if (password.substring(i, i + userLength).equals(username)) {
                        userCheck = false;
                    }
                }
            }


            char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

            for (int i = 0; i < (password.length() - 2); i++) {
                for (int n = 0; n < numbers.length; n++) {
                    if (passwordChars[i] == numbers[n]) {
                        numberCheck = false;
                    }
                }
            }

        
            if (vowelCheck && charCheck && userCheck && numberCheck) {
                System.out.println("Registration successful!");
            }else{
                if (!vowelCheck) {
                    System.out.println("Error: password does not start with a vowel.");
                }
                if (!charCheck) {
                    System.out.println("Error: password does not contain special character.");
                }
                if (!userCheck) {
                    System.out.println("Error: password contains username.");
                }
                if (!numberCheck) {
                    System.out.println("Error: password contains numbers outside last 2 chars.");
                }
            }

        }else{

            System.out.println("Username taken.");

        }


    }
}