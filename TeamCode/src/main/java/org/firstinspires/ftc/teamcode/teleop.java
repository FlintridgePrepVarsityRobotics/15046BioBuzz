
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

        double driveSpeed = 1;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * -1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            robot.fLeftWheel.setPower(frontLeftPower * driveSpeed);
            robot.bLeftWheel.setPower(backLeftPower * driveSpeed);
            robot.fRightWheel.setPower(frontRightPower * driveSpeed);
            robot.bRightWheel.setPower(backRightPower * driveSpeed);

            if (gamepad1.a){
                robot.intakeMotorIn.setPower(1);
                robot.intakeMotorUp.setPower(1);
            }
            else if (gamepad1.y){
                robot.intakeMotorIn.setPower(-0.3);
                robot.intakeMotorUp.setPower(-0.3);
            }
            else{
                robot.intakeMotorIn.setPower(0);
                robot.intakeMotorUp.setPower(0);
            }


        }


    }

}