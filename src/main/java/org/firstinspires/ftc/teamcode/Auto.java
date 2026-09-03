package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// @Autonomous so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@Autonomous(group = "Entropy")
public class Auto extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    DcMotor front_left, front_right, back_right, back_left, chelsea_is_so_67;

    @Override
    public void runOpMode() {
        // Run Once Here

        front_left = hardwareMap.get(DcMotor.class, "front_left");
        front_right = hardwareMap.get(DcMotor.class, "front_right");
        back_left = hardwareMap.get(DcMotor.class, "back_left");
        back_right = hardwareMap.get(DcMotor.class, "back_right");

        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        chelsea_is_so_67=hardwareMap.get(DcMotor.class,"intake");

        front_right.setDirection(DcMotorSimple.Direction.REVERSE);
        back_right.setDirection(DcMotorSimple.Direction.REVERSE);

        front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int ticks = 1000;


        waitForStart();

        driveTurnRight(ticks);

        while(back_right.isBusy() || back_left.isBusy() || front_right.isBusy() || front_left.isBusy()){

        }

        setMotorsPower(0);

        // Auto Sequence
    }

    public void setMotorsPower(int power){
        front_left.setPower(power);
        front_right.setPower(power);
        back_left.setPower(power);
        back_right.setPower(power);
    }

    public void driveForward(int ticks){

        front_left.setTargetPosition(ticks);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setPower(1);

        front_right.setTargetPosition(ticks);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setPower(1);

        back_left.setTargetPosition(ticks);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setPower(1);

        back_right.setTargetPosition(ticks);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setPower(1);



    }
    public void driveTurnLeft(int ticks){
        front_left.setTargetPosition(ticks);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setPower(-1);

        front_right.setTargetPosition(ticks);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setPower(1);

        back_left.setTargetPosition(ticks);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setPower(-1);

        back_right.setTargetPosition(ticks);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setPower(1);


    }

    public void driveTurnRight(int ticks){
        ResetEncoder();
        front_left.setTargetPosition(ticks);
        front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_left.setPower(1);

        front_right.setTargetPosition(-ticks);
        front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        front_right.setPower(1);

        back_left.setTargetPosition(ticks);
        back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_left.setPower(1);

        back_right.setTargetPosition(-ticks);
        back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        back_right.setPower(1);
        //chelsea is a bigback 67 skibidi no sigma rizz aura

    }
    public void ResetEncoder(){
        front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }




}