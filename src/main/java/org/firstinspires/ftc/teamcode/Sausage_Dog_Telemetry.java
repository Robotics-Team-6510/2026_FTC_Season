package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotserver.internal.programmingmode.ProgrammingModeWebHandlerDecorator;

@TeleOp
public class Sausage_Dog_Telemetry extends OpMode {
    // section 1 - declaring variables - making variables
    private DcMotor FrontRight, FrontLeft, BackLeft, BackRight, FirstFeeder, Intake;
    private IMU imu;
    private DcMotorEx LeftShooter, RightShooter;

    private CRServo LeftSide, RightSide;

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
        LeftSide = hardwareMap.get(CRServo.class, "LSW");
        RightSide = hardwareMap.get(CRServo.class, "RSW");



    FrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
    BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
    FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    BackRight.setDirection(DcMotorSimple.Direction.REVERSE);

    LeftShooter.setDirection(DcMotorSimple.Direction.FORWARD);
    RightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

    FirstFeeder.setDirection(DcMotorSimple.Direction.FORWARD);

    Intake.setDirection(DcMotorSimple.Direction.REVERSE);

    LeftSide.setDirection(DcMotorSimple.Direction.REVERSE);
    RightSide.setDirection(DcMotorSimple.Direction.FORWARD);




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
                LeftSide.setPower(1);
                RightSide.setPower(1);
            } else if (gamepad1.right_trigger_pressed) {
                Intake.setPower(-0.8);
            } else {
                Intake.setPower(0);
                LeftSide.setPower(0);
                RightSide.setPower(0);
            }

            if (gamepad1.left_bumper) {
                FirstFeeder.setPower(1);
            } else if (gamepad1.right_bumper) {
                FirstFeeder.setPower(-1);
            } else {
                FirstFeeder.setPower(0);
            }

            if (gamepad1.a) {
                LeftShooter.setVelocity(800);
                RightShooter.setVelocity(800);
            } else if (gamepad1.b) {
                LeftShooter.setVelocity(-800);
                RightShooter.setVelocity(-800);
            } else {
                LeftShooter.setVelocity(0);
                RightShooter.setVelocity(0);
            }

            if (gamepad1.x){

            }
            if (gamepad1.options) {
                imu.resetYaw();
            }

            if (gamepad2.leftTriggerWasPressed()) {
                speed += 0.1;
                if (speed > 1) speed = 1;
            } else if (gamepad2.rightTriggerWasPressed()) {
                speed -= 0.1;
                if (speed < 0.1) speed = 0.1;
            } else if (gamepad2.y) {
                speed = 0.85;
            } else if (gamepad2.right_trigger_pressed) {
                speed = 0.3;
            }

            double forward = -gamepad2.left_stick_y;
            double turn = gamepad2.right_stick_x;
            double strafe = gamepad2.left_stick_x;

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
