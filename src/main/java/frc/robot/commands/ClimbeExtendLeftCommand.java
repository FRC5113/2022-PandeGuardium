package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.FlagConstants;
import frc.robot.enums.ClimbDirection;
import frc.robot.subsystems.Climber;

public class ClimbeExtendLeftCommand extends CommandBase {

  private Climber mClimber;
  private ClimbDirection direction;

  public ClimbeExtendLeftCommand(Climber climber, ClimbDirection direction) {
    mClimber = climber;
    this.direction = direction;

    System.out.println("shut up vlad");
  }

  public void execute() {
    // System.out.println(mClimber.getExtenderEncoderValue());
    if (FlagConstants.useClimberConstraints) {
      if (direction == ClimbDirection.DOWN) {
        if (mClimber.getExtenderLeftEncoderValue() <= -ClimberConstants.LeftMinEncoderRestraint) {
          mClimber.extendLeft(ClimberConstants.LEFT_RETRACT_SPEED);
        } else {
          mClimber.stopLeftExtender();
        }
      } else {
        if (mClimber.getExtenderLeftEncoderValue() >= -ClimberConstants.LeftMaxEncoderRestraint) {
          mClimber.extendLeft(ClimberConstants.LEFT_EXTEND_SPEED);
        } else {
          mClimber.stopLeftExtender();
        }
      }
    } else {
      if (direction == ClimbDirection.DOWN) {
        mClimber.extendLeft(ClimberConstants.LEFT_RETRACT_SPEED);
      } else {
        mClimber.extendLeft(ClimberConstants.LEFT_EXTEND_SPEED);
      }
    }
  }

  public void end() {
    mClimber.stopLeftExtender();
  }
}
