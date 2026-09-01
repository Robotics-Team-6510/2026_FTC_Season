package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;


@Autonomous
//hi
public class Team_name_Auto extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    // 1 - var
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;
    private IMU imu;
    private DcMotor I1Motor;

    private double Threshold = 0.05;

    @Override
    public void runOpMode() {
        // INIT SECTION
        FRMotor = hardwareMap.get(DcMotor.class, "fr");
        FLMotor = hardwareMap.get(DcMotor.class, "fl");
        BRMotor = hardwareMap.get(DcMotor.class, "br");
        BLMotor = hardwareMap.get(DcMotor.class, "bl");
        I1Motor = hardwareMap.get(DcMotor.class, "i1");

        FRMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        FLMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLMotor.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();
        // Auto Sequence

        I1Motor.setPower(-1);
        sleep(2000);
        I1Motor.setPower(0);

        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRMotor.setTargetPosition(1000);
        FLMotor.setTargetPosition(1000);
        BRMotor.setTargetPosition(1000);
        BLMotor.setTargetPosition(1000);

        FRMotor.setPower(1);
        FLMotor.setPower(1);
        BRMotor.setPower(1);
        BLMotor.setPower(1);

        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FRMotor.isBusy()||FLMotor.isBusy() ||BRMotor.isBusy() || BLMotor.isBusy() ) {
            if(FRMotor.getPower() < Threshold && BRMotor.getPower() < Threshold &&
                    BLMotor.getPower() < Threshold && BRMotor.getPower() < Threshold) {
                break;
            }
        }


    }

}