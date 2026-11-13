package org.firstinspires.ftc.teamcode;

import java.util.function.Consumer;

public class Controller {
    public StaticFeedForwardSign staticFeedForwardSign = null;
    public double kP, kD, kV, kS;
    public double target;
    public double error;
    private long previousTime = 0L;

    public enum StaticFeedForwardSign {
        ERROR,
        TARGET
    }

    public Controller(Consumer<Controller> configure) {
        configure.accept(this);
    }

    public double calculate(double target, double current) {
        this.target = target;
        return calculate(current);
    }

    public double calculate(double current) {
        long time = System.nanoTime();
        double error = target - current;
        double res = (kV * target) + (kP * error) + (kD * (error - this.error) / (time - previousTime) / 1e9);

        switch (staticFeedForwardSign) {
            case ERROR:
                res += Math.signum(error) * kS;
                break;
            case TARGET:
                res += Math.signum(target) * kS;
                break;
        }

        previousTime = time;
        this.error = error;
        return res;
    }
}
