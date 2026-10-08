package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class season_teleop extends OpMode {
    private DcMotor front_left, front_right, back_right, back_left, intake, outtake;

    @Override
    public void init() {
        front_left = hardwareMap.get(DcMotor.class, "front_left");
        front_right = hardwareMap.get(DcMotor.class, "front_right");
        back_left = hardwareMap.get(DcMotor.class, "back_left");
        back_right = hardwareMap.get(DcMotor.class, "back_right");

        intake = hardwareMap.get(DcMotor.class, "intake");
        outtake = hardwareMap.get(DcMotor.class, "outtake");

        front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop() {

        double forward = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;

        if (gamepad1.right_bumper) {
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

        if (gamepad1.left_bumper) {
            intake.setPower(1);
        } else if (gamepad1.left_trigger > 0.1) {
            intake.setPower(-1);
        } else {
            intake.setPower(0);
        }

        if (gamepad1.right_bumper) {
            outtake.setPower(1);
        } else {
            outtake.setPower(0);
        }

    }
}