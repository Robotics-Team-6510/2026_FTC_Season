package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class field_centric extends OpMode {
    //naming motors
    private DcMotor tl, tr, bl, br;

    private IMU imu;
    //inertial measurement unit

    // DcMotor shartake;

    @Override
    public void init(){
        //configuring motors
        tl = hardwareMap.get(DcMotor.class, "tl");
        tr = hardwareMap.get(DcMotor.class, "tr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        // shartake = hardwareMap.get(DcMotor.class, "intake");

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
// Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        tl.setDirection(DcMotor.Direction.FORWARD);
        tr.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        imu.resetYaw();
    }
    @Override
    public void loop(){
        //telling motors what to do

        double y =gamepad1.left_stick_y; //y axis of field
        double turnright = gamepad1.right_stick_x; //x axis of field
            double x = gamepad1.left_stick_x;
        double yawning = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double strafe =x*Math.cos(yawning)-y*Math.sin(yawning);
        double forward =x*Math.sin(yawning)+y*Math.cos(yawning);



        tr.setPower(forward + turnright- strafe);
        tl.setPower(forward - turnright+strafe);
        br.setPower(forward + turnright- strafe);
        bl.setPower(forward - turnright+ strafe);


        if (gamepad1.aWasPressed()){
            tl.setPower((forward + turnright- strafe)/2);
            tr.setPower((forward - turnright+strafe)/2);
            bl.setPower((forward + turnright- strafe)/2);
            br.setPower((forward - turnright+ strafe)/2);
        }


        telemetry.addData("imu", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        //yaw turning
        //roll like a log, side to side
        //pitch aiming up or down like a pigeon
        telemetry.update();


    }

}
