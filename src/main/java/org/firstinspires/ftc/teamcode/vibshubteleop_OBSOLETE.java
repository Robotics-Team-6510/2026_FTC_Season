package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(group="vibshub")
public class vibshubteleop_OBSOLETE extends OpMode
{
    // Declare Motors, Variables, and Functions

    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    private IMU imu;


    // All Config and Hardware Mapping
    @Override
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        imu = hardwareMap.get(IMU.class,"imu");

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

        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT);
        IMU.Parameters parameters = new IMU.Parameters(orientation);
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }


    @Override
    public void loop() {
        // Collect Necessary Data from Gamepad in This Loop
        double ForwardBackwards = gamepad1.left_stick_y;
        double Turn = gamepad1.right_stick_x;
        double LeftRight = gamepad1.left_stick_x;

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        double rotX = ForwardBackwards * Math.cos(-botHeading) - LeftRight * Math.sin(-botHeading);
        double rotY = ForwardBackwards * Math.sin(-botHeading) + LeftRight * Math.cos(-botHeading);


        // Calculate the power for each Motor, combine the 3 above commented sections, multiply by the Scaler
        double FRPower = (rotY + Turn + rotX);
        double FLPower = (rotY -Turn -rotX);
        double BRPower = (rotY + Turn -rotX);
        double BLPower = (rotY -Turn + rotX);

        // Set the power of the motors
        frontRight.setPower(FRPower);
        frontLeft.setPower(FLPower);
        backRight.setPower(BRPower);
        backLeft.setPower(BLPower);
    }
}
