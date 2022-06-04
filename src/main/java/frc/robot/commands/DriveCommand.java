package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {

  private DriveTrain driveTrain;

  private DoubleSupplier leftValue;
  private DoubleSupplier rightValue;
  private BooleanSupplier yButton;

  private double leftVal;
  private double rightVal;

  public DriveCommand(
      DriveTrain driveTrain,
      DoubleSupplier leftVal,
      DoubleSupplier rightVal,
      BooleanSupplier yToggle) {
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
    this.leftValue = leftVal;
    this.rightValue = rightVal;
    this.yButton = yToggle;
    // System.out.println("drive command!!");
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // System.out.println("Executing drive train");
    // System.out.println("Driving actualy");
    // System.out.println(leftValue.getAsDouble() + " " + rightValue.getAsDouble());
    // SmartDashboard.putString(
    //    "Joystick Vals", leftValue.getAsDouble() + " " + rightValue.getAsDouble());

    /*
    if (Math.abs(leftVal - prevLeftValue) > DriveTrainConstants.rampUpRate) {
      leftVal = prevLeftValue + (Math.signum(leftVal) * DriveTrainConstants.rampUpRate);
    }
    if (Math.abs(rightVal - prevRightValue) > DriveTrainConstants.rampUpRate) {
      rightVal = prevRightValue + (Math.signum(rightVal) * DriveTrainConstants.rampUpRate);
    }
    */

    // driveTrain.curvatureDrive(leftVal, rightVal);

    // driveTrain.tankDrive(0.8 * leftVal, 0.8 * rightVal);
    /*
    if (Math.abs(leftVal) < 0.05) {
      leftVal = 0;
    }
    if (Math.abs(rightVal) < 0.05) {
      rightVal = 0;
    }
    */

    leftVal = scale(leftValue.getAsDouble());
    rightVal = scale(rightValue.getAsDouble());

    // driveTrain.tankDrive(-leftVal, -rightVal);
    // lego night y button adjust
    // System.out.println(yButton.getAsBoolean());
    if (yButton.getAsBoolean()) {
      driveTrain.arcadeDrive(leftVal, rightVal);
    } else {
      // driveTrain.arcadeDrive(0.35 * leftVal, 0.4 * rightVal);
      driveTrain.arcadeDrive(0.3 * leftVal, 0.325 * rightVal);
    }
    // prevLeftValue = leftVal;
    // prevRightValue = rightVal;
    // driveTrain.curvatureDrive(scale(leftValue.getAsDouble()),
    // scale(rightValue.getAsDouble()));
  }

  public void end() {
    // driveTrain.tankDrive(0, 0);
    driveTrain.arcadeDrive(0, 0);
  }

  public double scale(double value) {
    // return (DriveConstants.SCALE_FACTOR * Math.pow(value, 3)
    // + DriveConstants.SCALE_FACTOR * (value));
    // return 0.75 * Math.pow(value, 3) + Math.signum(value) * 0.25 * Math.pow(value, 2);
    // return 0.5 * Math.pow(value, 5) + 0.5 * Math.pow(value, 3);
    return (value + (Math.pow(value, 3)));
  }
}
