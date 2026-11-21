package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Utility
public class TestDrivetrain extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        // It is good practise to set the mode to ensure consistency
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up) fl.setPower(0.1);
            else  fl.setPower(0);

            if (gamepad1.dpad_down) bl.setPower(0.1);
            else  bl.setPower(0);

            if (gamepad1.y) fr.setPower(0.1);
            else  fr.setPower(0);

            if (gamepad1.a) br.setPower(0.1);
            else  br.setPower(0);
        }
    }
}
