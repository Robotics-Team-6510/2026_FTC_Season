package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// @TeleOp so it shows up in the right area of the Driver Station
// Do NOT give it a name "name = "something"", leave the name blank, and it will use the filename
// Code with the same group name will be grouped together in the driver station
@TeleOp(group="Group Name")
public class catapultTest extends OpMode
{
    // Declare OpMode members, put motors, devices, etc all here. i.e: private DcMotor LeftFront;

    DcMotor leftCatapult, rightCatapult;

    // Code that runs when you press "INIT" on the Driver Station, runs once.
    // Use this space for hardware mapping, motor configuration, etc.
    @Override
    public void init() {

        leftCatapult = hardwareMap.get(DcMotor.class, "Left catapult");
        rightCatapult = hardwareMap.get(DcMotor.class, "Right catapult");

        leftCatapult.setDirection(DcMotorSimple.Direction.FORWARD);
        rightCatapult.setDirection(DcMotorSimple.Direction.REVERSE);

        leftCatapult.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightCatapult.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    // Code to run repeatedly during TeleOp
    @Override
    public void loop() {

        if(gamepad1.a) {
            leftCatapult.setPower(0.1);
            rightCatapult.setPower(0.1);
        } else {
            leftCatapult.setPower(0);
            rightCatapult.setPower(0);
        }

    }
}
