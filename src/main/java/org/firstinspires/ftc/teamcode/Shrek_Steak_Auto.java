package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;


@Autonomous
public class Shrek_Steak_Auto extends LinearOpMode {

    private DcMotor FrontRightMotor;
    private DcMotor FrontLeftMotor;
    private DcMotor BackRightMotor;
    private DcMotor BackLeftMotor;
    private DcMotor IntakeMotor;
//    private DcMotor FeederMotor;
    private IMU Gyro;


    @Override
    public void runOpMode() {
        // Run Once Here
        FrontRightMotor = hardwareMap.get(DcMotor.class, "fr");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "fl");
        BackRightMotor = hardwareMap.get(DcMotor.class, "br");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "bl");
        IntakeMotor = hardwareMap.get(DcMotor.class,  "intake");
//        FeederMotor = hardwareMap.get(DcMotor.class, "feeder");
        Gyro = hardwareMap.get(IMU.class,"imu");

        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        IntakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        FeederMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        RevHubOrientationOnRobot Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);
        IMU.Parameters Parameter = new IMU.Parameters(Orientation);
        Gyro.initialize(Parameter);

        waitForStart();
        // Auto Sequence

        FrontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FrontLeftMotor.setTargetPosition(1000);
        FrontRightMotor.setTargetPosition(1000);
        BackLeftMotor.setTargetPosition(1000);
        BackRightMotor.setTargetPosition(1000);

        FrontLeftMotor.setPower(1);
        FrontRightMotor.setPower(1);
        BackLeftMotor.setPower(1);
        BackRightMotor.setPower(1);

        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BackRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);




    }

}