/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.frc5113.library.oi.joystick.Joystick;
import com.frc5113.library.oi.xbox.XboxGamepad;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.IndexIntakeCommand;
import frc.robot.commands.OneBallsAuton;
import frc.robot.commands.SpinUpCommand;
import frc.robot.enums.IntakeSystemMotors;
import frc.robot.enums.ShouldStop;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /** The container for the robot. Contains subsystems, IO devices, and commands. */
  public DriveTrain driveTrain = new DriveTrain();

  public Intake intake = new Intake();
  public Indexer indexer = new Indexer();
  public Limelight limelight = new Limelight();
  public Shooter shooter = new Shooter();
  public Climber climber = new Climber();

  // NOTE: The ports can be rearranged (by drag and drop) in the Driver Station
  private final Joystick leftDriveJoystick = new Joystick(0); // should be in port 0
  private final Joystick rightDriveJoystick = new Joystick(1); // should be in port 1
  public XboxGamepad xboxPad = new XboxGamepad(2); // should be in port 2

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button -> command run mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton} along with the number of the button.
   */
  private void configureButtonBindings() {

    xboxPad.aButton.whenHeld(
        new IndexIntakeCommand(
            indexer, intake, shooter, IntakeSystemMotors.IndexerIntakeForward, ShouldStop.No));
    xboxPad.bButton.whenHeld(
        new IndexIntakeCommand(
            indexer, intake, shooter, IntakeSystemMotors.IndexerIntakeBackward, ShouldStop.No));

    xboxPad.xButton.whenHeld(new SpinUpCommand(shooter, limelight));
  }

  public double getLeftJoystickY() {
    return -leftDriveJoystick.getY();
  }

  public double getRightJoystickY() {
    return rightDriveJoystick.getX();
  }

  public double getJoysticksVal(boolean rightSide) {
    // right joystick x
    if (rightSide) {
      return 1 * rightDriveJoystick.getRawAxis(1);
    }
    return 1 * leftDriveJoystick.getRawAxis(1);
  }

  public double getControllerLeftY() {
    return -xboxPad.leftStick.getY();
  }

  public double getControllerLeftX() {
    return xboxPad.leftStick.getX();
  }

  public double getControllerRightY() {
    return -xboxPad.rightStick.getY();
  }

  public double getControllerRightX() {
    return xboxPad.rightStick.getX();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutoCommand() {
    return new OneBallsAuton(shooter, indexer, limelight, driveTrain, intake);
  }
}
