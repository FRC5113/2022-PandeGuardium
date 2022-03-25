package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.enums.ClimbDirection;
import frc.robot.subsystems.Climber;

public class ClimbExtendCommand extends CommandBase {

  private Climber mClimber;
  private ClimbDirection direction;

  public ClimbExtendCommand(Climber climber, ClimbDirection direction) {
    mClimber = climber;
    this.direction = direction;

    System.out.println("shut up vlad");
  }

  public void execute() {
    System.out.println("Running climber extension command");
    if (direction == ClimbDirection.UP) {
      mClimber.extend(ClimberConstants.EXTEND_SPEED);
    } else {
      mClimber.extend(ClimberConstants.RETRACT_SPEED);
    }
  }

  public void end() {
    mClimber.stopExtender();
  }
}
