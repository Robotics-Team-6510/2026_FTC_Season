package org.firstinspires.ftc.teamcode.utilities;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Utility
@SuppressWarnings("unused")
public class TestDrivetrainUtility extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor FrontRight = hardwareMap.get(DcMotor.class, "FRW");
        DcMotor FrontLeft = hardwareMap.get(DcMotor.class, "FLW");
        DcMotor BackLeft = hardwareMap.get(DcMotor.class, "BLW");
        DcMotor BackRight = hardwareMap.get(DcMotor.class, "BRW");

        FrontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        FrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        FrontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BackRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        telemetry.addLine("press y to spin front right forwards");
        telemetry.addLine("press a to spin back right forwards");
        telemetry.addLine("press up to spin front left forwards");
        telemetry.addLine("press down to spin back left forwards");
        telemetry.update();

        while (opModeIsActive()) {
            FrontRight.setPower(gamepad1.y ? 1 : 0);
            BackRight.setPower(gamepad1.a ? 1 : 0);
            FrontLeft.setPower(gamepad1.dpad_up ? 1 : 0);
            BackLeft.setPower(gamepad1.dpad_down ? 1 : 0);
        }
    }
}
