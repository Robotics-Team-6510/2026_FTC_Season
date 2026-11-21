package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "test",group="Other Samples")
public class Team_Name_Teleop_Code extends OpMode {
    // Declare Motors, Variables, and Functions
    private DcMotor fr, fl, br, bl, i1, i2, shooter;
    private IMU imu;

    // private DcMotor I1Motor;
    // All Config and Hardware Mapping
    @Override
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        i1 = hardwareMap.get(DcMotor.class, "i1");
        shooter = hardwareMap.get(DcMotor.class, "shtr");
        i2 = hardwareMap.get(DcMotor.class, "i2");

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
        imu.resetYaw();

        // One side of motors will always need to be reversed so that they all spin in the same direction.
        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        // It is good practise to set the mode to ensure consistency
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
        // if (gamepad2.right_bumper) {
        // Slow Button, if the Right Bumper on the second gamepad is being pressed, the robot will move slower
        // SpeedScaler = 0.25;
        // }

        // Calculate the power for each Motor, combine the 3 above commented sections, multiply by the Scaler
        double FRPower = (ForwardBackwards - Turn - LeftRight) * SpeedScaler;
        double FLPower = (ForwardBackwards + Turn + LeftRight) * SpeedScaler;
        double BRPower = (ForwardBackwards - Turn + LeftRight) * SpeedScaler;
        double BLPower = (ForwardBackwards + Turn - LeftRight) * SpeedScaler;

        // Set the power of the motors
        fr.setPower(FRPower);
        fl.setPower(FLPower);
        br.setPower(BRPower);
        bl.setPower(BLPower);

        telemetry.addData("hi", 1);
        telemetry.addData("what is 9 + 10?", 21);
        telemetry.addData("YAWWWWW", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();

        if (gamepad2.left_bumper) {
            i1.setPower(-1);
        } else if (gamepad2.right_bumper) {
            i1.setPower(1);
        } else {
            i1.setPower(0);
        }

        if (gamepad2.b) {
            shooter.setPower(-0.12);
        } else {
            shooter.setPower(0);
        }
        if (gamepad2.a) {
            i2.setPower(-1);
        } else if (gamepad2.x) {
            i2.setPower(1);
        } else {
            i2.setPower(0);

        }


    }
}

