package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class vibshubrobotcentric extends OpMode {
    // Declare Motors, Variables, and Functions
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;
    private DcMotor FIntakeMotor;
    private DcMotor feeder;
    private DcMotor shooter1;
    private DcMotor shooter2;

    // Retrieve the IMU from the hardware map
    private IMU imu;

    @Override
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        FRMotor = hardwareMap.get(DcMotor.class, "fr");
        FLMotor = hardwareMap.get(DcMotor.class, "fl");
        BRMotor = hardwareMap.get(DcMotor.class, "br");
        BLMotor = hardwareMap.get(DcMotor.class, "bl");

        FIntakeMotor = hardwareMap.get(DcMotor.class, "fi");
        shooter1 = hardwareMap.get(DcMotor.class, "bottoms");
        shooter2 = hardwareMap.get(DcMotor.class, "tops");
        feeder = hardwareMap.get(DcMotor.class, "feeder");

        // One side of motors will always need to be reversed so that they all spin in the same direction.
        FRMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        FLMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // It is good practise to set the mode to ensure consistency
        FRMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
        IMU.Parameters parameters = new IMU.Parameters(orientation);
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }

    @Override
    public void loop() {
        // Intake Controls
        if (gamepad2.left_trigger_pressed) {
            FIntakeMotor.setPower(1.0);
        } else if (gamepad2.right_trigger_pressed) {
            FIntakeMotor.setPower(-1.0);
        } else {
            FIntakeMotor.setPower(0.0);
        }

        if (gamepad2.b) {
            feeder.setPower(-1.0);
        } else if (gamepad2.a) {
            feeder.setPower(1);
        } else {
            feeder.setPower(0);
        }

        if (gamepad2.left_bumper) {
            shooter1.setPower(0.85);
            shooter2.setPower(-0.85);
        } else {
            shooter1.setPower(0);
            shooter2.setPower(0);
        }


        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        FLMotor.setPower(frontLeftPower);
        BLMotor.setPower(backLeftPower);
        FRMotor.setPower(frontRightPower);
        BRMotor.setPower(backRightPower);

//        FLMotor.setPower(0);
//        BLMotor.setPower(0);
//        FRMotor.setPower(0);
//        BRMotor.setPower(0);
    }
}