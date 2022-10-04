package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.FlagConstants;
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
    if (FlagConstants.useClimberConstraints) {
      if (direction == ClimbDirection.DOWN) {
        if (mClimber.getExtenderLeftEncoderValue() <= -ClimberConstants.LeftMinEncoderRestraint) {
          mClimber.extendLeft(ClimberConstants.LEFT_RETRACT_SPEED);
        } else {
          mClimber.stopLeftExtender();
        }
        if (mClimber.getExtenderRightEncoderValue() <= -ClimberConstants.RightMinEncoderRestraint) {
          mClimber.extendRight(ClimberConstants.RIGHT_RETRACT_SPEED);
        } else {
          mClimber.stopRightExtender();
        }
      } else {
        if (mClimber.getExtenderLeftEncoderValue() >= -ClimberConstants.LeftMaxEncoderRestraint) {
          mClimber.extendLeft(ClimberConstants.LEFT_EXTEND_SPEED);
        } else {
          mClimber.stopLeftExtender();
        }
        if (mClimber.getExtenderRightEncoderValue() >= -ClimberConstants.RightMaxEncoderRestraint) {
          mClimber.extendRight(ClimberConstants.RIGHT_EXTEND_SPEED);
        } else {
          mClimber.stopRightExtender();
        }
      }
    } else {
      if (direction == ClimbDirection.DOWN) {
        mClimber.extendLeft(ClimberConstants.LEFT_RETRACT_SPEED);
        mClimber.extendRight(ClimberConstants.RIGHT_RETRACT_SPEED);
      } else {
        mClimber.extendLeft(ClimberConstants.LEFT_EXTEND_SPEED);
        mClimber.extendRight(ClimberConstants.RIGHT_EXTEND_SPEED);
      }
    }
  }

  public void end() {
    mClimber.stopLeftExtender();
    mClimber.stopRightExtender();
  }
}
