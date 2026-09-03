package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp()
public class SFC1872_L extends OpMode {
    // Declare Variables
    private DcMotor frontLeft, frontRight, backRight, backLeft,intake,ontake2;
    private IMU london;

    @Override
    public void init() {
        // Initialise variables
        frontLeft = hardwareMap.get(DcMotor.class, "frontleftwheel");
        frontRight = hardwareMap.get(DcMotor.class, "frontrightwheel");
        backLeft = hardwareMap.get(DcMotor.class, "backleftwheel");
        backRight = hardwareMap.get(DcMotor.class, "backrightwheel");
        intake = hardwareMap.get(DcMotor.class, "intake");
        ontake2=hardwareMap.get(DcMotor.class,"ontake2");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        london = hardwareMap.get(IMU.class,"imu");
        RevHubOrientationOnRobot london_bridge = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);
        IMU.Parameters london_tower=new IMU.Parameters(london_bridge);
        london.initialize(london_tower);
    }

    @Override
    public void loop() {
        // Robot drive
        double y = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double x = gamepad1.left_stick_x;

        double london_heading = -london.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double strafe = x * Math.cos(london_heading) - y * Math.sin(london_heading);
        double forward = x * Math.sin(london_heading) + y * Math.cos(london_heading);

        if (gamepad1.y) {
            london.resetYaw();
        }

        double SPEED = 1;

        frontLeft.setPower((forward+turn+strafe)*SPEED);
        frontRight.setPower((forward-turn-strafe)*SPEED);
        backLeft.setPower((forward+turn-strafe)*SPEED);
        backRight.setPower((forward-turn+strafe)*SPEED);

        if (gamepad1.right_bumper) {
            intake.setPower(1);
        }else if (gamepad1.left_bumper) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }

        if (gamepad1.right_trigger > 0.5) {
            ontake2.setPower(1);
        }else if (gamepad1.left_trigger > 0.5) {
            ontake2.setPower(-1);
        } else {
            ontake2.setPower(0);
        }

        telemetry.addData("anything", london.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();


    }
}
