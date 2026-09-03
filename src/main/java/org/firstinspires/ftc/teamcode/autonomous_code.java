package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(group = "Group Name")

public class autonomous_code extends LinearOpMode {
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;
    private DcMotor FIntakeMotor;

    private void setPowerAll(double pow) {
        FRMotor.setPower(pow);
        FLMotor.setPower(pow);
        BRMotor.setPower(pow);
        BLMotor.setPower(pow);
    }
    private void runPositionAll() {
        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    private void stop_resetCoder() {
        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    private boolean waitMotor() {
        boolean needWait = FRMotor.isBusy() || FLMotor.isBusy() || BRMotor.isBusy() || BLMotor.isBusy();
        return needWait;
    }

    @Override
    public void runOpMode() {
        // init
        FRMotor = hardwareMap.get(DcMotor.class, "fr");
        FLMotor = hardwareMap.get(DcMotor.class, "fl");
        BRMotor = hardwareMap.get(DcMotor.class, "br");
        BLMotor = hardwareMap.get(DcMotor.class, "bl");

        FIntakeMotor = hardwareMap.get(DcMotor.class, "fi");

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

        waitForStart();
        // Auto Sequence
        setPowerAll(0.5);
        FRMotor.setTargetPosition(1024);
        runPositionAll();
        while(waitMotor()){}
        stop_resetCoder();


        FIntakeMotor.setPower(1);
        sleep(5000);
        FIntakeMotor.setPower(0);

    }

}