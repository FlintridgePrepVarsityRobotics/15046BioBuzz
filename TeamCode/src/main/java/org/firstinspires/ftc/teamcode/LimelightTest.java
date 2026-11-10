package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;


import java.util.List;
@TeleOp(name = "visionTest")
@Disabled
public class LimelightTest extends LinearOpMode {


    private Limelight3A limelight;


    @Override
    public void runOpMode() throws InterruptedException
    {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");


        telemetry.setMsTransmissionInterval(11);


        limelight.pipelineSwitch(0);


        /*
         * Starts polling for data.  If you neglect to call start(), getLatestResult() will return null.
         */
        limelight.start();


        telemetry.addData(">", "Robot Ready.  Press Play.");
        telemetry.update();
        waitForStart();


        while (opModeIsActive()) {
            LLStatus status = limelight.getStatus();
            telemetry.addData("Name", "%s",
                    status.getName());
            telemetry.addData("LL", "Temp: %.1fC, CPU: %.1f%%, FPS: %d",
                    status.getTemp(), status.getCpu(),(int)status.getFps());
            telemetry.addData("Pipeline", "Index: %d, Type: %s",
                    status.getPipelineIndex(), status.getPipelineType());


            LLResult result = limelight.getLatestResult();
            if (result.isValid()) {
                // Access general information
                Pose3D botpose = result.getBotpose();
                double captureLatency = result.getCaptureLatency();
                double targetingLatency = result.getTargetingLatency();
                double parseLatency = result.getParseLatency();
                telemetry.addData("LL Latency", captureLatency + targetingLatency);
                telemetry.addData("Parse Latency", parseLatency);
                telemetry.addData("PythonOutput", java.util.Arrays.toString(result.getPythonOutput()));


                telemetry.addData("tx", result.getTx());
                telemetry.addData("txnc", result.getTxNC());
                telemetry.addData("ty", result.getTy());
                telemetry.addData("tync", result.getTyNC());


                telemetry.addData("Botpose", botpose.toString());


                // Access classifier results
                List<LLResultTypes.ClassifierResult> classifierResults = result.getClassifierResults();
                for (LLResultTypes.ClassifierResult cr : classifierResults) {
                    telemetry.addData("Classifier", "Class: %s, Confidence: %.2f", cr.getClassName(), cr.getConfidence());
                }


                // Access detector results
                List<LLResultTypes.DetectorResult> detections = result.getDetectorResults();
                for (LLResultTypes.DetectorResult detection : detections) {
                    telemetry.addData("Detector", "Class: %s, Area: %.2f", detection.getClassName(), detection.getTargetArea());
                }


                // Access fiducial results
                List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
                for (LLResultTypes.FiducialResult fiducial : fiducialResults) {
                    telemetry.addData("Fiducial", "ID: %d, Family: %s, X: %.2f, Y: %.2f", fiducial.getFiducialId(), fiducial.getFamily(), fiducial.getTargetXDegrees(), fiducial.getTargetYDegrees());
                    int id = fiducial.getFiducialId(); // The ID number of the fiducial
                    double x = fiducial.getTargetXDegrees(); // Where it is (left-right)
                    double y = fiducial.getTargetYDegrees(); // Where it is (up-down)
                    double StrafeDistance_3D = fiducial.getRobotPoseTargetSpace().getPosition().y;
                    fiducial.getRobotPoseTargetSpace(); // Robot pose relative it the AprilTag Coordinate System (Most Useful)
                    fiducial.getCameraPoseTargetSpace(); // Camera pose relative to the AprilTag (useful)
                    fiducial.getRobotPoseFieldSpace(); // Robot pose in the field coordinate system based on this tag alone (useful)
                    fiducial.getTargetPoseCameraSpace(); // AprilTag pose in the camera's coordinate system (not very useful)
                    fiducial.getTargetPoseRobotSpace(); // AprilTag pose in the robot's coordinate system (not very useful)
                    Pose3D cameraPose = fiducial.getCameraPoseTargetSpace();


                    telemetry.addData("Camera Position",
                            "X: %.3f, Y: %.3f, Z: %.3f",
                            cameraPose.getPosition().x,
                            cameraPose.getPosition().y,
                            cameraPose.getPosition().z);


                    telemetry.addData("Camera Rotation",
                            "Pitch: %.2f, Roll: %.2f, Yaw: %.2f",
                            Math.toDegrees(cameraPose.getOrientation().getPitch()),
                            Math.toDegrees(cameraPose.getOrientation().getRoll()),
                            Math.toDegrees(cameraPose.getOrientation().getYaw()));
                }


            } else {
                telemetry.addData("Limelight", "No data available");
            }


            telemetry.update();
        }
        limelight.stop();
    }
}
