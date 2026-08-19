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
    private DcMotor FrontRight, FrontLeft, BackLeft, BackRight, Intake;
    private IMU imu;

    @Override
    public void init(){
        // linking to config
        FrontRight = hardwareMap.get(DcMotor.class, "FRW");
        FrontLeft = hardwareMap.get(DcMotor.class, "FLW");
        BackLeft = hardwareMap.get(DcMotor.class, "BLW");
        BackRight = hardwareMap.get(DcMotor.class, "BRW");
        Intake = hardwareMap.get(DcMotor.class, "FF");

        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "IMU");
        RevHubOrientationOnRobot Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        IMU.Parameters Para = new IMU.Parameters(Orientation);
        imu.initialize(Para);



    }
    @Override
    public void loop(){
        // actual code

        double forward = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        double speed = 0.5;

        if (gamepad1.left_trigger_pressed) {
            speed = 0.85;
        } else if (gamepad1.right_trigger_pressed) {
            speed = 0.3;
        }

        if (gamepad1.options) {
            imu.resetYaw();
        }

        if (gamepad1.right_bumper) {
            Intake.setPower(0.6);
        } else if (gamepad1.left_bumper) {
            Intake.setPower(-0.6);
        } else {
            Intake.setPower(0);
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
