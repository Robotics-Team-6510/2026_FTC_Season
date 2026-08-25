package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(group="Other Samples")
public class Team_Name_Centric_Code extends OpMode {
    // Declare Motors, Variables, and Functions
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;
    private IMU imu;
    private DcMotor I1Motor;


    public int bob;

    // storage location || Class || Name
    // Private || DcMotor || BLMotor

    // All Config and Hardware Mapping
    @Override
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        FRMotor = hardwareMap.get(DcMotor.class, "fr");
        FLMotor = hardwareMap.get(DcMotor.class, "fl");
        BRMotor = hardwareMap.get(DcMotor.class, "br");
        BLMotor = hardwareMap.get(DcMotor.class, "bl");
        I1Motor = hardwareMap.get(DcMotor.class, "i1");


        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
        imu.resetYaw();

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
    }


    @Override
    public void loop() {
        // Collect Necessary Data from Gamepad in This Loop
        double y = -gamepad1.left_stick_y; // Y_1
        double Turn = gamepad1.right_stick_x;
        double x = gamepad1.left_stick_x; // X_1

        double yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS); // 360* = 2*Pi, 180 = Pi, 90 = Pi/1

        double ForwardBackwards = x * Math.sin(-yaw) + y * Math.cos(-yaw);
        double LeftRight = x * Math.cos(-yaw) - y * Math.sin(-yaw);


        // For Speed Scaling
        double SpeedScaler = 0.75;

        // Example of Second Gamepad functioning in code
        if (gamepad2.right_bumper) {
            // Slow Button, if the Right Bumper on the second gamepad is being pressed, the robot will move slower
            SpeedScaler = 0.25;
        }


        // Calculate the power for each Motor, combine the 3 above commented sections, multiply by the Scaler
        double FRPower = (ForwardBackwards - Turn - LeftRight) * SpeedScaler;
        double FLPower = (ForwardBackwards + Turn + LeftRight) * SpeedScaler;
        double BRPower = (ForwardBackwards - Turn + LeftRight) * SpeedScaler;
        double BLPower = (ForwardBackwards + Turn - LeftRight) * SpeedScaler;

        // Set the power of the motors
        FRMotor.setPower(FRPower);
        FLMotor.setPower(FLPower);
        BRMotor.setPower(BRPower);
        BLMotor.setPower(BLPower);

        telemetry.addData("what is 9 + 10?", 21);
        telemetry.addData("YAWWWWW", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();

        if (gamepad1.left_bumper) {
            I1Motor.setPower(-1);
        } else if (gamepad1.right_bumper) {
            I1Motor.setPower(1);
        }
        else {
            I1Motor.setPower(0);


        }
    }

}

