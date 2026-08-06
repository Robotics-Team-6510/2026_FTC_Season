package org.firstinspires.ftc.teamcode.templates;
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
@Disabled //##### REMOVE THIS LINE #####
public class  Template_Teleop extends OpMode
{
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;


    // Code that runs when you press "INIT" on the Driver Station, runs once.
    // Use this space for hardware mapping, motor configuration, etc.
    @Override
    public void init() {
    }

    // Code to run repeatedly during TeleOp
    @Override
    public void loop() {

    }
}
