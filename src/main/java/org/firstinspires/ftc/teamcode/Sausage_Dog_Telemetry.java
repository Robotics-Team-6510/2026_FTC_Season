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

@TeleOp
public class Sausage_Dog_Telemetry extends OpMode {
    // section 1 - declaring variables - making variables
    private DcMotor FrontRight, FrontLeft, BackLeft, BackRight, FirstFeeder, Intake;
    private IMU imu;
    private DcMotorEx LeftShooter, RightShooter;



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


        imu = hardwareMap.get(IMU.class, "IMU");
        RevHubOrientationOnRobot Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        IMU.Parameters Para = new IMU.Parameters(Orientation);
        imu.initialize(Para);

    }
        @Override
        public void loop() {

            if (gamepad1.left_trigger_pressed) {
                Intake.setPower(-0.8);
            } else if (gamepad1.right_trigger_pressed) {
                Intake.setPower(0.8);
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
                LeftShooter.setVelocity(-0.5);
                RightShooter.setVelocity(0.5);
            } else if (gamepad1.b) {
                LeftShooter.setVelocity(0.5);
                RightShooter.setVelocity(-0.5);
            } else {
                LeftShooter.setVelocity(0);
                RightShooter.setVelocity(0);
            }


            // actual code
            telemetry.addData("Front Right Value: ", FrontRight.getCurrentPosition());
            telemetry.addData(" Front Left Value: ", FrontLeft.getCurrentPosition());
            telemetry.addData(" Back Right Value: ", BackRight.getCurrentPosition());
            telemetry.addData("  Back Left Value: ", BackLeft.getCurrentPosition());
            telemetry.addData(" Angle: ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.update();

        }
}
