package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class RICE_FC extends OpMode {

    DcMotor lf_motor, lb_motor, rf_motor, rb_motor;
    IMU imu;
    DcMotor intake;
    double drive_power;
    double best;

    // DcMotor should become DcMotorEx. You also need an encoder cable on the shooter, and then you can use "setVelocity" commands instead of setPower
    DcMotorEx shooter;
    DcMotorEx shooter_2;
    Servo extension;
    DcMotor feeder;

    private final Controller shooterController = new Controller(c -> {
        c.staticFeedForwardSign = Controller.StaticFeedForwardSign.TARGET;
        c.kS = 0.12;
        c.kV = 0.000343;
        c.kP = 0.0023;
        c.kD = 0;
    });
    private final Controller shooter2Controller = new Controller(c -> {
        c.staticFeedForwardSign = Controller.StaticFeedForwardSign.TARGET;
        c.kS = 0.05;
        c.kV = 0.000382;
        c.kP = 0.0023;
        c.kD = 0;
    });

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

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu.initialize(parameters);


        ////////////////////////////////////////////////////////
        intake = hardwareMap.get(DcMotor.class, "intake");

        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        shooter_2 =hardwareMap.get(DcMotorEx.class, "shooter_2");

        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter_2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        shooter.setVelocityPIDFCoefficients(20,0,0,15);
//        shooter_2.setVelocityPIDFCoefficients(20,0,0,15);

    }
    @Override
    public void loop() {

        double robot_heading =  imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double forward = -gamepad1.left_stick_y; // y
        double turn = gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x; // x

        double rotForward = strafe * Math.sin(-robot_heading) + forward * Math.cos(-robot_heading);
        double rotStrafe = strafe * Math.cos(-robot_heading) - forward * Math.sin(-robot_heading);

        lf_motor.setPower(drive_power*(rotForward + turn + rotStrafe));
        lb_motor.setPower(drive_power*(rotForward + turn - rotStrafe));
        rf_motor.setPower(drive_power*(rotForward - turn - rotStrafe));
        rb_motor.setPower(drive_power*(rotForward - turn + rotStrafe));

        rb_motor.setZeroPowerBehavior(rb_motor.getZeroPowerBehavior());
        rf_motor.setZeroPowerBehavior(rb_motor.getZeroPowerBehavior());
        lb_motor.setZeroPowerBehavior(rb_motor.getZeroPowerBehavior());
        lf_motor.setZeroPowerBehavior(rb_motor.getZeroPowerBehavior());

//        double current = gamepad2.right_trigger;
/////////////shooter////////
        if (gamepad2.x) {
            shooterController.target = 1700;
            shooter2Controller.target = 1700;
        }
        else {
            shooterController.target = 0;
            shooter2Controller.target = 0;
        }
        ///////////servo extension//////
        if (gamepad1.dpad_down) {
            extension.setPosition(0.45);
        } else if (gamepad1.dpad_right) {
            extension.setPosition(0.1);

        } else if (gamepad1.dpad_up) {
            extension.setPosition(0.078);
        }
///////drive slower/////////////
        if (gamepad1.right_bumper) {
            drive_power = 0.8;
        } else {
            drive_power = 0.5;
        }
        if (gamepad1.start) {
            imu.resetYaw();
        }
///////////intake///////////
        if (gamepad2.right_bumper) {
            intake.setPower(1);
        } ////////////outtake//////
        else if (gamepad2.left_bumper) {
            intake.setPower(-1);
            feeder.setPower(-1);
        }
        else {
            intake.setPower(0);
            feeder.setPower(0);
        }

        ////////////////////////feeder code///////
        if (gamepad2.a) {
            feeder.setPower(1);
        }
        else {
            feeder.setPower(0);
        }

        double shooterVel = shooter.getVelocity();
        shooter.setPower(shooterController.calculate(shooterVel));

        double shooter2Vel = shooter_2.getVelocity();
        shooter_2.setPower(shooter2Controller.calculate(shooter2Vel));


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
        telemetry.addData("velocity_shooter", shooterVel);
        telemetry.addData("velocity_shooter2", shooter2Vel);
        telemetry.addData("imu", robot_heading);
       // telemetry.update();

        telemetry.addData("lf_motor",lf_motor.getPower());
        telemetry.addData("lb_motor",lb_motor.getPower());
        telemetry.addData("rf_motor",rf_motor.getPower());
        telemetry.addData("rb_motor",rb_motor.getPower());

        telemetry.addData("intake", intake.getPower());
//        telemetry.update();

        telemetry.addData("extension_angle", extension.getPosition());
        telemetry.update();

    }
}
