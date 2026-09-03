package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous
public class autonomous_SFC1872 extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    private DcMotor frontLeft, frontRight, backRight, backLeft,intake,ontake2;

    private void setPowerAll(double motor_power) {
        frontLeft.setPower(motor_power);
        frontRight.setPower(motor_power);
        backLeft.setPower(motor_power);
        backRight.setPower(motor_power);
    }

    private void run_to_position() {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void stop_and_reset_encoder() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void runOpMode() {
        // Run Once Here
        frontLeft = hardwareMap.get(DcMotor.class, "frontleftwheel");
        frontRight = hardwareMap.get(DcMotor.class, "frontrightwheel");
        backLeft = hardwareMap.get(DcMotor.class, "backleftwheel");
        backRight = hardwareMap.get(DcMotor.class, "backrightwheel");
        intake = hardwareMap.get(DcMotor.class, "intake");
        ontake2 = hardwareMap.get(DcMotor.class,"ontake2");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        // Auto Sequence
        setPowerAll(0.3);
        frontLeft.setTargetPosition(1024);
        run_to_position();
        while(frontLeft.isBusy() || frontRight.isBusy()) {}
        stop_and_reset_encoder();

    }

}