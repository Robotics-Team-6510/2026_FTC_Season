package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
public class Autonomous_code extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    // Section 1
    // Declare Motors, Variables, and Functions
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;

    private DcMotor Fintake;

    private IMU imu;


    @Override
    public void runOpMode() {
        // Run Once Here
        //Section 2 - init

        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        FRMotor = hardwareMap.get(DcMotor.class, "fr");
        FLMotor = hardwareMap.get(DcMotor.class, "fl");
        BRMotor = hardwareMap.get(DcMotor.class, "br");
        BLMotor = hardwareMap.get(DcMotor.class, "bl");
        Fintake = hardwareMap.get(DcMotor.class, "fin");

        // One side of motors will always need to be reversed so that they all spin in the same direction.
        FRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FLMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BLMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        // It is good practise to set the mode to ensure consistency
        FRMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        IMU.Parameters parameters = new IMU.Parameters(orientation);
        // Without this, the REV Hub's orientation is assumed;]
        // }to be logo up / USB forward
        imu.initialize(parameters);


        waitForStart();
        // Auto Sequence
        // Section 3
        forwards(2800, 0.5);
        turn(-998,0.5);
        forwards(5000,0.5);
        turn(-1000,0.5);
        forwards(5000,0.5);
        turn(-1000,0.5);
        forwards(5000,0.5);
        turn(-1000,0.5);
        forwards(2000,0.5);


    }

    void forwards(int TP, double P){
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FLMotor.setTargetPosition(TP);
        FRMotor.setTargetPosition(TP);
        BLMotor.setTargetPosition(TP);
        BRMotor.setTargetPosition(TP);

        FLMotor.setPower(P);
        FRMotor.setPower(P);
        BLMotor.setPower(P);
        BRMotor.setPower(P);

        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FLMotor.isBusy() || FRMotor.isBusy() || BRMotor.isBusy() || BLMotor.isBusy()) {   }

    }
    void turn(int TP, double P){
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FLMotor.setTargetPosition(TP);
        FRMotor.setTargetPosition(-TP);
        BLMotor.setTargetPosition(TP);
        BRMotor.setTargetPosition(-TP);

        FLMotor.setPower(P);
        FRMotor.setPower(P);
        BLMotor.setPower(P);
        BRMotor.setPower(P);

        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (FLMotor.isBusy() || FRMotor.isBusy() || BRMotor.isBusy() || BLMotor.isBusy()) {   }

    }

    void drive_turn(int forwards_distance, double power,int target_position_turn){
        forwards(forwards_distance,power);
        turn(target_position_turn,power);
        forwards(forwards_distance,power);
        turn(target_position_turn,power);
        forwards(forwards_distance,power);
        turn(target_position_turn,power);

    }


}