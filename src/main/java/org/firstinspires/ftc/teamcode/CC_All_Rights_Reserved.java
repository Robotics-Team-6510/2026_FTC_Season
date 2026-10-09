package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "cc all rights reserved one gamepad")
public class CC_All_Rights_Reserved extends OpMode {
    // 1 - variables

    DcMotor FRwheel, FLwheel, BRwheel, BLwheel, Intake, Nectarshooter;
    DcMotorEx Pollenshooter;
    CRServo SFintake, MPOutake;
    IMU imu_called_bob;
    double drivePower;

    @Override
    public void init(){
        // 2 - link to config

        FRwheel = hardwareMap.get(DcMotor.class, "frw");
        FLwheel = hardwareMap.get(DcMotor.class, "flw");
        BRwheel = hardwareMap.get(DcMotor.class, "brw");
        BLwheel = hardwareMap.get(DcMotor.class, "blw");

        Intake = hardwareMap.get(DcMotor.class, "i");
        Pollenshooter = hardwareMap.get(DcMotorEx.class, "Ps");
       // Nectarshooter = hardwareMap.get(DcMotor.class, "Ns");

        SFintake = hardwareMap.get(CRServo.class, "SFi");
        MPOutake = hardwareMap.get(CRServo.class, "MPO");

        imu_called_bob = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu_called_bob.initialize(parameters);


        FRwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        BRwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        Intake.setDirection(DcMotorSimple.Direction.REVERSE);
        Pollenshooter.setDirection(DcMotorSimple.Direction.REVERSE);
        MPOutake.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    @Override
    public void loop(){
        // 3 - actual robot code

        double forwards = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double sideways = gamepad1.left_stick_x;

        double whatever_you_want_dont_type_in_whatever_you_want =  imu_called_bob.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotStrafe = sideways * Math.cos(-whatever_you_want_dont_type_in_whatever_you_want) - forwards * Math.sin(-whatever_you_want_dont_type_in_whatever_you_want);
        double rotForwards = sideways * Math.sin(-whatever_you_want_dont_type_in_whatever_you_want) + forwards * Math.cos(-whatever_you_want_dont_type_in_whatever_you_want);

        if (gamepad1.right_bumper){
            drivePower = 0.2;
        } else {
            drivePower = 1;
        }

        if (gamepad1.start){
            imu_called_bob.resetYaw();
        }

//        if (gamepad1.left_trigger>0.3) {
//            Intake.setPower(-0.3);
//        } else if (gamepad1.left_bumper){
//            Intake.setPower(-1);
//        } else if (gamepad1.a) {
//            Intake.setPower(0.7);
//        } else {
//            Intake.setPower(0);
//        }

        if (gamepad1.a){
            Intake.setPower(0.4);
        } else if (gamepad1.x){
            Intake.setPower(-1);
        } else {
            Intake.setPower(0);
        }

//        if (gamepad1.y) {
//            Nectarshooter.setPower(1);
//        } else if (gamepad1.x){
//            Nectarshooter.setPower(0);
//        }

        if (gamepad1.dpad_up )  {
            Pollenshooter.setVelocity(1780);
//            MPOutake.setPower(1);
        } else if (gamepad1.dpad_down) {
            Pollenshooter.setVelocity(0);
//            MPOutake.setPower(0);
        }

        if (gamepad1.left_bumper) {
            MPOutake.setPower(1);
        } else if (gamepad1.left_trigger > 0.3){
            MPOutake.setPower(-1);
        } else {
            MPOutake.setPower(0);
        }

        if (gamepad1.y) {
            SFintake.setPower(1);
        } else {
            SFintake.setPower(0);
        }



        FRwheel.setPower(drivePower*(rotForwards - turn - rotStrafe));
        FLwheel.setPower(drivePower*(rotForwards + turn + rotStrafe));
        BRwheel.setPower(drivePower*(rotForwards - turn + rotStrafe));
        BLwheel.setPower(drivePower*(rotForwards + turn - rotStrafe));


        telemetry.addData("robot heading", imu_called_bob.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("ticks",FRwheel.getCurrentPosition());
        telemetry.addData("ticks",FLwheel.getCurrentPosition());
        telemetry.addData("ticks",BLwheel.getCurrentPosition());
        telemetry.addData("ticks",BRwheel.getCurrentPosition());
        telemetry.addData("velcity", Pollenshooter.getVelocity());
        telemetry.update();

    }

    // @Autonomous so it shows up in the right area of the Driver Station
    // Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
    // Code with the same group name will be grouped together in the driver station
    @Autonomous(group = "Group Name")
    @Disabled //##### REMOVE THIS LINE #####
    public static class auto_cc_all_rights_reserved extends LinearOpMode {
        // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;

        @Override
        public void runOpMode() {
            // Run Once Here

            waitForStart();
            // Auto Sequence
        }

    }
}
