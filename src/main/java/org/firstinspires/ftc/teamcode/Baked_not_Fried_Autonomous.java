package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

// @Autonomous so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@Autonomous(group = "Baked_not_Fried_Autonomous")

public class Baked_not_Fried_Autonomous extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    // 1 - declare variables

    DcMotor RF, RB, LF, LB, I, F;
    DcMotorEx FS, SS;

    IMU imu;

    public void move(int distance, double speed) {
        // Run Once Here
//35971
        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RF.setTargetPosition(distance);
        RB.setTargetPosition(distance);
        LF.setTargetPosition(distance);
        LB.setTargetPosition(distance);

        RF.setPower(speed);
        RB.setPower(speed);
        LF.setPower(speed);
        LB.setPower(speed);

        RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(RF.isBusy() || RB.isBusy() || LF.isBusy() || LB.isBusy()) {//  Run Once Here
        }
    }

    public void turn(int distance, double speed) {
        // Run Once Here
//35971
        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RF.setTargetPosition(-distance);
        RB.setTargetPosition(-distance);
        LF.setTargetPosition(distance);
        LB.setTargetPosition(distance);

        RF.setPower(speed);
        RB.setPower(speed);
        LF.setPower(speed);
        LB.setPower(speed);

        RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(RF.isBusy() || RB.isBusy() || LF.isBusy() || LB.isBusy()) {//  Run Once Here
        }
    }

    public void strafe(int distance, double speed) {
        // Run Once Here
//35971
        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RF.setTargetPosition(-distance);
        RB.setTargetPosition(distance);
        LF.setTargetPosition(distance);
        LB.setTargetPosition(-distance);

        RF.setPower(speed);
        RB.setPower(speed);
        LF.setPower(speed);
        LB.setPower(speed);

        RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(RF.isBusy() || RB.isBusy() || LF.isBusy() || LB.isBusy()) {//  Run Once Here
        }
    }

    public void setShooters(){
        FS.setVelocity(-2200);
        SS.setVelocity(-2400);
    }
    public void stopShooters(){
        FS.setVelocity(0);
        SS.setVelocity(0);
    }

    @Override
    public void runOpMode(){
        RF = hardwareMap.get(DcMotor.class, "rf");
        RB = hardwareMap.get(DcMotor.class, "rb");
        LF = hardwareMap.get(DcMotor.class, "lf");
        LB = hardwareMap.get(DcMotor.class, "lb");
        I = hardwareMap.get(DcMotor.class, "i");
        F = hardwareMap.get(DcMotor.class, "f");
        FS = hardwareMap.get(DcMotorEx.class, "fs");
        SS = hardwareMap.get(DcMotorEx.class, "ss");

        imu = hardwareMap.get(IMU.class, "imu");

        RF.setDirection(DcMotorSimple.Direction.FORWARD);
        RB.setDirection(DcMotorSimple.Direction.FORWARD);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        F.setDirection(DcMotorSimple.Direction.REVERSE);

        FS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        imu.resetYaw();

        waitForStart();

       move(1000,0.7);
       turn(-500, 0.4);
       strafe(750, 0.2);
       setShooters();
       sleep(1000);
       stopShooters();
       I.setPower(1);
       move(2000, 0.3);
       I.setPower(0);

    }
}