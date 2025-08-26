import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.awt.Toolkit;

public class AlarmClock implements Runnable {

    private final LocalTime alarmTime;

    public AlarmClock(LocalTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    @Override
    public void run() {
        LocalDateTime target = LocalDateTime.of(LocalDate.now(), alarmTime);
        if (!LocalDateTime.now().isBefore(target)) {
            target = target.plusDays(1);
        }

        while (LocalDateTime.now().isBefore(target)) {
            LocalDateTime now = LocalDateTime.now();
            long secondsLeft = Duration.between(now, target).getSeconds();

            long h = secondsLeft / 3600;
            long m = (secondsLeft % 3600) / 60;
            long s = secondsLeft % 60;

            LocalTime cur = now.toLocalTime();
            System.out.printf(
                "\rCurrent: %02d:%02d:%02d | Time left: %02d:%02d:%02d",
                cur.getHour(), cur.getMinute(), cur.getSecond(),
                h, m, s
            );

            try { Thread.sleep(1000); } catch (InterruptedException e) { return; }
        }

        System.out.println("\nAlarm is going off!");
        try {
            for (int i = 0; i < 5; i++) {
                Toolkit.getDefaultToolkit().beep();
                Thread.sleep(500);
            }
        } catch (Exception ignored) {
            System.out.print("\007\007\007");
            System.out.flush();
        }
    }
}
