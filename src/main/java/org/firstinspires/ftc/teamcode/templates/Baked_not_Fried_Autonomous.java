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
@Autonomous(group = "Baked_not_Fried_Autonomous")

public class Baked_not_Fried_Autonomous extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;


    private DcMotor RF, RB, LF, LB, I, F, Out;

    public void move(int distance, double speed) {
        // Run Once Here

        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RF.setTargetPosition(distance);
        RB.setTargetPosition(distance);
        LF.setTargetPosition(distance);
        LB.setTargetPosition(distance);

        RF.setPower(speed);
        RB.setPower(speed);
        LF.setPower(speed);
        LB.setPower(speed);

        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while(RF.isBusy() || RB.isBusy() || LF.isBusy() || LB.isBusy()) {//  Run Once Here

            waitForStart();
            // Auto Sequence
        }







        waitForStart();
        // Auto Sequence
    }

    @Override
    public void runOpMode(){
        RF = hardwareMap.get(DcMotor.class, "rf");
        RB = hardwareMap.get(DcMotor.class, "rb");
        LF = hardwareMap.get(DcMotor.class, "lf");
        LB = hardwareMap.get(DcMotor.class, "lb");
        I = hardwareMap.get(DcMotor.class, "i");
        F = hardwareMap.get(DcMotor.class, "f");
        Out = hardwareMap.get(DcMotor.class, "out");

        RF.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);

        move(1000, 10.5);
        

    }
}