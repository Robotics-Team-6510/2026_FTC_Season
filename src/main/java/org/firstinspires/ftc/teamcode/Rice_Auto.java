package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// @Autonomous so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@Autonomous
public class Rice_Auto extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    // 1
    DcMotor lf_motor, lb_motor, rf_motor, rb_motor;
    DcMotor intake;
    @Override

    public void runOpMode() {
        // Run Once Here
        // 2 - init

        lf_motor = hardwareMap.get(DcMotor.class, "lf_motor");
        lb_motor = hardwareMap.get(DcMotor.class, "lb_motor");
        rf_motor = hardwareMap.get(DcMotor.class, "rf_motor");
        rb_motor = hardwareMap.get(DcMotor.class, "rb_motor");

        rb_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rf_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        intake = hardwareMap.get(DcMotor.class, "intake");

        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        // Auto Sequence
        // 3 - auto code

        intake.setPower(-1);
        sleep(5000);
        intake.setPower(0);

        forward(1000,1);





    }

    // type of data returned   name(inputs) {}
    int square(int bob, double jeff){
        return bob*bob+1;
    }

    void forward(int distance, double power){
        lf_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lf_motor.setTargetPosition(distance); // TICKS
        lb_motor.setTargetPosition(distance);
        rf_motor.setTargetPosition(distance);
        rb_motor.setTargetPosition(distance);

        lf_motor.setPower(power);
        lb_motor.setPower(power);
        rf_motor.setPower(power);
        rb_motor.setPower(power);

        lf_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (lf_motor.isBusy()) {  }

    }

}