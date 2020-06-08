package secret;

import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentStarter {
    public static void premain(String agentArgs, Instrumentation inst) {
        execute();
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        execute();
    }

    private static void execute() {
        try {
            Logger.getLogger("JFR2GRAPHITE").log(Level.INFO, "Attaching JFR Monitor");
            new Thread(new Agent()).start();
        } catch (Throwable t) {
            Logger.getLogger("JFR2GRAPHITE").log(Level.SEVERE,"Unable to attach JFR Monitor", t);
        }
    }
}
