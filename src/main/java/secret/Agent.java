package secret;

import jdk.jfr.FlightRecorder;
import jdk.jfr.consumer.RecordingStream;

import java.time.Duration;


public class Agent implements Runnable {
    public void run() {
        try (RecordingStream rs = new RecordingStream()) {
            FlightRecorder.getFlightRecorder()
                          .getEventTypes()
                          .forEach(eventType -> {
                              rs.enable(eventType.getName()).withPeriod(Duration.ofMillis(1));
                              rs.onEvent(eventType.getName(), System.out::println);
                          });
            rs.start();
        }
    }
}
