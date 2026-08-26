package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
public class Challenge_5_Wayfinder extends LinearOpMode {
    // declare varisbles

    DcMotor FRwheel, FLwheel, BRwheel, BLwheel, Fintake ;

    IMU imu_called_bob;
    double drivePower;


    @Override
    public void runOpMode() {
        // init = config

        FRwheel = hardwareMap.get(DcMotor.class, "frw");
        FLwheel = hardwareMap.get(DcMotor.class, "flw");
        BRwheel = hardwareMap.get(DcMotor.class, "brw");
        BLwheel = hardwareMap.get(DcMotor.class, "blw");

        Fintake = hardwareMap.get(DcMotor.class, "Ff");



        imu_called_bob = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu_called_bob.initialize(parameters);

        //hihihihio

        FLwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        BLwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        Fintake.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        // actual code

        forwards(0.4,2000);
        turn(0.4,1000);
        forwards(0.4,4000);
        turn(0.4,1000);
        forwards(0.4,4000);
        turn(0.4,1000);
        forwards(0.4,4000);
        turn(0.4,1000);
        forwards(0.4,2000);




    }

    void forwards(double speed, int target_postition){

        FRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRwheel.setTargetPosition(target_postition);
        FLwheel.setTargetPosition(target_postition);
        BRwheel.setTargetPosition(target_postition);
        BLwheel.setTargetPosition(target_postition);

        FRwheel.setPower(speed);
        FLwheel.setPower(speed);
        BRwheel.setPower(speed);
        BLwheel.setPower(speed);

        FRwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FRwheel.isBusy()){}



    }


    void turn(double speed, int target_postition){

        FRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRwheel.setTargetPosition(target_postition);
        FLwheel.setTargetPosition(-target_postition);
        BRwheel.setTargetPosition(target_postition);
        BLwheel.setTargetPosition(-target_postition);

        FRwheel.setPower(speed);
        FLwheel.setPower(speed);
        BRwheel.setPower(speed);
        BLwheel.setPower(speed);

        FRwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLwheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FRwheel.isBusy()){}





    }

    int multiply(int number){
        return number * number + number;
    }

}
