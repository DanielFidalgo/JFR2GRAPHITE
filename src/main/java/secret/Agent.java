package secret;

import jdk.jfr.consumer.RecordingStream;

import java.lang.instrument.Instrumentation;
import java.time.Duration;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        try (var rs = new RecordingStream()) {
            rs.enable("jdk.CPULoad").withPeriod(Duration.ofSeconds(1));
            rs.enable("jdk.JavaMonitorEnter").withThreshold(Duration.ofMillis(10));
            rs.onEvent("jdk.CPULoad", event -> {
                System.out.println(event.getFloat("machineTotal"));
            });
            rs.onEvent("jdk.JavaMonitorEnter", event -> {
                System.out.println(event.getClass("monitorClass"));
            });
            rs.start();
        }
    }
}
