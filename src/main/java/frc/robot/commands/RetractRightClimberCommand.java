package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;

public class RetractRightClimberCommand extends CommandBase {
  private Climber mClimber;

  public RetractRightClimberCommand(Climber climber) {
    addRequirements(climber);
    mClimber = climber;
  }

  public void execute() {
    mClimber.extendRight(ClimberConstants.RIGHT_RETRACT_SPEED);
  }

  public void end() {
    mClimber.stopRightExtender();
    mClimber.resetEncoders();
  }
}
