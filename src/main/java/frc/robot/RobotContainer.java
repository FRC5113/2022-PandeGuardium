/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.AutonCommand;
import frc.robot.commands.IndexIntakeCommand;
import frc.robot.commands.LowTargetShootCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.SpinDownCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import java.util.List;

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
  // public LED led = new LED();

  // NOTE: The ports can be rearanged (by drag and drop) in the Driver Station Terminal
  private Joystick leftDriveJoystick = new Joystick(0); // should be in port 0
  private Joystick rightDriveJoystick = new Joystick(1); // should be in port 1
  public XboxController xboxController = new XboxController(2); // should be in port 2

  // xbox controller buttons
  public JoystickButton aButton = new JoystickButton(xboxController, 1);
  public JoystickButton bButton = new JoystickButton(xboxController, 2);
  public JoystickButton xButton = new JoystickButton(xboxController, 3);
  public JoystickButton yButton = new JoystickButton(xboxController, 4);
  public JoystickButton rbButton = new JoystickButton(xboxController, 6);
  public JoystickButton backButton = new JoystickButton(xboxController, 7);
  // xbox trigger
  public Trigger rightTrigger = new Trigger(() -> (xboxController.getRightTriggerAxis() > 0.75));

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

    // rightTrigger.toggleWhenActive(new IntakeCommand(intake));

    // new JoystickButton(xboxController, xboxLeftBumper)
    // .whenPressed(() -> driveTrain.setMaxOutput(0.5))
    // .whenReleased(() -> driveTrain.setMaxOutput(1));

    // new JoystickButton(xboxController, xboxAButton) // toggleWhenActive???
    //     .toggleWhenActive(new IndexerCommand(indexer), false);

    /*new JoystickButton(xboxController, xboxRightBumper)
        .whileHeld(new IndexCommand(indexer), false);

    new JoystickButton(xboxController, xboxBButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet17halffront));

    new JoystickButton(xboxController, xboxAButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet12halffront));

    new JoystickButton(xboxController, xboxXButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet7halffront));

    new JoystickButton(xboxController, xboxYButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet7halfback));

    //new Trigger(() -> (xboxController.getRightTriggerAxis() > 0.75))
    //    .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet17halffront));

    new Trigger(() -> (xboxController.getRightTriggerAxis() > 0.75))
          .whileActiveContinuous(new IndexCommand(indexer), false);*/

    /*
        new Trigger(() -> (xboxController.getLeftTriggerAxis() > 0.75))
            .whileActiveContinuous(new CenterTargetRobot(driveTrain, limelight));
    */
    // toggleWhenActive => push to turn on, push to turn off
    // toggleWhenPressed => ??????
    // whenPressed => When but pressed
    // whenActive => ???
    // aButton.toggleWhenPressed(new IntakeCommand(intake));
    /**
     * Button mappings xBox A - Run the indexer and intake xBox B - Outake xBox X - Only run the
     * intake xBox Y - Shoot
     */
    aButton.whenHeld(new IndexIntakeCommand(indexer, intake, false, true, true));
    bButton.whenHeld(new OuttakeCommand(intake, indexer));
    xButton.whenHeld(
        new IndexIntakeCommand(indexer, intake, false, true, false)); // uses only the indexer
    // bButton.whenHeld(new IndexerOnlyCommand(indexer));
    // xButton.whenHeld(new ShootCommand(shooter, indexer, intake, limelight), true);

    yButton.whenHeld(new ShootCommand(shooter, indexer, intake, limelight));
    // yButton.whenHeld(new SpinUpCommand(shooter, limelight, false));
    yButton.whenReleased(new SpinDownCommand(shooter));
    // rightTrigger.whileActiveContinuous(new ShootCommand(shooter, indexer, intake, limelight),
    // true);
    // rightTrigger.whenInactive(new SpinDownCommand(shooter), false);

    // xButton.toggleWhenActive();

    rbButton.whenHeld(new LowTargetShootCommand(shooter, indexer, intake));
    rbButton.whenReleased(new SpinDownCommand(shooter));
    /*
    xButton
        .whenHeld(new SpinUpCommand(shooter, limelight, true))
        .whenReleased(new SpinDownCommand(shooter));
    */
    // yButton.whenHeld(new OuttakeCommand(intake, indexer));
    // yButton.whenHeld(new IndexerOnlyCommand(indexer));

    // rbButton.whenHeld(new CenterTargetRobotCommand(driveTrain, limelight));

    // backButton.whenPressed(new StopAllMotersCommand(indexer, intake, shooter, driveTrain));

    // aButton.toggleWhenActive(new IntakeCommand(intake));
    // bButton.toggleWhenActive(new IndexerCommand(indexer));
    // xButton.toggleWhenActive(new ShootCommand(shooter, indexer, 0.1));
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
    return -1 * leftDriveJoystick.getRawAxis(1);
  }

  public double getControllerLeftY() {
    return -xboxController.getLeftY();
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
  public Command getAutonomousCommand() {
    DifferentialDriveKinematics difDrive =
        new DifferentialDriveKinematics(DriveConstants.kTrackwidthMeters);

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                DriveConstants.ksVolts,
                DriveConstants.kvVoltSecondsPerMeter,
                DriveConstants.kaVoltSecondsSquaredPerMeter),
            difDrive,
            12);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(
                DriveConstants.kMaxSpeedMetersPerSecond,
                DriveConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(difDrive)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow. All units in meters.

    // String trajectoryJSON = "paths/output/AutonTestPathRapidReact.wpilib.json";
    Trajectory trajectory = new Trajectory();

    // try {
    // System.out.println("Constacting " +
    // Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON).toString());
    // Path trajectoryPath =
    // Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    // trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    // } catch (IOException ex) {
    // // DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON,
    // ex.getStackTrace());
    // System.out.println("No, it not work" + ex.getStackTrace());
    // }

    trajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(3, 0)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0, new Rotation2d(0.0)),
            // Pass confi
            config);

    RamseteCommand ramseteCommand =
        new RamseteCommand(
            trajectory,
            driveTrain::getPose,
            new RamseteController(DriveConstants.kRamseteB, DriveConstants.kRamseteZeta),
            new SimpleMotorFeedforward(
                DriveConstants.ksVolts,
                DriveConstants.kvVoltSecondsPerMeter,
                DriveConstants.kaVoltSecondsSquaredPerMeter),
            difDrive,
            driveTrain::getWheelSpeeds,
            new PIDController(DriveConstants.kPDriveVel, 0, 0),
            new PIDController(DriveConstants.kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            driveTrain::tankDriveVolts,
            driveTrain);

    // Reset odometry to the starting pose of the trajectory.
    // Pose2d autoPose = new Pose2d(new Translation2d(0.235, 4.326), new
    // Rotation2d(0.0));
    driveTrain.resetOdometry(trajectory.getInitialPose());
    // driveTrain.resetEncoders();
    // driveTrain.resetGyro();

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  }

  public Command getAutonCommand() {
    // return new AutonCommand(shooter, indexer, limelight, driveTrain, intake);
    // return new DriveAutonTimerCommand(driveTrain);
    return new AutonCommand(shooter, indexer, limelight, driveTrain, intake);
  }
}
