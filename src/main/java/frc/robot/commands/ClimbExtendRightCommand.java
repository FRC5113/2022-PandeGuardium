package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.FlagConstants;
import frc.robot.enums.ClimbDirection;
import frc.robot.subsystems.Climber;

public class ClimbExtendRightCommand extends CommandBase {

  private Climber mClimber;
  private ClimbDirection direction;

  public ClimbExtendRightCommand(Climber climber, ClimbDirection direction) {
    mClimber = climber;
    this.direction = direction;

    System.out.println("shut up vlad");
  }

  public void execute() {
    // System.out.println(mClimber.getExtenderEncoderValue());
    if (FlagConstants.useClimberConstraints) {
      if (direction == ClimbDirection.DOWN) {
        if (mClimber.getExtenderRightEncoderValue() <= -ClimberConstants.RightMinEncoderRestraint) {
          mClimber.extendRight(ClimberConstants.RIGHT_RETRACT_SPEED);
        } else {
          mClimber.stopRightExtender();
        }
      } else {
        if (mClimber.getExtenderRightEncoderValue() >= -ClimberConstants.RightMaxEncoderRestraint) {
          mClimber.extendRight(ClimberConstants.RIGHT_EXTEND_SPEED);
        } else {
          mClimber.stopRightExtender();
        }
      }
    } else {
      if (direction == ClimbDirection.DOWN) {
        mClimber.extendRight(ClimberConstants.RIGHT_RETRACT_SPEED);
      } else {
        mClimber.extendRight(ClimberConstants.RIGHT_EXTEND_SPEED);
      }
    }
  }

  public void end() {
    mClimber.stopRightExtender();
  }
}
