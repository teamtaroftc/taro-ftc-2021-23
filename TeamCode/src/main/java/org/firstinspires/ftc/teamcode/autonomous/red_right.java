package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


//version 1 of team taro's autonomous code.

@Autonomous(name="red_right", group="Linear OpMode")

public class red_right extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor fldrive, frdrive, bldrive, brdrive, carousel;

    double TICKS_PER_REV = 383.6, DIAM = 11.87374; // radius of wheels 2 in & ticks might be 28 or 383.6
    int distance;

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Intialized");
        telemetry.update();

        fldrive  = hardwareMap.get(DcMotor.class, "fldrive");
        frdrive  = hardwareMap.get(DcMotor.class, "frdrive");
        brdrive = hardwareMap.get(DcMotor.class, "brdrive");
        bldrive = hardwareMap.get(DcMotor.class, "bldrive");
        carousel = hardwareMap.get(DcMotor.class, "carousel");

        fldrive.setDirection(DcMotor.Direction.REVERSE);
        frdrive.setDirection(DcMotor.Direction.FORWARD);
        brdrive.setDirection(DcMotor.Direction.FORWARD);
        bldrive.setDirection(DcMotor.Direction.REVERSE);
        carousel.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        // start robot facing towards red alliance carousel
        // next to barrier & RIGHT of shipping hub

        distance = 0; // distance forward
        distance = (int)(distance*TICKS_PER_REV);
        forward(0.7, distance);

        carousel(0.7, 2000); // by time (negative if reverse direction)

        distance = 0; // distance right
        distance = (int)(distance*TICKS_PER_REV);
        straferight(0.7, distance);

    }

    public void carousel(double power, long time) throws InterruptedException
    {
        carousel.setPower(power);
        Thread.sleep(time);
    }

    public void forwardtime(double power, long time) throws InterruptedException
    {
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
        Thread.sleep(time);
    }

    public void backwardtime(double power, long time) throws InterruptedException
    {
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
        Thread.sleep(time);
    }

    public void lefttime(double power, long time) throws InterruptedException
    {
        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(-power);
        Thread.sleep(time);
    }

    public void righttime(double power, long time) throws InterruptedException
    {
        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(power);
        Thread.sleep(time);
    }

    public void strafelefttime(double power, long time) throws InterruptedException
    {
        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(-power);
        bldrive.setPower(power);
        Thread.sleep(time);
    }

    public void straferighttime(double power, long time) throws InterruptedException
    {
        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(power);
        bldrive.setPower(-power);
        Thread.sleep(time);
    }

    public void forward(double power, int distance) {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(distance);
        frdrive.setTargetPosition(distance);
        bldrive.setTargetPosition(distance);
        brdrive.setTargetPosition(distance);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);

        while (fldrive.isBusy() && frdrive.isBusy() && bldrive.isBusy() && brdrive.isBusy()) {
            //until point reached
        }

        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }

    public void backward(double power, int distance) {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(-distance);
        frdrive.setTargetPosition(-distance);
        bldrive.setTargetPosition(-distance);
        brdrive.setTargetPosition(-distance);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);

        while (fldrive.isBusy() && frdrive.isBusy() && bldrive.isBusy() && brdrive.isBusy()) {
            //until point reached
        }

        power = 0.0;
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }

    public void left(double power, int distance) {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(-distance);
        frdrive.setTargetPosition(distance);
        bldrive.setTargetPosition(distance);
        brdrive.setTargetPosition(-distance);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(-power);

        while (fldrive.isBusy() && frdrive.isBusy() && bldrive.isBusy() && brdrive.isBusy()) {
            //until point reached
        }

        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }

    public void right(double power, int distance) {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(distance);
        frdrive.setTargetPosition(-distance);
        bldrive.setTargetPosition(-distance);
        brdrive.setTargetPosition(distance);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(power);

        while (fldrive.isBusy() && frdrive.isBusy() && bldrive.isBusy() && brdrive.isBusy()) {
            //until point reached
        }

        power = 0.0;
        fldrive.setPower(-power);
        frdrive.setPower(-power);
        brdrive.setPower(-power);
        bldrive.setPower(-power);
    }
    public void strafeleft(double power, int distance) {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(-distance);
        frdrive.setTargetPosition(distance);
        bldrive.setTargetPosition(-distance);
        brdrive.setTargetPosition(distance);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fldrive.setPower(-power);
        frdrive.setPower(power);
        brdrive.setPower(-power);
        bldrive.setPower(power);

        while (fldrive.isBusy() && frdrive.isBusy() && bldrive.isBusy() && brdrive.isBusy()) {
            //until point reached
        }

        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }
    public void straferight(double power, int distance) {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(distance);
        frdrive.setTargetPosition(-distance);
        bldrive.setTargetPosition(distance);
        brdrive.setTargetPosition(-distance);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fldrive.setPower(power);
        frdrive.setPower(-power);
        brdrive.setPower(power);
        bldrive.setPower(-power);

        while (fldrive.isBusy() && frdrive.isBusy() && bldrive.isBusy() && brdrive.isBusy()) {
            //until point reached
        }

        power = 0.0;
        fldrive.setPower(power);
        frdrive.setPower(power);
        brdrive.setPower(power);
        bldrive.setPower(power);
    }
}