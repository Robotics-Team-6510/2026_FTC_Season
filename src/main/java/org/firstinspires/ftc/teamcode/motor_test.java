package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(group="Group Name")
public class motor_test extends OpMode
{

    DcMotor Number_1, Number_2;

    @Override
    public void init() {

        Number_1 = hardwareMap.get(DcMotor.class,"1");
        Number_2 = hardwareMap.get(DcMotor.class,"2");


    }

    @Override
    public void loop() {

        if(gamepad1.a)


    }
}
