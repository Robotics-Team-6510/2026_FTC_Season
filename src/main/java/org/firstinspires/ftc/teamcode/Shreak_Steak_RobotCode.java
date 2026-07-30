package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="ShrekSteakCode", group="Iterative OpMode")
public class Shrek_Steak_RobotCode extends OpMode
{
    // Declare OpMode members.
    private DcMotor FrontRightMotor;
    private DcMotor FrontLeftMotor;
    private DcMotor BackRightMotor;
    private DcMotor BackLeftMotor;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        FrontRightMotor = hardwareMap.get(DcMotor.class, "fr");
        FrontLeftMotor = hardwareMap.get(DcMotor.class, "fl");
        BackRightMotor = hardwareMap.get(DcMotor.class, "br");
        BackLeftMotor = hardwareMap.get(DcMotor.class, "bl");

        FrontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        BackRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /*
     * Code to run REPEATEDLY after the driver hits START but before they hit STOP
     */

    @Override
    public void loop() {
        double forward_and_back = gamepad1.right_stick_y;
        double turn = gamepad1.left_stick_x;
        double left_and_right = gamepad1.right_stick_x;
        double speed_multiplier = 0.75;

        if(gamepad1.right_bumper) {
            speed_multiplier = 0.25;
        }

        double front_right_power = (forward_and_back + turn + left_and_right) * speed_multiplier;
        double front_left_power = (forward_and_back -turn -left_and_right) * speed_multiplier;
        double back_right_power = (forward_and_back + turn -left_and_right) * speed_multiplier;
        double back_left_power = (forward_and_back -turn + left_and_right) * speed_multiplier;

//        FrontRightMotor.setPower(forward_and_back);
//        FrontLeftMotor.setPower(forward_and_back);
//        BackRightMotor.setPower(forward_and_back);
//        BackLeftMotor.setPower(forward_and_back);

//        FrontRightMotor.setPower(turn);
//        FrontLeftMotor.setPower(-turn);
//        BackRightMotor.setPower(turn);
//        BackLeftMotor.setPower(-turn);

//        FrontRightMotor.setPower(left_and_right);
//        FrontLeftMotor.setPower(-left_and_right);
//        BackRightMotor.setPower(-left_and_right);
//        BackLeftMotor.setPower(left_and_right);

        FrontRightMotor.setPower(front_right_power);
        FrontLeftMotor.setPower(front_left_power);
        BackRightMotor.setPower(back_right_power);
        BackLeftMotor.setPower(back_left_power);
    }
}
