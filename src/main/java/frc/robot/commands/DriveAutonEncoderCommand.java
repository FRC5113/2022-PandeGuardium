package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;

public class DriveAutonEncoderCommand extends CommandBase {

  private DriveTrain driveTrain;

  public DriveAutonEncoderCommand(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (driveTrain.leftParent.getSelectedSensorPosition()
        < DriveConstants.distanceModifier * DriveConstants.autonDistance) {
      driveTrain.tankDrive(-DriveConstants.autonSpeed, DriveConstants.autonSpeed);
    }
    // driveTrain.curvatureDrive(scale(leftValue.getAsDouble()),
    // scale(rightValue.getAsDouble()));
  }

  @Override
  public boolean isFinished() {
    System.out.println(driveTrain.leftParent.getSelectedSensorPosition());
    return driveTrain.leftParent.getSelectedSensorPosition()
        < DriveConstants.distanceModifier * DriveConstants.autonDistance;
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
  }
}
