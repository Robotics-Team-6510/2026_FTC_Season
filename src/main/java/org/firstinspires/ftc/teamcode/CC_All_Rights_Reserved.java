package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "a name- cc all rights reserved")
public class CC_All_Rights_Reserved extends OpMode {
    // 1 - variables

    DcMotor FRwheel, FLwheel, BRwheel, BLwheel, Fintake ;

    IMU imu_called_bob;
    double drivePower;

    @Override
    public void init(){
        // 2 - link to config

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

        FRwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        BRwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        Fintake.setDirection(DcMotorSimple.Direction.REVERSE);

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

        if (gamepad1.left_trigger>0.3) {
            Fintake.setPower(-0.3);
        } else if (gamepad1.left_bumper){
            Fintake.setPower(-1);
        } else if (gamepad1.a) {
            Fintake.setPower(0.7);
        } else {
            Fintake.setPower(0);
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
