package org.firstinspires.ftc.teamcode.sharkbeach;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

// @Autonomous so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@Autonomous(group = "Group Name")
@Disabled //##### REMOVE THIS LINE #####
public class autoblueleft extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    public DcMotor FR, FL, BR, BL;
     DcMotor intake, feeder;

     DcMotorEx shooter;

    IMU shark;


    @Override
    public void runOpMode() {
        // Run Once Here init
            // Declare Motors, Variables, and Functions

            // All Config and Hardware Mapping
                // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
                FR = hardwareMap.get(DcMotor.class, "tr");
                FL = hardwareMap.get(DcMotor.class, "tl");
                BR = hardwareMap.get(DcMotor.class, "br");
                BL = hardwareMap.get(DcMotor.class, "bl");
                intake = hardwareMap.get(DcMotor.class, "shartake");
                shooter = hardwareMap.get(DcMotorEx.class, "sharter");
                feeder = hardwareMap.get(DcMotor.class, "feed");

                shark = hardwareMap.get(IMU.class, "imu");

                IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
                shark.initialize(parameters);


                // One side of motors will always need to be reversed so that they all spin in the same direction.
                FR.setDirection(DcMotorSimple.Direction.FORWARD);
                FL.setDirection(DcMotorSimple.Direction.FORWARD);
                BR.setDirection(DcMotorSimple.Direction.FORWARD);
                BL.setDirection(DcMotorSimple.Direction.FORWARD);
                intake.setDirection(DcMotorSimple.Direction.FORWARD);
                shooter.setDirection(DcMotorSimple.Direction.FORWARD);
                feeder.setDirection(DcMotorSimple.Direction.FORWARD);

                // It is good practise to set the mode to ensure consistency
                FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                feeder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                shooter.setVelocityPIDFCoefficients(33,0,0,15.7);

        waitForStart();
                shooter.setVelocity(-1150);
                sleep (2000);
                intake.setPower(0.8);
                feeder.setPower(0.8);
                sleep (7000);
                shooter.setVelocity(0);
                intake.setPower(0);
                feeder.setPower(0);



    }


        // Auto Sequence
    }

