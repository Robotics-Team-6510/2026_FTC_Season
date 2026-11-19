package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

public class Team_name_telemetry {

    private DcMotor fr;
    private DcMotor fl;
    private DcMotor br;
    private DcMotor bl;
    private IMU imu;
    //    private DcMotor I1Motor;

    public int bob;

    // storage location || Class || Name
    // Private || DcMotor || BLMotor

    // All Config and Hardware Mapping
    public void init() {
        // Hardware mapping tells the program what Variables corresponds to what motor in your Robot Configuration
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");


    }

    public void loop() {

        telemetry.addData("Encoder Position", br.getCurrentPosition());
        telemetry.update();
    }
}

