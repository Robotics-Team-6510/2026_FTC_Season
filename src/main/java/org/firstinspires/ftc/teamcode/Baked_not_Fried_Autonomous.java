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

    public void turnRight(int distance, double speed) {
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

    public void strafeR(int distance, double speed) {
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

        RF.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);
        LF.setDirection(DcMotorSimple.Direction.FORWARD);
        LB.setDirection(DcMotorSimple.Direction.FORWARD);
        F.setDirection(DcMotorSimple.Direction.FORWARD);


        FS.setDirection(DcMotorSimple.Direction.REVERSE);
        SS.setDirection(DcMotorSimple.Direction.REVERSE);


        FS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        imu.resetYaw();

        waitForStart();

       FS.setVelocity(1800);
       SS.setVelocity(1750);
       sleep(3000);
       I.setPower(1);
       F.setPower(0.75);
       sleep(3000);
       FS.setVelocity(0);
       SS.setVelocity(0);
       F.setPower(0);
       I.setPower(0);
       move(-700,1);
       strafeR(1700,1);
       move(-3000,1);
       strafeR(300,1);


    }
}