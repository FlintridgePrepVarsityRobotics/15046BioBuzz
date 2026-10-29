package org.firstinspires.ftc.teamcode;

//Eisa Is Better Than Justin At Coding :)



import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



import java.util.List;

@Autonomous(name = "Auto")
public class autonomous extends LinearOpMode {

    public HWMap robot = new HWMap();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        while (!isStarted()) {


        }

        waitForStart(); //wait for play button to be pressed
        robot.bRightWheel.setPower(-.5);
        robot.fRightWheel.setPower(-.5);
        robot.bLeftWheel.setPower(-.5);
        robot.fLeftWheel.setPower(-.5);
        sleep(500);
        robot.bRightWheel.setPower(0);
        robot.fRightWheel.setPower(0);
        robot.bLeftWheel.setPower(0);
        robot.fLeftWheel.setPower(0);

        robot.bRightWheel.setPower(.5);
        robot.fRightWheel.setPower(.5);
        robot.bLeftWheel.setPower(-.5);
        robot.fLeftWheel.setPower(-.5);
        sleep(700);
        robot.bRightWheel.setPower(0);
        robot.fRightWheel.setPower(0);
        robot.bLeftWheel.setPower(0);
        robot.fLeftWheel.setPower(0);

        robot.bRightWheel.setPower(-.7);
        robot.fRightWheel.setPower(-.7);
        robot.bLeftWheel.setPower(-.7);
        robot.fLeftWheel.setPower(-.7);
        sleep(1000);
        robot.bRightWheel.setPower(0);
        robot.fRightWheel.setPower(0);
        robot.bLeftWheel.setPower(0);
        robot.fLeftWheel.setPower(0);

    }

}

