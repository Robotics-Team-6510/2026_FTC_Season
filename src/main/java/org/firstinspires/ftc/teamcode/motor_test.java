package org.firstinspires.ftc.teamcode;
// ^^ Must match the folder our Java Class is in, check the left hand side of the screen.

// Prewritten Code that is imported so we don't have to write EVERYTHING from scratch.
// Delete what you don't need.
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(group="Group Name")
public class motor_test extends OpMode
{


    DcMotor num_1, num_2;
    @Override
    public void init() {
        num_1 = hardwareMap.get(DcMotor.class, "1");
        num_2 = hardwareMap.get(DcMotor.class, "2");
    }

    @Override
    public void loop() {

      if(gamepad1.a){
          num_1.setPower(0.8);
          num_2.setPower(0.8);
      }else{
          num_2.setPower(0);
          num_1.setPower(0);
      }


    }
}
