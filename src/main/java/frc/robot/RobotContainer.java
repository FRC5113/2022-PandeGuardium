/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
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
  // public LED led = new LED();

  // NOTE: The ports can be rearanged (by drag and drop) in the Driver Station
  // Terminal
  private Joystick leftDriveJoystick = new Joystick(0); // should be in port 0
  private Joystick rightDriveJoystick = new Joystick(1); // should be in port 1
  public XboxController xboxController = new XboxController(2); // should be in port 2

  // xbox controller buttons
  public JoystickButton aButton = new JoystickButton(xboxController, 1);
  public JoystickButton bButton = new JoystickButton(xboxController, 2);
  public JoystickButton xButton = new JoystickButton(xboxController, 3);
  public JoystickButton yButton = new JoystickButton(xboxController, 4);
  public JoystickButton lbButton = new JoystickButton(xboxController, 5);
  public JoystickButton rbButton = new JoystickButton(xboxController, 6);
  public JoystickButton startButton = new JoystickButton(xboxController, 8);
  public JoystickButton backButton = new JoystickButton(xboxController, 7);
  // xbox trigger
  public Trigger rightTrigger = new Trigger(() -> (xboxController.getRightTriggerAxis() > 0.75));
  public Trigger leftTrigger = new Trigger(() -> (xboxController.getLeftTriggerAxis() > 0.75));

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
  
    aButton.whenHeld(
        new IndexIntakeCommand(
            indexer, intake, shooter, IntakeSystemMotors.IndexerIntakeForward, ShouldStop.No));
    bButton.whenHeld(
        new IndexIntakeCommand(
            indexer, intake, shooter, IntakeSystemMotors.IndexerIntakeBackward, ShouldStop.No));
    
    xButton.whenHeld(new SpinUpCommand(shooter, limelight));    
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
    return -xboxController.getLeftY();
  }

  public double getControllerLeftX() {
    return xboxController.getLeftX();
  }

  public double getControllerRightY() {
    return -xboxController.getRightY();
  }

  public double getControllerRightX() {
    return xboxController.getRightX();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  
  public Command getAutonCommand() {
    return new OneBallsAuton(shooter, indexer, limelight, driveTrain, intake);
  }
}
