package org.firstinspires.ftc.teamcode.templates;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

// @Autonomous so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@Autonomous(group = "Entropy")
public class Auto extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    DcMotor front_left, front_right, back_right, back_left, chelsea_is_so_67;

    @Override
    public void runOpMode() {
        // Run Once Here

        front_left = hardwareMap.get(DcMotor.class, "front_left");
        front_right = hardwareMap.get(DcMotor.class, "front_right");
        back_left = hardwareMap.get(DcMotor.class, "back_left");
        back_right = hardwareMap.get(DcMotor.class, "back_right");

        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        chelsea_is_so_67=hardwareMap.get(DcMotor.class,"intake");

        front_right.setDirection(DcMotorSimple.Direction.REVERSE);
        back_right.setDirection(DcMotorSimple.Direction.REVERSE);

        front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int ticks = 450;


        waitForStart();
        driveTurnRight(ticks);

        // Auto Sequence
    }

    public void driveForward(int ticks){
        front_left.setTargetPosition(ticks);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        front_right.setTargetPosition(ticks);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        back_left.setTargetPosition(ticks);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        back_right.setTargetPosition(ticks);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void driveTurnRight(int ticks){
        front_left.setTargetPosition(ticks);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        front_right.setTargetPosition(ticks);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        back_left.setTargetPosition(-ticks);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        back_right.setTargetPosition(-ticks);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    public void driveTurnLeft(int ticks){
        front_left.setTargetPosition(-ticks);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        front_right.setTargetPosition(-ticks);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        back_left.setTargetPosition(ticks);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        back_right.setTargetPosition(ticks);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }



}