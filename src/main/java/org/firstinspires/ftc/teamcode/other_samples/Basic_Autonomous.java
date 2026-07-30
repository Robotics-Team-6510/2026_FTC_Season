package org.firstinspires.ftc.teamcode.other_samples;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

/*
 * ### ATTENTION ###
 * This may look scary, big file, lots of code
 * If you go step by step, it is very simple. We have a few custom functions.
 * When starting out, you can ignore "stepGyroTurn", as that uses the IMU and is a little more complicated.
 */
@Autonomous(name = "Basic Autonomous", group = "Other Samples")
@Disabled
public class Basic_Autonomous extends OpMode {
    // Declare Motors, Variables, and Functions
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;
    private IMU Gyro;
    private int STEP = 0;
    private boolean canIncrementStep = false;
    private boolean gyroTurning = false;

    // Checks if any motors are currently moving using encoders
    private boolean isBusy() {
        return (FRMotor.isBusy() || FLMotor.isBusy() || BRMotor.isBusy() || BLMotor.isBusy());
    }


    // Stops and resets all motors, convenience function so we don't have to type these 4 lines over and over
    private void stopAndReset() {
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Sets all motors to run to position mode, convenience function so we don't have to type these 4 lines over and over
    private void runToPosition() {
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // Sets the power of all motors, convenience function so we don't have to type these 4 lines over and over
    private void setPower(double power) {
        FRMotor.setPower(power);
        FLMotor.setPower(power);
        BRMotor.setPower(power);
        BLMotor.setPower(power);
    }

    // Increments step and drives to position
    private void stepPosition(int position, double power) {
        if(!canIncrementStep) {
            // Reset the motors first
            stopAndReset();

            // Set the new power
            setPower(power);

            // Set the position of the motors using the correct +- signs
            FRMotor.setTargetPosition(position);
            FLMotor.setTargetPosition(position);
            BRMotor.setTargetPosition(position);
            BLMotor.setTargetPosition(position);

            // Tell the motors to spin there
            runToPosition();

            // Mark this step as complete so we can increment to the next step once the motors are done moving
            canIncrementStep = true;
        }
    }

    // Increments step and strafes to position
    private void stepStrafe(int strafe, double power) {
        if(!canIncrementStep) {
            stopAndReset();

            setPower(power);

            FRMotor.setTargetPosition(strafe);
            FLMotor.setTargetPosition(-strafe);
            BRMotor.setTargetPosition(-strafe);
            BLMotor.setTargetPosition(strafe);

            runToPosition();

            canIncrementStep = true;
        }
    }

    private void stepTurn(int turn, double power) {
        if(!canIncrementStep) {
            stopAndReset();

            setPower(power);

            FRMotor.setTargetPosition(-turn);
            FLMotor.setTargetPosition(turn);
            BRMotor.setTargetPosition(-turn);
            BLMotor.setTargetPosition(turn);

            runToPosition();

            canIncrementStep = true;
        }
    }

    // MORE ADVANCED -> Increments step and turns to position using the gyro, does not use PID control
    // Only use once confident with the other functions.
    private void stepGyroTurn(int direction, double angle, double power) {
        if (!canIncrementStep) {
            FRMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            FLMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BRMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            BLMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            canIncrementStep = true;
            gyroTurning = true;
        }

        YawPitchRollAngles orientation = Gyro.getRobotYawPitchRollAngles();
        double facing = orientation.getYaw(AngleUnit.DEGREES);
        double difference = abs(angle-abs(facing));
        if (difference>2) {
            double scaler = difference + 0.1;
            FRMotor.setPower(power * direction * scaler);
            FLMotor.setPower(power * direction * scaler);
            BRMotor.setPower(-power * direction * scaler);
            BLMotor.setPower(-power * direction * scaler);
        } else {
            stopAndReset();
            Gyro.resetYaw();
            gyroTurning = false;
        }
    }

    // All Config and Hardware Mapping
    @Override
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        FRMotor = hardwareMap.get(DcMotor.class, "fr");
        FLMotor = hardwareMap.get(DcMotor.class, "fl");
        BRMotor = hardwareMap.get(DcMotor.class, "br");
        BLMotor = hardwareMap.get(DcMotor.class, "bl");

        // One side of motors will always need to be reversed so that they all spin in the same direction.
        FRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // Set the zeroPowerBehaviour mode to ensure it brakes upon reaching the position
        FRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // It is good practise to set the mode to ensure consistency
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Configure IMU, it looks scary but it is very easy:
        Gyro = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot hubOrientation = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        Gyro.initialize(new IMU.Parameters(hubOrientation));
        Gyro.resetYaw();
    }

    // Code to run repeatedly during Autonomous
    @Override
    public void loop() {
        // We use a switch statement for Autonomous
        switch(STEP) {
            case 0:
                stepPosition(1000,0.5);
                break;
            case 1:
                stepTurn(1296, 0.5);
                break;
            case 2:
                stepStrafe(-2000, 0.8);
                break;
            case 3:
                stepGyroTurn(-1, 90,0.3);
                break;
            default:
                stopAndReset();
                break;
        }

        // Once the motors have stopped moving and we are cleared to advance to the next step, increment the step
        // gyroTurning is optional and only needed if using gyro for turns.
        if(!isBusy() && canIncrementStep && !gyroTurning) {
            STEP++;
            canIncrementStep = false;
        }
    }
}