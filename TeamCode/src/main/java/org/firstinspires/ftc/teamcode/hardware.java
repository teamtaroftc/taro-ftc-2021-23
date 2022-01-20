package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
// Testing

public class hardware {
    // Create Motors
    public DcMotor fldrive = null;
    public DcMotor frdrive = null;
    public DcMotor bldrive = null;
    public DcMotor brdrive = null;
    public DcMotor lslides = null;
    public DcMotor carousel = null;

    // Create Servo
    public Servo claw = null;

    // Additional Variables
    HardwareMap hardwareMap = null;
    public ElapsedTime runtime = new ElapsedTime();

    public hardware(HardwareMap hwMap) {
        initialize(hwMap);
    }

    private void initialize(HardwareMap hwMap) {
        hardwareMap = hwMap;

        // Connect Motors
        fldrive = hardwareMap.get(DcMotor.class, "fldrive");
        frdrive = hardwareMap.get(DcMotor.class, "frdrive");
        brdrive = hardwareMap.get(DcMotor.class, "brdrive");
        bldrive = hardwareMap.get(DcMotor.class, "bldrive");
        lslides = hardwareMap.get(DcMotor.class, "lslides");
        carousel = hardwareMap.get(DcMotor.class, "carousel");

        // Connect Servo
        claw = hardwareMap.get(Servo.class, "claw");

        // Set Motor Directions
        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);
        lslides.setDirection(DcMotor.Direction.FORWARD);
        carousel.setDirection(DcMotor.Direction.FORWARD);
    }
}
