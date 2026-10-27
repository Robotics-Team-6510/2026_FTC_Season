
package org.firstinspires.ftc.teamcode.sharkbeach;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// Adapted from Basic_Robot_Centric.java
@TeleOp
public class teleop extends OpMode
{
    // Declare Motors, Variables, and Functions
    private DcMotor FR, FL, BR, BL;
    private DcMotor intake;

    private DcMotorEx shooter;


    // All Config and Hardware Mapping
    @Override
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        FR = hardwareMap.get(DcMotor.class, "tr");
        FL = hardwareMap.get(DcMotor.class, "tl");
        BR = hardwareMap.get(DcMotor.class, "br");
        BL = hardwareMap.get(DcMotor.class, "bl");
        intake = hardwareMap.get(DcMotor.class, "shartake");
        shooter = hardwareMap.get(DcMotorEx.class, "sharter");


        // One side of motors will always need to be reversed so that they all spin in the same direction.
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        FL.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);

        // It is good practise to set the mode to ensure consistency
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter.setVelocityPIDFCoefficients(33,0,0,15.7);
    }


    @Override
    public void loop() {
        // Collect Necessary Data from Gamepad in This Loop
        double ForwardBackwards = gamepad2.left_stick_y;
        double Turn = gamepad2.right_stick_x;
        double LeftRight = gamepad2.left_stick_x;

        // For Speed Scaling
        double SpeedScaler = 0.75;

        // Example of Second Gamepad functioning in code
        if(gamepad2.right_bumper) {
            // Slow Button, if the Right Bumper on the second gamepad is being pressed, the robot will move slower
            SpeedScaler = 0.25;
        }

        // Calculate the power for each Motor, combine the 3 above commented sections, multiply by the Scaler
        double FRPower = (ForwardBackwards + Turn + LeftRight) * SpeedScaler;
        double FLPower = (ForwardBackwards -Turn -LeftRight) * SpeedScaler;
        double BRPower = (ForwardBackwards + Turn -LeftRight) * SpeedScaler;
        double BLPower = (ForwardBackwards -Turn + LeftRight) * SpeedScaler;

        // Set the power of the motors
        FR.setPower(FRPower);
        FL.setPower(FLPower);
        BR.setPower(BRPower);
        BL.setPower(BLPower);
        if(gamepad2.left_bumper) {
            // Slow Button, if the Right Bumper on the second gamepad is being pressed, the robot will move slower
            intake.setPower(0.8);
        } else if (gamepad2.left_trigger>0.5){
            intake.setPower(-0.8);
        } else {
            intake.setPower(0);
        }
        if(gamepad2.right_bumper) {
            // Slow Button, if the Right Bumper on the second gamepad is being pressed, the robot will move slower
            shooter.setVelocity(500);
        } else if (gamepad2.right_trigger>0.5) {
            shooter.setVelocity(0);
        }

        telemetry.addData("current velocity of shooter", shooter.getVelocity());
        telemetry.update();


    }
}
