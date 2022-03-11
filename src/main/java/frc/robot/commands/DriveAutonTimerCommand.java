package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;

public class DriveAutonTimerCommand extends CommandBase {

  private DriveTrain driveTrain;
  private Timer timer;

  public DriveAutonTimerCommand(DriveTrain driveTrain) {
    addRequirements(driveTrain);

    timer = new Timer();
    timer.start();
    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    driveTrain.tankDrive(-DriveConstants.autonSpeed, DriveConstants.autonSpeed);
    // driveTrain.curvatureDrive(scale(leftValue.getAsDouble()),
    // scale(rightValue.getAsDouble()));
  }

  @Override
  public boolean isFinished() {
    return timer.get() >= 1; // adjust to go forward or backwards
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
  }
}