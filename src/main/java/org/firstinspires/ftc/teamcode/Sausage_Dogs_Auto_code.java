package org.firstinspires.ftc.teamcode;

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.opencv.video.BackgroundSubtractorKNN;


@Autonomous
public class Sausage_Dogs_Auto_code extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;

    private DcMotor FrontRight, FrontLeft, BackLeft, BackRight, FirstFeeder, SecondFeeder;
    private IMU imu;

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
        imu.resetYaw();
    }



    @Override
    public void runOpMode() {
        // Run Once Here
        // linking to config
        FrontRight = hardwareMap.get(DcMotor.class, "FRW");
        FrontLeft = hardwareMap.get(DcMotor.class, "FLW");
        BackLeft = hardwareMap.get(DcMotor.class, "BLW");
        BackRight = hardwareMap.get(DcMotor.class, "BRW");
        FirstFeeder = hardwareMap.get(DcMotor.class, "FF");
        SecondFeeder = hardwareMap.get(DcMotor.class, "SF");

        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "IMU");
        RevHubOrientationOnRobot Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        IMU.Parameters Para = new IMU.Parameters(Orientation);
        imu.initialize(Para);

        waitForStart();
        // Auto Sequence
        AllSetPower(0.5);
        AllRunToPosition();
        AllStopAndResetEncoder();
        AllSetTargetPosition(1024);
        while(AllBusy()){}

        FirstFeeder.setPower(1);
        AllDrive(1000, 1);
        FirstFeeder.setPower(0);
        AllTurn(-1, 60, 0.8);
        AllStrafe(2000,0.6);

    }

}