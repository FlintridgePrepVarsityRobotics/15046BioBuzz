package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
public class HWMap {
    public static final double SHOOTER_PUSH = 0.2;   // Position to push ball into flywheel / block intake
    public static final double SHOOTER_RESET = 0.0;

    public DcMotor fLeftWheel = null;  //expansion hub port0
    public DcMotor fRightWheel = null; //control hub port0
    public DcMotor bLeftWheel = null; //expansion hub port1
    public DcMotor bRightWheel = null; //control hub port1
    public DcMotorEx intakeMotorIn = null;
    public DcMotorEx intakeMotorUp = null;


    public void init(HardwareMap hwMap) {
        fLeftWheel = hwMap.dcMotor.get("lf");
        fRightWheel = hwMap.dcMotor.get("rf");
        bLeftWheel = hwMap.dcMotor.get("lr");
        bRightWheel = hwMap.dcMotor.get("rr");
        intakeMotorIn = hwMap.get(DcMotorEx.class, "intakeMotorIn");
        intakeMotorUp = hwMap.get(DcMotorEx.class, "intakeMotorUp");

        fRightWheel.setDirection(DcMotor.Direction.REVERSE);
        fLeftWheel.setDirection(DcMotor.Direction.FORWARD);
        bRightWheel.setDirection(DcMotor.Direction.REVERSE);
        bLeftWheel.setDirection(DcMotor.Direction.FORWARD);

        fRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Stop();
    }


    public void Stop() {
        fRightWheel.setPower(0);
        fLeftWheel.setPower(0);
        bRightWheel.setPower(0);
        bLeftWheel.setPower(0);

    }
}
