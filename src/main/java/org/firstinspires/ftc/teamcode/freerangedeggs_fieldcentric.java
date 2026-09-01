package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class freerangedeggs_fieldcentric extends OpMode {
    // Declare Motors, Variables, and Functions
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    CRServo servo1, tom;

    private IMU imu;

    private DcMotor intake;



    // Retrieve the IMU from the hardware map



    @Override
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        intake = hardwareMap.get(DcMotor.class, "intake");


        tom = hardwareMap.get(CRServo.class, "sr");
        servo1 = hardwareMap.get(CRServo.class, "l");

        // One side of motors will always need to be reversed so that they all spin in the same direction.
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        // It is good practise to set the mode to ensure consistency
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        IMU.Parameters parameters = new IMU.Parameters(orientation);
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

    }

    @Override
    public void loop() {
        // Collect Necessary Data from Gamepad in This Loop
        double y = -gamepad1.left_stick_y;
        double Turn = gamepad1.right_stick_x;
        double x = gamepad1.left_stick_x;

        double botheading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botheading) - y * Math.sin(-botheading);
        double rotY = x * Math.sin(-botheading) + y * Math.cos(-botheading);

        // For Speed Scaling
        double SpeedScaler = 0.75;

        // Example of Second Gamepad functioning in code
        if(gamepad2.right_bumper) {
            // Slow Button, if the Right Bumper on the second gamepad is being pressed, the robot will move slower
            SpeedScaler = 0.5;
        }

        if (gamepad1.options) {
            imu.resetYaw();
        }

        // Calculate the power for each Motor, combine the 3 above commented sections, multiply by the Scaler
        double FRPower = (rotY - Turn - rotX) * SpeedScaler;
        double FLPower = (rotY +Turn + rotX) * SpeedScaler;
        double BRPower = (rotY - Turn +rotX) * SpeedScaler;
        double BLPower = (rotY +Turn - rotX) * SpeedScaler;

        // Set the power of the motors
        frontRight.setPower(FRPower);
        frontLeft.setPower(FLPower);
        backRight.setPower(BRPower);
        backLeft.setPower(BLPower);

        if (gamepad1.left_bumper) {
            intake.setPower(1);
            tom.setPower(1);
            servo1.setPower(1);
        } else if (gamepad1.left_trigger > 0) {
            intake.setPower(-0.45);
        } else {
            intake.setPower(0);
            tom.setPower(0);
            servo1.setPower(0);
        }






        telemetry.addData("heading", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("right front motors position yall", frontRight.getCurrentPosition());
        telemetry.addData("left front motors position yall",frontLeft.getCurrentPosition());
        telemetry.addData("back left motors position yall",backLeft.getCurrentPosition());
        telemetry.addData("back right motors position yall",backRight.getCurrentPosition());
        telemetry.update();


    }

}