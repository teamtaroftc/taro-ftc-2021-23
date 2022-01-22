package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

// version 3 of taro's driver control code
// basic drivetrain opmode w/ other components (& servo claw) and split gamepads

@TeleOp(name="teleop_v3", group="Linear Opmode")
public class teleop_v3 extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    //name motor variables
    private DcMotor fldrive, frdrive, brdrive, bldrive, lslides, carousel;
    Servo   clawServo;
    double  clawPosition;
    double  MIN_POSITION = 0, MAX_POSITION = 1;

    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //map the motor variables to actual motors
        fldrive = hardwareMap.get(DcMotor.class, "fldrive");
        frdrive = hardwareMap.get(DcMotor.class, "frdrive");
        brdrive = hardwareMap.get(DcMotor.class, "brdrive");
        bldrive = hardwareMap.get(DcMotor.class, "bldrive");
        lslides = hardwareMap.get(DcMotor.class, "lslides");
        carousel = hardwareMap.get(DcMotor.class, "carousel");

        //set direction of motors
        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);
        lslides.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // slowmode
        boolean slowmode = false;
        boolean xControl = false;

        while (opModeIsActive())
        {
            double speed = gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x;
            double strafe = -gamepad1.left_stick_x;
            double lsfor = gamepad2.right_trigger;
            double lsback = gamepad2.left_trigger;
            boolean cfor = gamepad2.right_bumper;
            boolean cback = gamepad2.left_bumper;

            //determine power for each motor
            double fl = speed+turn+strafe;
            double fr = speed-turn-strafe;
            double br = speed-turn+strafe;
            double bl = speed+turn-strafe;
            double ls = lsfor - lsback;

            // slow mode
            if (slowmode){
                fl /= 10;
                fr /= 10;
                bl /= 10;
                br /= 10;
            }

            //set power to motors with range of -1 to 1
            fldrive.setPower(Range.clip(fl, -1.0, 1.0));
            frdrive.setPower(Range.clip(fr, -1.0, 1.0));
            brdrive.setPower(Range.clip(br, -1.0, 1.0));
            bldrive.setPower(Range.clip(bl, -1.0, 1.0));
            lslides.setPower(Range.clip(ls, -1.0, 1.0));

            if (cfor) carousel.setPower(0.5);

            else if (cback) carousel.setPower(-0.5);

            else carousel.setPower(0);            }

            if (gamepad1.x && xControl) slowmode = !slowmode;

            if (gamepad1.dpad_up && clawPosition > MIN_POSITION) clawPosition -= .01;

            // move arm up on B button if not already at the highest position.
            if (gamepad1.dpad_down && clawPosition < MAX_POSITION) clawPosition += .01;

            xControl = !gamepad1.x;

            //debug messages for each motor
            telemetry.addData("fldrive",Double.toString(fldrive.getPower()));
            telemetry.addData("frdrive",Double.toString(frdrive.getPower()));
            telemetry.addData("brdrive",Double.toString(brdrive.getPower()));
            telemetry.addData("bldrive",Double.toString(bldrive.getPower()));
            telemetry.addData("lslides",Double.toString(lslides.getPower()));

            telemetry.update();
        }
    }
}