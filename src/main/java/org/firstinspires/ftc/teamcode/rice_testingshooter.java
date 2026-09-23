package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "rice_shootertest")
public class rice_testingshooter extends OpMode {
    DcMotor shooter;

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            shooter.setPower(0.4);
        }
        else {
            shooter.setPower(0);

            }
        }
    }
