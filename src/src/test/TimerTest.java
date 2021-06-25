package test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    public static class CountNumber extends TimerTask {
        int count = 0;
        Timer timer;

        CountNumber(Timer timer){
            this.timer = timer;
        }
        @Override
        public void run() {
            System.out.println(count);
            count++;
            if(count == 10){
                timer.cancel();
            }
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new CountNumber(timer), 1, 1000);
    }
}
