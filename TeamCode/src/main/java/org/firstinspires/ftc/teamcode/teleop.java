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

        boolean isSpinning = false;
        int ticksPerRev = 28;
        double targetRPM = 0;
        double rpmStep = 1000; // 500 RPM per tap
        double maxRPM = 6000; // motor max free speed
        double minRPM = 0;    // optional lower bound

        boolean lastUp = false;
        boolean lastDown = false;

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
                robot.intakeMotorIn.setPower(-.5);
                robot.intakeMotorUp.setPower(-.5);
                robot.servo1.setPosition(0);
            }else if (gamepad1.b){
                robot.servo1.setPosition(0.2);
                robot.intakeMotorIn.setPower(-.5);
                robot.intakeMotorUp.setPower(-.5);
            }
            else if (gamepad1.y){
                robot.intakeMotorIn.setPower(0.5);
                robot.intakeMotorUp.setPower(.5);
                robot.servo1.setPosition(0);
            }
            else{
                robot.servo1.setPosition(0);
                robot.intakeMotorIn.setPower(0);
                robot.intakeMotorUp.setPower(0);
            }

            boolean upPressed = gamepad1.dpad_up;
            boolean downPressed = gamepad1.dpad_down;

            if (upPressed && !lastUp) {
                targetRPM += rpmStep;
                targetRPM = Math.min(targetRPM, maxRPM);
            }

            if (downPressed && !lastDown) {
                targetRPM -= rpmStep;
                targetRPM = Math.max(targetRPM, minRPM);
            }

            if (gamepad1.dpad_left){
                targetRPM = 0;
            }




            lastUp = upPressed;
            lastDown = downPressed;

            double targetTicksPerSec = targetRPM / 60.0 * ticksPerRev;

            robot.flywheelP.setVelocity(targetTicksPerSec);
            double currentRPM = robot.flywheelP.getVelocity() / ticksPerRev * 60.0;
            robot.flywheelN.setVelocity(targetTicksPerSec);
            double currentRPMN = robot.flywheelN.getVelocity() / ticksPerRev * 60.0;



            telemetry.addData("TargetRPM", targetRPM);
            telemetry.addData("CurrentRPM", currentRPM);
            telemetry.addData("CurrentRPMN", currentRPMN);
            telemetry.update();

        }
    }
}