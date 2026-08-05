import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "i make understanding :)")
public class NEW_botzilla_challenge1 extends OpMode {
    // 1 - varibale definition

    int mynamebob;

    DcMotor FR;
    DcMotor FL;
    DcMotor BR;
    DcMotor BL;

    IMU imu;



    @Override
    public void init(){
        // 2
        FR = hardwareMap.get(DcMotor.class, "front right");
        FL = hardwareMap.get(DcMotor.class, "front left");
        BL = hardwareMap.get(DcMotor.class, "back left");
        BR = hardwareMap.get(DcMotor.class, "back right");

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN));
        imu.initialize(parameters);

        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);




    }


    @Override
    public void loop(){
        // 3
        double forward = gamepad1.left_stick_y;
        double sideways = gamepad1.left_stick_x;
        double turning = gamepad1.right_stick_x;
                    // number
        FR.setPower(forward - turning - sideways);
        FL.setPower(forward + turning + sideways);
        BR.setPower(forward - turning + sideways);
        BL.setPower(forward + turning - sideways);

        telemetry.addData("caption", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();

      
    }

}
