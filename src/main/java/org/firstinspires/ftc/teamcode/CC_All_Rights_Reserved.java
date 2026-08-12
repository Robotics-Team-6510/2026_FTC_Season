package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
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

        FLwheel.setDirection(DcMotorSimple.Direction.REVERSE);
        BLwheel.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    @Override
    public void loop(){
        // 3 - actual robot code

        double forwards = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;
        double sideways = -gamepad1.left_stick_x;

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

        if (gamepad1.a){
            Fintake.setPower(0.35);
        } else if (gamepad1.b) {
            Fintake.setPower(-1);
        } else{
            Fintake.setPower(0);
        }


        FRwheel.setPower(drivePower*(rotForwards + turn - rotStrafe));
        FLwheel.setPower(drivePower*(rotForwards - turn + rotStrafe));
        BRwheel.setPower(drivePower*(rotForwards + turn + rotStrafe));
        BLwheel.setPower(drivePower*(rotForwards - turn - rotStrafe));


        telemetry.addData("robot heading", imu_called_bob.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();
    }
}
