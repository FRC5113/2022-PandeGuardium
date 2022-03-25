package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.enums.ClimbRotationDirection;
import frc.robot.subsystems.Climber;

public class ClimbRotateCommand extends CommandBase {

  private Climber mClimber;
  private ClimbRotationDirection direction;

  public ClimbRotateCommand(Climber climber, ClimbRotationDirection direction) {
    mClimber = climber;
    this.direction = direction;
  }

  public void execute() {
    if (direction == ClimbRotationDirection.FORWARD) {
      mClimber.rotate(ClimberConstants.ROTATE_FORWARD_SPEED);
    } else {
      mClimber.rotate(ClimberConstants.ROTATE_BACKWARD_SPEED); // THIS CONSTANT SHOULD BE NEGATIVE
    }
  }

  public void end() {
    mClimber.stopRotator();
  }
}
