package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {

  private DriveTrain driveTrain;

  private DoubleSupplier leftValue;
  private DoubleSupplier rightValue;

  public DriveCommand(DriveTrain driveTrain, DoubleSupplier leftVal, DoubleSupplier rightVal) {
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
    this.leftValue = leftVal;
    this.rightValue = rightVal;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    driveTrain.tankDrive(scale(leftValue.getAsDouble()), scale(rightValue.getAsDouble()));
    // driveTrain.curvatureDrive(scale(leftValue.getAsDouble()), scale(rightValue.getAsDouble()));
  }

  public void end() {
    driveTrain.tankDrive(0, 0);
  }

  public double scale(double value) {
    return (0.5 * Math.pow(value, 3) + 0.5 * (value));
  }
}
