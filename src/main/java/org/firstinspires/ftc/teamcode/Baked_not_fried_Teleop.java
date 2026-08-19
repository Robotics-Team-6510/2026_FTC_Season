package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class Baked_not_fried_Teleop extends OpMode {
    // 1 - declare variables

    DcMotor RF, RB, LF, LB, I;

    IMU imu;

    @Override
    public void init(){
        // 2 - configure motors

        RF = hardwareMap.get(DcMotor.class, "rf");
        RB = hardwareMap.get(DcMotor.class, "rb");
        LF = hardwareMap.get(DcMotor.class, "lf");
        LB = hardwareMap.get(DcMotor.class, "lb");
        I = hardwareMap.get(DcMotor.class, "i");

        imu = hardwareMap.get(IMU.class, "imu");



        RF.setDirection(DcMotorSimple.Direction.FORWARD);
        RB.setDirection(DcMotorSimple.Direction.FORWARD);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        imu.resetYaw();

    }

    @Override
    public void loop(){
        // 3 - acual drive code

        double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double forward = gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        double strafe = -gamepad1.left_stick_x;

        double rotStrafe = strafe * Math.cos(botHeading) - forward * Math.sin(botHeading);
        double rotForward = strafe * Math.sin(botHeading) + forward * Math.cos(botHeading);

        RF.setPower(rotForward - turn - rotStrafe);
        RB.setPower(rotForward - turn + rotStrafe);
        LF.setPower(rotForward + turn + rotStrafe);
        LB.setPower(rotForward + turn - rotStrafe);

        if (gamepad1.right_trigger > 0.1) {
            I.setPower(-1);
        } else {
            I.setPower(0);
        }

        if (gamepad1.left_trigger > 0.1) {
            I.setPower(1);
        } else {
            I.setPower(0);
        }

        if (gamepad1.options) {
            imu.resetYaw();
        }


    }


}
