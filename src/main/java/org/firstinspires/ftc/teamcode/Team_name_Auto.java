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

        I1Motor.setPower(1);
        sleep(2000);
        I1Motor.setPower(0);

        driveForward(1000, 1);

    }

    public void driveForward(int targetPosition, double power){
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FRMotor.setTargetPosition(targetPosition);
        FLMotor.setTargetPosition(targetPosition);
        BRMotor.setTargetPosition(targetPosition);
        BLMotor.setTargetPosition(targetPosition);

        FRMotor.setPower(power);
        FLMotor.setPower(power);
        BRMotor.setPower(power);
        BLMotor.setPower(power);

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