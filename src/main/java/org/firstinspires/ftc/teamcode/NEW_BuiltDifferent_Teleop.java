package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class NEW_BuiltDifferent_Teleop extends OpMode
{
    // Declare Motors, Variables, and Functions
    private DcMotor FRMotor;
    private DcMotor FLMotor;
    private DcMotor BRMotor;
    private DcMotor BLMotor;

    private DcMotor Fintake;

    private double power = 2;



    private IMU imu;


    // All Config and Hardware Mapping
    @Override
    public void init() {
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


    }


    @Override
    public void loop() {
        // Collect Necessary Data from Gamepad in This Loop
        double y = -gamepad1.left_stick_y;
        double Turn = gamepad1.right_stick_x;
        double x = gamepad1.left_stick_x;

        if (gamepad1.options) {
            imu.resetYaw();
        }
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double strafe = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);

        double forwards = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);



        // Calculate the power for each Motor, combine the 3 above commented sections, multiply by the Scaler
        double FRPower = (forwards - Turn - strafe);
        double FLPower = (forwards + Turn +strafe);
        double BRPower = (forwards - Turn +strafe);
        double BLPower = (forwards + Turn - strafe);

        // Set the power of the motors
        FRMotor.setPower(FRPower);
        FLMotor.setPower(FLPower);
        BRMotor.setPower(BRPower);
        BLMotor.setPower(BLPower);

        if (gamepad1.left_bumper){
            Fintake.setPower(1);
        } else if (gamepad1.right_bumper){
            Fintake.setPower(-power);
        }
        else {
            Fintake.setPower(0);
        }


        if(gamepad1.dpadUpWasPressed()){
            power += 0.1;
        }

        if(gamepad1.dpadDownWasPressed()){
            power -= 0.1;
        }




        telemetry.addData("slay", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("yay", power);
        telemetry.update();



    }
}
