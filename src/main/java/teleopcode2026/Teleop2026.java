package teleopcode2026;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Drivetrain", group = "Robot")
public class Teleop2026 extends OpMode {
    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "front_left");
        rightFront = hardwareMap.get(DcMotor.class, "front_right");
        leftBack = hardwareMap.get(DcMotor.class, "back_left");
        rightBack = hardwareMap.get(DcMotor.class, "back_right");


        leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.FORWARD);

    }

    @Override
    public void loop() {

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double frontLeftPower = y + x + rx;
        double backLeftPower = y - x + rx;
        double frontRightPower = y - x - rx;
        double backRightPower = y + x - rx;


        leftFront.setPower(frontLeftPower);
        rightFront.setPower(frontRightPower);
        leftBack.setPower(backLeftPower);
        rightBack.setPower(backRightPower);

        telemetry.addData("joystick y (drive)", y);
        telemetry.addData("joystick x (strafe)", x);
        telemetry.addData("joystick rx (turn)", rx);

        telemetry.addData("motor power fL", frontLeftPower);
        telemetry.addData("motor power fR", frontRightPower);
        telemetry.addData("motor power bL", backLeftPower);
        telemetry.addData("motor power bR", backRightPower);

        telemetry.update();
    }

}


