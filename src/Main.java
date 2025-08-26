import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalTime alarmTime = null;

        while (alarmTime == null) {
            System.out.print("Enter the time for the alarm: ");
            String input = scanner.nextLine().trim();

            input = input.replace('.', ':');

            try {
                alarmTime = parseFlexibleTime(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Try again!");
            }
        }

        Thread alarmThread = new Thread(new AlarmClock(alarmTime));
        alarmThread.start();

        scanner.close();
    }

    private static LocalTime parseFlexibleTime(String input) {
        String[] parts = input.split(":");

        int hour = 0, minute = 0, second = 0;

        if (parts.length >= 1) hour = Integer.parseInt(parts[0]);
        if (parts.length >= 2) minute = Integer.parseInt(parts[1]);
        if (parts.length >= 3) second = Integer.parseInt(parts[2]);

        return LocalTime.of(hour, minute, second);
    }
}
