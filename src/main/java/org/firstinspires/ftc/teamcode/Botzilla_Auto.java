package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.util.ElapsedTime;

// @Autonomous so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@Autonomous(group = "Group Name")

public class Botzilla_Auto extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;

    private DcMotor shooter1, shooter2, intake;
    DcMotor FR, FL, BR, BL, IN;
    IMU imu;
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        // Run Once Here
        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");
        intake = hardwareMap.get(DcMotor.class, "intake");

        shooter1.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter2.setDirection(DcMotorSimple.Direction.REVERSE);

        shooter1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // 2
        FR = hardwareMap.get(DcMotor.class, "front right");
        FL = hardwareMap.get(DcMotor.class, "front left");
        BL = hardwareMap.get(DcMotor.class, "back left");
        BR = hardwareMap.get(DcMotor.class, "back right");
        IN = hardwareMap.get(DcMotor.class, "intake");

        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        imu.initialize(parameters);

        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        timer.reset();

        powerDriveMotors(0.4);

        while (timer.seconds()<0.5){}
        botShoot(1);
        while (timer.seconds()<1) {
        }

        powerDriveMotors(0);
//        botIntake(1);
//        botShoot(1);

        while (timer.seconds()<7){}
        botTurnRight(0.25);
        while (timer.seconds()<10){}

        while (timer.seconds()<10){}
//

        // Auto Sequence
        /*
        right turn
        straight for 2sec
        intake
        shoot ball
        turn left
        intake
        shoot ball

        */

    }

    public void botShoot(double shooterPower) {
        shooter1.setPower(shooterPower);
        shooter2.setPower(shooterPower);
    }

    public void botIntake(double intakePower) {
        intake.setPower(intakePower);
    }

    public void powerDriveMotors(double bobpwr) {
        FR.setPower(bobpwr);
        FL.setPower(bobpwr);
        BR.setPower(bobpwr);
        BL.setPower(bobpwr);

    }

    public void botTurnRight (double bobpwr) {
        FR.setPower(-bobpwr);
        FL.setPower(bobpwr);
        BR.setPower(-bobpwr);
        BL.setPower(bobpwr);

    }

    public void botTurnLeft (double bobpwr) {
        FR.setPower(bobpwr);
        FL.setPower(-bobpwr);
        BR.setPower(bobpwr);
        BL.setPower(-bobpwr);

    }

}