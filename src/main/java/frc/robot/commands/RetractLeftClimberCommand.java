package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.subsystems.Climber;

public class RetractLeftClimberCommand extends CommandBase {
  private Climber mClimber;

  public RetractLeftClimberCommand(Climber climber) {
    addRequirements(climber);
    mClimber = climber;
  }

  public void execute() {
    mClimber.extendLeft(ClimberConstants.LEFT_RETRACT_SPEED);
  }

  public void end() {
    mClimber.stopLeftExtender();
    mClimber.resetEncoders();
  }
}
