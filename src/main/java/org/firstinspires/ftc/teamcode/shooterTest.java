package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

// @TeleOp so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@TeleOp(group="Group Name")
public class shooterTest extends OpMode
{
    private DcMotor tops, bottoms;

    @Override
    public void init() {
        tops = hardwareMap.get(DcMotor.class, "tops");
        bottoms = hardwareMap.get(DcMotor.class, "bottoms");


        tops.setDirection(DcMotor.Direction.REVERSE);
        bottoms.setDirection(DcMotor.Direction.FORWARD);

        tops.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottoms.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    // Code to run repeatedly during TeleOp
    @Override
    public void loop() {
        if (gamepad1.left_bumper){
            tops.setPower(1);
            bottoms.setPower(1);
        } else {
            tops.setPower(0);
            bottoms.setPower(0);
        }
    }
}


