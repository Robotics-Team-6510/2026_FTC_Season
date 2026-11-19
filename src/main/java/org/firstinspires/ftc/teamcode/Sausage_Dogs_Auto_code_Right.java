package org.firstinspires.ftc.teamcode;

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@Autonomous
public class Sausage_Dogs_Auto_code_Right extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;

    private DcMotor FrontRight, FrontLeft, BackLeft, BackRight, FirstFeeder, Intake;
    private IMU imu;

    private DcMotorEx LeftShooter, RightShooter;

    private void AllSetPower(double power) {
        FrontRight.setPower(power);
        FrontLeft.setPower(power);
        BackLeft.setPower(power);
        BackRight.setPower(power);
    }

    private void AllRunToPosition() {
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeft.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    private void AllStopAndResetEncoder() {
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void AllRunNormal() {
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void AllSetTargetPosition(int distance) {
        FrontRight.setTargetPosition(distance);
        FrontLeft.setTargetPosition(distance);
        BackRight.setTargetPosition(distance);
        BackLeft.setTargetPosition(distance);
    }

    private void AllSetStrafePosition(int distance) {
        FrontRight.setTargetPosition(-distance);
        FrontLeft.setTargetPosition(distance);
        BackRight.setTargetPosition(distance);
        BackLeft.setTargetPosition(-distance);

    }

    private boolean AllBusy() {
        boolean busy = FrontRight.isBusy() || FrontLeft.isBusy() || BackRight.isBusy() || BackLeft.isBusy();
        return busy;
    }

    private void AllDrive(int distance, double power) {
        AllSetTargetPosition(distance);
        AllSetPower(power);
        AllRunToPosition();
        while(AllBusy());
        AllStopAndResetEncoder();
    }

    private void AllStrafe(int distance, double power) {
        AllSetStrafePosition(distance);
        AllSetPower(power);
        AllRunToPosition();
        while(AllBusy());
        AllStopAndResetEncoder();
    }

    private void AllTurn(int direction, double angle, double power) {
        AllSetPower(0);
        AllRunNormal();
        imu.resetYaw();
        double Yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double difference = Math.abs(angle - Math.abs(Yaw));
        while(difference > 2) {
            Yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
            difference = Math.abs(angle - Math.abs(Yaw));
            double speed = difference / 20 + 0.1;
            FrontLeft.setPower(direction * power * speed);
            FrontRight.setPower(-direction * power * speed);
            BackLeft.setPower(direction * power * speed);
            BackRight.setPower(-direction * power * speed);
        }
        AllStopAndResetEncoder();
    }



    @Override
    public void runOpMode() {
        // Run Once Here
        // linking to config

        // init

        FrontRight = hardwareMap.get(DcMotor.class, "FRW");
        FrontLeft = hardwareMap.get(DcMotor.class, "FLW");
        BackLeft = hardwareMap.get(DcMotor.class, "BLW");
        BackRight = hardwareMap.get(DcMotor.class, "BRW");

        LeftShooter = hardwareMap.get(DcMotorEx.class, "LS");
        RightShooter = hardwareMap.get(DcMotorEx.class, "RS");

        FirstFeeder = hardwareMap.get(DcMotor.class, "FF");

        Intake = hardwareMap.get(DcMotor.class, "IN");


        FrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftShooter.setDirection(DcMotorSimple.Direction.FORWARD);
        RightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        FirstFeeder.setDirection(DcMotorSimple.Direction.FORWARD);

        Intake.setDirection(DcMotorSimple.Direction.REVERSE);


        imu = hardwareMap.get(IMU.class, "IMU");
        RevHubOrientationOnRobot Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.LEFT, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        IMU.Parameters Para = new IMU.Parameters(Orientation);
        imu.initialize(Para);


        AllStopAndResetEncoder();
        waitForStart();

        AllStopAndResetEncoder();
        waitForStart();
        sleep(12000);

        LeftShooter.setVelocity(2150);
        RightShooter.setVelocity(2150);
        sleep(1000);
        FirstFeeder.setPower(0.8);
        sleep(1000);
        Intake.setPower(0.8);
        sleep(1000);

        LeftShooter.setVelocity(0);
        RightShooter.setVelocity(0);
        FirstFeeder.setPower(0);
        Intake.setPower(0);

        AllDrive(1000, 0.5);
        AllTurn(1, 80, 0.5);
        AllDrive(2000, 0.5);


    }
}