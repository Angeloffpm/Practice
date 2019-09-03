import java.util.Scanner;
import java.time.LocalDateTime;

public class TimeUntil {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        int currentHour = LocalDateTime.now().getHour();
        int currentMinute = LocalDateTime.now().getMinute();
        int currentSecond = LocalDateTime.now().getSecond();

        System.out.println("Current time: " + currentHour + " hours " + currentMinute + " minutes " + currentSecond + " seconds");
        
        System.out.println("Enter hour: ");
        int targetHour = scan.nextInt();

        System.out.println("Enter minute: ");
        int targetMinute = scan.nextInt();

        System.out.println("Enter second: ");
        int targetSecond = scan.nextInt();

        int hoursUntil;
        int minutesUntil;
        int secondsUntil;


        if (targetHour >= currentHour) {
            hoursUntil = targetHour - currentHour;
        }else{
            hoursUntil = (targetHour + 12) - currentHour;
        }

        if (targetMinute >= currentMinute) {
            minutesUntil = targetMinute - currentMinute;
        }else{
            minutesUntil = (targetMinute + 60) - currentMinute;
            hoursUntil = hoursUntil - 1;
        }

        if (targetSecond >= currentSecond) {
            secondsUntil = targetSecond - currentSecond;
        }else{
            secondsUntil = (targetSecond + 60) - currentSecond;
            minutesUntil = minutesUntil - 1;
        }



        if (hoursUntil > 0 && minutesUntil > 0 && secondsUntil > 0) {
            System.out.println(hoursUntil + " hours " + minutesUntil + " minutes " + secondsUntil + " seconds");
        }else if (hoursUntil > 0 && minutesUntil > 0) {
            System.out.println(hoursUntil + " hours " + minutesUntil + " minutes");
        }else if (hoursUntil > 0 && secondsUntil > 0) {
            System.out.println(hoursUntil + " hours " + secondsUntil + " seconds");
        }else if (minutesUntil > 0 && secondsUntil > 0) {
            System.out.println(minutesUntil + " minutes " + secondsUntil + " seconds");
        }else if (hoursUntil > 0) {
            System.out.println(hoursUntil + " hours");
        }else if (minutesUntil > 0) {
            System.out.println(minutesUntil + " minutes");
        }else if (secondsUntil > 0) {
            System.out.println(secondsUntil + " seconds");
        }

    }
}