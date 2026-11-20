package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class Baked_not_fried_Teleop extends OpMode {
    // 1 - declare variables

    DcMotor RF, RB, LF, LB, I, F;
    DcMotorEx FS, SS;

    Servo P;

    IMU imu;

    @Override
    public void init(){
        // 2 - configure motors

        RF = hardwareMap.get(DcMotor.class, "rf");
        RB = hardwareMap.get(DcMotor.class, "rb");
        LF = hardwareMap.get(DcMotor.class, "lf");
        LB = hardwareMap.get(DcMotor.class, "lb");
        I = hardwareMap.get(DcMotor.class, "i");
        F = hardwareMap.get(DcMotor.class, "f");
        FS = hardwareMap.get(DcMotorEx.class, "fs");
        SS = hardwareMap.get(DcMotorEx.class, "ss");
        P = hardwareMap.get(Servo.class,"p");

        imu = hardwareMap.get(IMU.class, "imu");

        RF.setDirection(DcMotorSimple.Direction.FORWARD);
        RB.setDirection(DcMotorSimple.Direction.FORWARD);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        F.setDirection(DcMotorSimple.Direction.REVERSE);

        FS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SS.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        imu.resetYaw();


        F.setDirection(DcMotorSimple.Direction.REVERSE);
        FS.setDirection(DcMotorSimple.Direction.REVERSE);
        SS.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop(){
        // 3 - actual drive code and field centric

        double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double forward = gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;

        double rotStrafe = strafe * Math.cos(botHeading) - forward * Math.sin(botHeading);
        double rotForward = strafe * Math.sin(botHeading) + forward * Math.cos(botHeading);

        double shooterSpeed = 1725;

        RF.setPower(rotForward - turn - rotStrafe);
        RB.setPower(rotForward - turn + rotStrafe);
        LF.setPower(rotForward + turn + rotStrafe);
        LB.setPower(rotForward + turn - rotStrafe);

        if(gamepad2.left_trigger > 0.1) {
            I.setPower(1);
        } else if(gamepad2.right_trigger > 0.1){
            I.setPower(-1);
        } else {
            I.setPower(0);
        }

        if(gamepad2.dpad_down){
            F.setPower(-1);
        } else if (gamepad2.dpad_up) {
            F.setPower(1);
        } else {
            F.setPower(0);
        }

        if (gamepad2.y) {
            FS.setVelocity(2200);
            SS.setVelocity(2400);
        } else {
            FS.setVelocity(0);
            SS.setVelocity(0);
        }

        if (gamepad2.a) {
            P.setPosition(0.7);
        } else {
            P.setPosition(0);
        }

        if (gamepad1.options) {
            imu.resetYaw();
        }
    }
}
