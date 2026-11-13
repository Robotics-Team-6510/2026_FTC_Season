package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class RICE_RobotCentric extends OpMode {

    DcMotor lf_motor, lb_motor, rf_motor, rb_motor;
    DcMotor intake;
    double drive_power;
    double best;

    // DcMotor should become DcMotorEx. You also need an encoder cable on the shooter, and then you can use "setVelocity" commands instead of setPower
    DcMotor shooter;
    Servo extension;
    DcMotor feeder;
    


    @Override
    public void init(){
        lf_motor = hardwareMap.get(DcMotor.class, "lf_motor");
        lb_motor = hardwareMap.get(DcMotor.class, "lb_motor");
        rf_motor = hardwareMap.get(DcMotor.class, "rf_motor");
        rb_motor = hardwareMap.get(DcMotor.class, "rb_motor");

        feeder = hardwareMap.get(DcMotor.class, "feeder");

        extension = hardwareMap.get(Servo.class, "extension");

        rb_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rf_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        best = 0;



        ////////////////////////////////////////////////////////
        intake = hardwareMap.get(DcMotor.class, "intake");

        shooter = hardwareMap.get(DcMotor.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    @Override
    public void loop() {



        double forward = -gamepad1.left_stick_y; // y
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x; // x


        lf_motor.setPower(drive_power*(forward + turn + strafe));
        lb_motor.setPower(drive_power*(forward + turn - strafe));
        rf_motor.setPower(drive_power*(forward - turn - strafe));
        rb_motor.setPower(drive_power*(forward - turn + strafe));

//        double current = gamepad2.right_trigger;
/////////////shooter////////
        if (gamepad2.x) {
            shooter.setPower(0.55);
        }
        else {
            shooter.setPower(0);
        }
        ///////////servo extension//////
        if (gamepad2.dpad_up) {
            extension.setPosition(0.5);
        } else if (gamepad2.dpad_down) {
            extension.setPosition(0);

        }
///////drive faster/////////////
        if (gamepad1.right_bumper) {
            drive_power = 0.8;
        } else {
            drive_power = 0.4;
        }

///////////intake///////////
        if (gamepad2.right_bumper) {
            intake.setPower(1);
        } ////////////outtake//////
        else if (gamepad2.left_bumper) {
            intake.setPower(-1);
        }
        else {
            intake.setPower(0);
        }

        ////////////////////////feeder code///////
        if (gamepad2.a) {
            feeder.setPower(1);
        }
        else {
            feeder.setPower(0);
        }

//////////////////////////////////outtake extra code///////
        /*if (current > best) {
            best = current;
        }

        if (gamepad2.b) {
            intake.setPower(-best);
        } else {
            intake.setPower(0);
        }


        if (gamepad2.a) {
            best = 0;
        }
//        if (gamepad2.a) {
//            intake.setPower(-0.8);
//            telemetry.update();
//        }
//        else {
//            intake.setPower(0);
//        }

        if  (gamepad2.dpadUpWasPressed()){
            best += 0.1;
        }
        if (gamepad2.dpadDownWasPressed()){
            best -=0.1;
        }*/


/////////////////telemetry code//////////


        telemetry.addData("intake", intake.getPower());
//        telemetry.update();

        telemetry.addData("extension_angle", extension.getPosition());
        telemetry.update();

    }
}
