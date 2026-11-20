package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
//hi
public class Team_name_Auto extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    // 1 - var
    private DcMotor fr;
    private DcMotor fl;
    private DcMotor br;
    private DcMotor bl;
    private IMU imu;
    private DcMotor i1;

    private double Threshold = 0.05;

    @Override
    public void runOpMode() {
        // INIT SECTION
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        i1 = hardwareMap.get(DcMotor.class, "i1");

        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);


        waitForStart();
        // Auto Sequence

//        fl.setTargetPosition(1024);
//        fr.setTargetPosition(1024);
//        bl.setTargetPosition(1024);
//        br.setTargetPosition(1024);
//        fl.setPower(0.3);
//        fr.setPower(0.3);
//        bl.setPower(0.3);
//        br.setPower(0.3);
        drive(1024, 0.3);




    }

    public void drive(int targetPosition, double power){
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fr.setTargetPosition(targetPosition);
        fl.setTargetPosition(targetPosition);
        br.setTargetPosition(targetPosition);
        bl.setTargetPosition(targetPosition);

        fr.setPower(power);
        fl.setPower(power);
        br.setPower(power);
        bl.setPower(power);

        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (fr.isBusy()||fl.isBusy() ||br.isBusy() || bl.isBusy() ) {
            if(fr.getPower() < Threshold && br.getPower() < Threshold &&
                    bl.getPower() < Threshold && br.getPower() < Threshold) {
                break;
            }
        }
    }
}