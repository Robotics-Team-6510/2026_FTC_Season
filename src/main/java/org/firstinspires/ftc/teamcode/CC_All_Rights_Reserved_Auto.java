package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous
public class CC_All_Rights_Reserved_Auto extends LinearOpMode {
    // declare variables

    DcMotor FRwheel, FLwheel, BRwheel, BLwheel, SFintake, Intake, Pollenshooter, Nectarshooter;

    IMU imu_called_bob;
    double drivePower;


    @Override
    public void runOpMode() {
        // init = config

        FRwheel = hardwareMap.get(DcMotor.class, "frw");
        FLwheel = hardwareMap.get(DcMotor.class, "flw");
        BRwheel = hardwareMap.get(DcMotor.class, "brw");
        BLwheel = hardwareMap.get(DcMotor.class, "blw");

        Intake = hardwareMap.get(DcMotor.class, "i");
        SFintake = hardwareMap.get(DcMotor.class, "SFi");
        Pollenshooter = hardwareMap.get(DcMotor.class, "Ps");
        Nectarshooter = hardwareMap.get(DcMotor.class, "Ns");



        imu_called_bob = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu_called_bob.initialize(parameters);

        //hihihihio

        FLwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        BLwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        Intake.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        // actual code

        //shoot first 4 pollen
        Pollenshooter.setPower(1);
        sleep(100);
        Intake.setPower(1);
        sleep(300);
        Pollenshooter.setPower(0);

        //collect more pollen from garden
        turn(0.8, -1000);
        Intake.setPower(1);
        SFintake.setPower(1);
        forwards(0.8, 4);

        //shoot collected pollen
        SFintake.setPower(0);
        forwards(0.8, -4);
        turn(0.8,-1000);
        forwards(0.8, -10);
        Intake.setPower(0);
        Pollenshooter.setPower(1);
        Nectarshooter.setPower(1);
        sleep(100);
        Intake.setPower(1);
        sleep(300);
        Pollenshooter.setPower(0);
        Nectarshooter.setPower(0);

        //park
        Intake.setPower(0);
        turn(0.8, 1000);
        forwards(0.8, 4);
        turn(0.8, -1000);
        forwards(0.8, 1);

    }

    void forwards(double speed, int target_postition){

        FRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLwheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRwheel.setTargetPosition(target_postition*494);
        FLwheel.setTargetPosition(target_postition*494);
        BRwheel.setTargetPosition(target_postition*494);
        BLwheel.setTargetPosition(target_postition*494);

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

    void IMU_called_bobella(double target_heading) {

        double current_heading = imu_called_bob.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double difference = Math.abs(current_heading) - Math.abs(target_heading);

        FRwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (difference > 5){
            double power = (current_heading - target_heading)/180; // difference

            FRwheel.setPower(-power);
            FLwheel.setPower(power);
            BRwheel.setPower(-power);
            BLwheel.setPower(power);

        }

        FRwheel.setPower(0);
        FLwheel.setPower(0);
        BRwheel.setPower(0);
        BLwheel.setPower(0);



    }

    int multiply(int number){
        return number * number + number;
    }

}
