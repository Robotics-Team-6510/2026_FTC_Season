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
    // 1 - varibale definition

    int mynamebob;

    DcMotor FR;
    DcMotor FL;
    DcMotor BR;
    DcMotor BL;

    IMU imu;

    @Override
    public void init(){
        // 2
        FR = hardwareMap.get(DcMotor.class, "front right");
        FL = hardwareMap.get(DcMotor.class, "front left");
        BL = hardwareMap.get(DcMotor.class, "back left");
        BR = hardwareMap.get(DcMotor.class, "back right");

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
    public void loop(){
        // 3
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double turning = gamepad1.right_stick_x;

        if(gamepad1.b){
            imu.resetYaw();
        }

        double angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double forward = x * Math.sin(-angle) + y * Math.cos(-angle);

        double sideways = x * Math.cos(-angle) - y * Math.sin(-angle);

        sideways = sideways * 1.1;

        double denom = Math.max(Math.abs(forward) + Math.abs(sideways) + Math.abs(turning),1);

        // number
        FR.setPower((forward - turning - sideways) / denom);
        FL.setPower((forward + turning + sideways) / denom);
        BR.setPower((forward - turning + sideways) / denom);
        BL.setPower((forward + turning - sideways) / denom);

//        FR.setPower((y - turning - x));
//        FL.setPower((y + turning + x));
//        BR.setPower((y - turning + x));
//        BL.setPower((y + turning - x));

        telemetry.addData("caption", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();


    }

}
