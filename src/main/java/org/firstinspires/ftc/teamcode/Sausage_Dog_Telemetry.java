package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotserver.internal.programmingmode.ProgrammingModeWebHandlerDecorator;

@TeleOp
public class Sausage_Dog_Telemetry extends OpMode {
    // section 1 - declaring variables - making variables
    private DcMotor FrontRight, FrontLeft, BackLeft, BackRight, FirstFeeder, Intake;
    private IMU imu;
    private DcMotorEx LeftShooter, RightShooter;

    double speed = 1;

    @Override
    public void init() {
        // linking to config
        FrontRight = hardwareMap.get(DcMotor.class, "FRW");
        FrontLeft = hardwareMap.get(DcMotor.class, "FLW");
        BackLeft = hardwareMap.get(DcMotor.class, "BLW");
        BackRight = hardwareMap.get(DcMotor.class, "BRW");
        FirstFeeder = hardwareMap.get(DcMotor.class, "FF");
        LeftShooter = hardwareMap.get(DcMotorEx.class, "LS");
        RightShooter = hardwareMap.get(DcMotorEx.class, "RS");
        Intake = hardwareMap.get(DcMotor.class, "IN");


    FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    FrontRight.setDirection(DcMotorSimple.Direction.FORWARD);
    BackRight.setDirection(DcMotorSimple.Direction.FORWARD);

    LeftShooter.setDirection(DcMotorSimple.Direction.REVERSE);
    RightShooter.setDirection(DcMotorSimple.Direction.FORWARD);


    LeftShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    RightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        imu = hardwareMap.get(IMU.class, "IMU");
        RevHubOrientationOnRobot Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        IMU.Parameters Para = new IMU.Parameters(Orientation);
        imu.initialize(Para);

    }
        @Override
        public void loop() {

            if (gamepad1.left_trigger_pressed) {
                Intake.setPower(0.8);
            } else if (gamepad1.right_trigger_pressed) {
                Intake.setPower(-0.8);
            } else {
                Intake.setPower(0);
            }

            if (gamepad1.left_bumper) {
                FirstFeeder.setPower(1);
            } else if (gamepad1.right_bumper) {
                FirstFeeder.setPower(-1);
            } else {
                FirstFeeder.setPower(0);
            }

            if (gamepad1.a) {
                LeftShooter.setVelocity(0.5);
                RightShooter.setVelocity(0.5);
            } else if (gamepad1.b) {
                LeftShooter.setVelocity(-0.5);
                RightShooter.setVelocity(-0.5);
            } else {
                LeftShooter.setVelocity(0);
                RightShooter.setVelocity(0);
            }
            if (gamepad1.options) {
                imu.resetYaw();
            }
            double forward = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;

            double angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double x = strafe * Math.cos(-angle) - forward * Math.sin(-angle);
            double y = strafe * Math.sin(-angle) + forward * Math.cos(-angle);
            x = x * 1.1;

            double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(turn), 1);

            FrontLeft.setPower((y + turn + x) / denominator * speed);
            FrontRight.setPower((y - turn - x) / denominator * speed);
            BackLeft.setPower((y + turn - x) / denominator * speed);
            BackRight.setPower((y - turn + x) / denominator * speed);

            // actual code
            telemetry.addData("Front Right Value: ", FrontRight.getCurrentPosition());
            telemetry.addData(" Front Left Value: ", FrontLeft.getCurrentPosition());
            telemetry.addData(" Back Right Value: ", BackRight.getCurrentPosition());
            telemetry.addData("  Back Left Value: ", BackLeft.getCurrentPosition());
            telemetry.addData(" Angle: ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.update();

        }
}
