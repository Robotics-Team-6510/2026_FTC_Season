package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class NEW_botzilla_challenge1 extends OpMode {
    // 1 - variable definition

    private DcMotor shooter1, shooter2;
    DcMotor FR, FL, BR, BL, IN;
    IMU imu;

    @Override
    public void init() {

        shooter1 = hardwareMap.get(DcMotor.class, "shooter1");
        shooter2 = hardwareMap.get(DcMotor.class, "shooter2");

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

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        imu.initialize(parameters);

        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    @Override
    public void loop() {

        if (gamepad1.right_trigger > 0.1) {
            shooter1.setPower(1);
            shooter2.setPower(1);
        } else {
            shooter1.setPower(0);
            shooter2.setPower(0);
        }

        // 3
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double turning = gamepad1.right_stick_x;

        if (gamepad1.b) {
            imu.resetYaw();
        }

        if (gamepad1.left_bumper) {
            IN.setPower(0.6);
        } else if (gamepad1.right_bumper) {
            IN.setPower(-0.6);
        } else {
            IN.setPower(0);
        }

        double angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double forward = x * Math.sin(-angle) + y * Math.cos(-angle);

        double sideways = x * Math.cos(-angle) - y * Math.sin(-angle);

        sideways = sideways * 1.1;

        double denom = Math.max(Math.abs(forward) + Math.abs(sideways) + Math.abs(turning), 1);

        // number
        FR.setPower((forward - turning - sideways) / denom);
        FL.setPower((forward + turning + sideways) / denom);
        BR.setPower((forward - turning + sideways) / denom);
        BL.setPower((forward + turning - sideways) / denom);

        //        FR.setPower((y - turning - x));
        //        FL.setPower((y + turning + x));
        //        BR.setPower((y - turning + x));
        //        BL.setPower((y + turning - x));

        telemetry.addData("heading", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
    }
}