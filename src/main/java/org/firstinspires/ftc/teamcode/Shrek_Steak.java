package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class Shrek_Steak extends OpMode
{
    // Declare OpMode members.
    private DcMotor FrontRightMotor;
    private DcMotor FrontLeftMotor;
    private DcMotor BackRightMotor;
    private DcMotor BackLeftMotor;
    private DcMotor IntakeMotor;
    private IMU Gyro;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        FrontRightMotor = hardwareMap.get(DcMotor.class, "fr");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "fl");
        BackRightMotor = hardwareMap.get(DcMotor.class, "br");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "bl");
        IntakeMotor = hardwareMap.get(DcMotor.class,  "intake");
        Gyro = hardwareMap.get(IMU.class,"imu");

        FrontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        IntakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        RevHubOrientationOnRobot Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);
        IMU.Parameters Parameter = new IMU.Parameters(Orientation);
        Gyro.initialize(Parameter);
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */

    @Override
    public void loop() {
        double Y_axis = gamepad1.right_stick_y;
        double turn = -gamepad1.left_stick_x;
        double X_axis = gamepad1.right_stick_x;
        double speed_multiplier = 0.75;
        boolean intake_button = gamepad1.a;
        boolean outake_button = gamepad1.b;

        double CurrentYaw = Gyro.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double forward_and_back = X_axis*Math.sin(-CurrentYaw)+Y_axis*Math.cos(-CurrentYaw);
        double left_and_right = X_axis*Math.cos(-CurrentYaw)-Y_axis*Math.sin(-CurrentYaw);

        left_and_right = left_and_right*1.1;

        double Normalise = Math.max(Math.abs(Y_axis)+Math.abs(X_axis)+Math.abs(turn),1);

        if(gamepad1.right_bumper) {
            speed_multiplier = 0.25;
        }

        if(gamepad1.options){
            Gyro.resetYaw();
        }

        if(intake_button) {
            IntakeMotor.setPower(1);
        } else if (outake_button) {
            IntakeMotor.setPower(-1);
        } else {
            IntakeMotor.setPower(0);
        }

        double front_right_power = (forward_and_back + turn + left_and_right) / Normalise * speed_multiplier;
        double front_left_power = (forward_and_back -turn -left_and_right) / Normalise * speed_multiplier;
        double back_right_power = (forward_and_back + turn -left_and_right) / Normalise * speed_multiplier;
        double back_left_power = (forward_and_back -turn + left_and_right) / Normalise * speed_multiplier;

        FrontRightMotor.setPower(front_right_power);
        FrontLeftMotor.setPower(front_left_power);
        BackRightMotor.setPower(back_right_power);
        BackLeftMotor.setPower(back_left_power);
    }
}
