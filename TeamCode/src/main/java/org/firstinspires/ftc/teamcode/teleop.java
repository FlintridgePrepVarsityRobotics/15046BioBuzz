
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name = "test")
public class teleop extends LinearOpMode {
  public HWMap robot =  new HWMap();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            int rr = robot.bRightWheel.getCurrentPosition();
            int lf = robot.fLeftWheel.getCurrentPosition();
            int lr = robot.bLeftWheel.getCurrentPosition();


            telemetry.addData("rr",rr);
            telemetry.addData("lf",lf);
            telemetry.addData("lr",lr);

            telemetry.update();
        }


    }

}