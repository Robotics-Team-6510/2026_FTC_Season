package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp (name="Entropy")
public class NEW_Entropy_Teleop extends OpMode {
    private DcMotor front_left, front_right, back_right, back_left, chelsea_is_so_67;

    private IMU imu;


    @Override
    public void init() {
        front_left = hardwareMap.get(DcMotor.class, "front_left");
        front_right = hardwareMap.get(DcMotor.class, "front_right");
        back_left = hardwareMap.get(DcMotor.class, "back_left");
        back_right = hardwareMap.get(DcMotor.class, "back_right");

        chelsea_is_so_67 = hardwareMap.get(DcMotor.class,"intake");

        front_right.setDirection(DcMotorSimple.Direction.REVERSE);
        back_right.setDirection(DcMotorSimple.Direction.REVERSE);

        front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        imu.initialize(parameters);

        imu.resetYaw();

    }

    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double x = gamepad1.left_stick_x;

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double strafe = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double forward = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        if(gamepad1.start){
            imu.resetYaw();
        }

        if(gamepad1.right_bumper) {
            front_left.setPower((forward + turn + strafe) * 0.5);
            front_right.setPower((forward - turn - strafe) * 0.5);
            back_left.setPower((forward + turn - strafe) * 0.5);
            back_right.setPower((forward - turn + strafe) * 0.5);
        } else {
            front_left.setPower((forward + turn + strafe));
            front_right.setPower((forward - turn - strafe));
            back_left.setPower((forward + turn - strafe));
            back_right.setPower((forward - turn + strafe));
        }

        if (gamepad1.left_stick_button) {
            chelsea_is_so_67.setPower(1);
        } else if (gamepad1.right_stick_button){
            chelsea_is_so_67.setPower(-1);
        }else
        {
            chelsea_is_so_67.setPower(0);
        }

        telemetry.addData("front left motor posit yo", front_left.getCurrentPosition());
        telemetry.addData("front right motor position", front_right.getCurrentPosition());
        telemetry.addData("back left motor position", back_left.getCurrentPosition());
        telemetry.addData("back right motor position", back_right.getCurrentPosition());
        telemetry.update();

    }
}
