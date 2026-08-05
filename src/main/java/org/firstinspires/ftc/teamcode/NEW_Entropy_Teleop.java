package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name="Entropy")
public class NEW_Entropy_Teleop extends OpMode {
    private DcMotor front_left, front_right, back_right, back_left;


    @Override
    public void init() {
        front_left = hardwareMap.get(DcMotor.class, "front_left");
        front_right = hardwareMap.get(DcMotor.class, "front_right");
        back_left = hardwareMap.get(DcMotor.class, "back_left");
        back_right = hardwareMap.get(DcMotor.class, "back_right");

        front_left.setDirection(DcMotorSimple.Direction.REVERSE);
        back_left.setDirection(DcMotorSimple.Direction.REVERSE);

        front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        double forward = gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        double strafe= gamepad1.left_stick_x;

        if(gamepad1.right_bumper) {
            front_left.setPower((forward + turn - strafe) * 0.5);
            front_right.setPower((forward - turn + strafe) * 0.5);
            back_left.setPower((forward + turn + strafe) * 0.5);
            back_right.setPower((forward - turn - strafe) * 0.5);
        } else {
            front_left.setPower(forward + turn - strafe);
            front_right.setPower(forward - turn + strafe);
            back_left.setPower(forward + turn + strafe);
            back_right.setPower(forward - turn - strafe);
        }

    }
}
