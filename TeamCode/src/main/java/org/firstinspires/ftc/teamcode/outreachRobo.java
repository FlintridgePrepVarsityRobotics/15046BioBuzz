//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//@TeleOp(name = "drive")
//public class outreachRobo extends LinearOpMode {
//    public DcMotor LeftWheel = null;  //expansion hub port0
//    public DcMotor RightWheel = null; //control hub port0
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        LeftWheel = hardwareMap.dcMotor.get("l");
//        RightWheel = hardwareMap.dcMotor.get("r");
//        while (opModeIsActive()) {
//
//            double y = -gamepad1.left_stick_y;
//            double x = gamepad1.right_stick_x;
//
//            LeftWheel.setPower(y+x);
//            RightWheel.setPower(y-x);
//
//
//        }
//    }
//}