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
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    leftVal = scale(leftValue.getAsDouble());
    rightVal = scale(rightValue.getAsDouble());

    if (yButton.getAsBoolean()) {
      driveTrain.arcadeDrive(leftVal, rightVal);
    } else {
      driveTrain.arcadeDrive(0.3 * leftVal, 0.325 * rightVal);
    }
  }

  public void end() {
    driveTrain.arcadeDrive(0, 0);
  }

  public double scale(double value) {
    return (value + (Math.pow(value, 3)));
  }
}
