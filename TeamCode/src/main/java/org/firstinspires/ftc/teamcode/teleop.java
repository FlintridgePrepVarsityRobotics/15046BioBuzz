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
        double rpmStep = 500; // 500 RPM per tap
        double maxRPM = 6000; // motor max free speed
        double minRPM = 0;    // optional lower bound

        boolean lastUp = false;
        boolean lastDown = false;
        int servoState = 0;
        boolean lastLeftBumper = false;

        boolean lastUp2 = false;
        boolean lastDown2 = false;
        boolean servoOpen2 = false;
        boolean lastRightBumper = false;

        double intakeState = 0;

        boolean lastA = false;
        boolean lastB = false;
        boolean lastY = false;

        double driveSpeed = 1;

        long lastServoToggleTime = 0;
        long lastServo3ToggleTime = 0;
        long servoCooldown = 400;
        long lastIntakeToggleTime = 0;
        long intakeCooldown = 400;

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

            boolean aPressed = gamepad1.a && !lastA;
            boolean bPressed = gamepad1.b && !lastB;
            boolean yPressed = gamepad1.y && !lastY;

            if (System.currentTimeMillis() - lastIntakeToggleTime >= intakeCooldown) {

                if (aPressed || bPressed) {
                    intakeState = (intakeState == 1) ? 0 : 1;
                    lastIntakeToggleTime = System.currentTimeMillis();
                }
                else if (yPressed) {
                    intakeState = (intakeState == -.6) ? 0 : -.6;
                    lastIntakeToggleTime = System.currentTimeMillis();
                }
            }

            robot.intakeMotorIn.setPower(intakeState);

            lastA = gamepad1.a;
            lastB = gamepad1.b;
            lastY = gamepad1.y;


            boolean currentLeftBumper = gamepad1.left_bumper;

            if (currentLeftBumper && !lastLeftBumper
                    && System.currentTimeMillis() - lastServoToggleTime >= servoCooldown) {

                servoState = (servoState + 1) % 3;
                telemetry.addData("servostate", servoState);
                telemetry.update();

                if (servoState == 0) {//downstate
                    robot.rightservo.setPosition(0.65);
                    robot.leftservo.setPosition(0.2);
                }
                else if (servoState == 1) {//upstate
                    robot.rightservo.setPosition(0);
                    robot.leftservo.setPosition(0.78);
                }
                else if (servoState == 2) {//midstate
                    robot.rightservo.setPosition(0.6);
                    robot.leftservo.setPosition(0.3);
                }

                lastServoToggleTime = System.currentTimeMillis();
            }

            lastLeftBumper = currentLeftBumper;

            boolean currentRightBumper = gamepad1.right_bumper;

            if (currentRightBumper && !lastRightBumper
                    && System.currentTimeMillis() - lastServo3ToggleTime >= servoCooldown) {

                servoOpen2 = !servoOpen2;

                if (servoOpen2) {
                    robot.servo3.setPosition(0);
                } else {
                    robot.servo3.setPosition(0.2);
                }

                lastServo3ToggleTime = System.currentTimeMillis();
            }

            lastRightBumper = currentRightBumper;

            lastLeftBumper = currentLeftBumper;
            boolean upPressed = gamepad1.dpad_up;
            boolean downPressed = gamepad1.dpad_down;

            if (upPressed && !lastUp) {
                targetRPM = 2700;
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
//            robot.flywheelN.setVelocity(targetTicksPerSec);
//            double currentRPMN = robot.flywheelN.getVelocity() / ticksPerRev * 60.0;



            telemetry.addData("TargetRPM", targetRPM);
            telemetry.addData("CurrentRPM", currentRPM);
//            telemetry.addData("CurrentRPMN", currentRPMN);
            telemetry.update();

        }
    }
}