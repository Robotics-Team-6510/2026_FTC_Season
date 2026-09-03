package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class Challenge_Code extends OpMode {
    // section 1 - declaring variables - making variables
    private DcMotor FrontRight, FrontLeft, BackLeft, BackRight, FirstFeeder, SecondFeeder;
    private IMU imu;

    double speed = 0.85;

    @Override
    public void init(){
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



    }
    @Override
    public void loop() {
        // actual code

        double forward = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;

        if (gamepad1.left_bumper) {
            FirstFeeder.setPower(1);
        } else if (gamepad1.right_bumper) {
            FirstFeeder.setPower(-1);
        } else {
            FirstFeeder.setPower(0);
        }

        if (gamepad1.a) {
            SecondFeeder.setPower(1);
        } else if (gamepad1.b) {
            SecondFeeder.setPower(-1);
        } else {
            SecondFeeder.setPower(0);
        }

        if (gamepad1.leftTriggerWasPressed()) {
            speed += 0.1;
            if (speed > 1) speed = 1;
        } else if (gamepad1.rightTriggerWasPressed()) {
            speed -= 0.1;
            if (speed < 0.1) speed = 0.1;
        } else if (gamepad1.y) {
            speed = 0.85;
        } else if (gamepad1.right_trigger_pressed) {
            speed = 0.3;
        }

        if (gamepad1.options) {
            imu.resetYaw();
        }

        double angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double x = strafe * Math.cos(-angle) - forward * Math.sin(-angle);
        double y = strafe * Math.sin(-angle) + forward * Math.cos(-angle);
        x = x * 1.1;

        double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(turn), 1);

        FrontLeft.setPower((y + turn + x) / denominator * speed);
        FrontRight.setPower((y - turn - x) / denominator * speed);
        BackLeft.setPower((y + turn - x) / denominator * speed);
        BackRight.setPower((y - turn + x) / denominator * speed);



    }
}
