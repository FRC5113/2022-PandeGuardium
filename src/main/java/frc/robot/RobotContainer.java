/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.JoystickConstants.xboxAButton;
import static frc.robot.Constants.JoystickConstants.xboxBButton;
import static frc.robot.Constants.JoystickConstants.xboxLeftBumper;
import static frc.robot.Constants.JoystickConstants.xboxRightBumper;
import static frc.robot.Constants.JoystickConstants.xboxXButton;
import static frc.robot.Constants.JoystickConstants.xboxYButton;
import static frc.robot.Constants.PathweaverConstants.kDriveKinematics;
import static frc.robot.Constants.PathweaverConstants.kMaxAccelerationMetersPerSecondSquared;
import static frc.robot.Constants.PathweaverConstants.kMaxSpeedMetersPerSecond;
import static frc.robot.Constants.PathweaverConstants.kPDriveVel;
import static frc.robot.Constants.PathweaverConstants.kRamseteB;
import static frc.robot.Constants.PathweaverConstants.kRamseteZeta;
import static frc.robot.Constants.PathweaverConstants.kaVoltSecondsSquaredPerMeter;
import static frc.robot.Constants.PathweaverConstants.ksVolts;
import static frc.robot.Constants.PathweaverConstants.kvVoltSecondsPerMeter;
import static frc.robot.Constants.ShooterConstants.feet12halffront;
import static frc.robot.Constants.ShooterConstants.feet17halffront;
import static frc.robot.Constants.ShooterConstants.feet7halfback;
import static frc.robot.Constants.ShooterConstants.feet7halffront;
import static edu.wpi.first.math.util.Units.*;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
// import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CenterTargetRobot;
import frc.robot.commands.IndexCommand;
import frc.robot.commands.SpinUpCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HopUp;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI; 

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  //private Compressor compressor = new Compressor();
  public DriveTrain driveTrain = new DriveTrain();
  public Indexer indexer = new Indexer();
  public Limelight limelight = new Limelight();
  public HopUp hopper = new HopUp();
  public Shooter shooter = new Shooter();
  public Turret turret = new Turret();
  //public LED led = new LED();

  private Joystick leftDriveJoystick = new Joystick(0);
  private Joystick rightDriveJoystick = new Joystick(1);
  public XboxController driveController = new XboxController(2);

  

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    //compressor.setClosedLoopControl(true);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //new JoystickButton(driveController, xboxLeftBumper)
        //.whenPressed(() -> driveTrain.setMaxOutput(0.5))
        //.whenReleased(() -> driveTrain.setMaxOutput(1));

    /*new JoystickButton(driveController, xboxLeftBumper)
        .whileHeld(new IndexCommand(indexer,true), false);

    new JoystickButton(driveController, xboxRightBumper)
        .whileHeld(new IndexCommand(indexer), false);
    
    new JoystickButton(driveController, xboxBButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet17halffront));

    new JoystickButton(driveController, xboxAButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet12halffront));

    new JoystickButton(driveController, xboxXButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet7halffront));

    new JoystickButton(driveController, xboxYButton)
        .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet7halfback));

    //new Trigger(() -> (driveController.getRightTriggerAxis() > 0.75))
    //    .whileActiveContinuous(new SpinUpCommand(shooter, hopper, feet17halffront));
    
    new Trigger(() -> (driveController.getRightTriggerAxis() > 0.75))
          .whileActiveContinuous(new IndexCommand(indexer), false);*/
    
    new Trigger(() -> (driveController.getLeftTriggerAxis() > 0.75))
        .whileActiveContinuous(new CenterTargetRobot(driveTrain, limelight));
  }

  public double getDriveLeftVal() {
    return -leftDriveJoystick.getY();
  }

  public double getDriveRightVal() {
    return rightDriveJoystick.getX();
  }

  public double getControllerLeftY() {
    return -driveController.getLeftY();
  }

  public double getControllerRightX() {
    return driveController.getRightX();
  }

  public Command getAutoCommand() {
    return null;
  }

  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getSlalomPath() {
    
    
    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(ksVolts,
                                       kvVoltSecondsPerMeter,
                                       kaVoltSecondsSquaredPerMeter),
            kDriveKinematics,
            10);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(kMaxSpeedMetersPerSecond,
                             kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    config.setReversed(false);
    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(inchesToMeters(37.5), inchesToMeters(30), new Rotation2d(0)),
        List.of(
            new Translation2d(inchesToMeters(90), inchesToMeters(30)),
            new Translation2d(inchesToMeters(105), inchesToMeters(90)),
            new Translation2d(inchesToMeters(255), inchesToMeters(90)),
            new Translation2d(inchesToMeters(275), inchesToMeters(60)),
            new Translation2d(inchesToMeters(300), inchesToMeters(30)),
            new Translation2d(inchesToMeters(340), inchesToMeters(60)),
            new Translation2d(inchesToMeters(285), inchesToMeters(90)),
            new Translation2d(inchesToMeters(285), inchesToMeters(30)),
            new Translation2d(inchesToMeters(190), inchesToMeters(30)),
            new Translation2d(inchesToMeters(90), inchesToMeters(30)),
            new Translation2d(inchesToMeters(90), inchesToMeters(60)),
            new Translation2d(inchesToMeters(80), inchesToMeters(90))
        ),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(inchesToMeters(37.5), inchesToMeters(120), new Rotation2d(Math.PI)),
        // Pass config
        config
    );

    RamseteCommand ramseteCommand = new RamseteCommand(
        exampleTrajectory,
        driveTrain::getPose,
        new RamseteController(kRamseteB, kRamseteZeta),
        new SimpleMotorFeedforward(ksVolts,
                                   kvVoltSecondsPerMeter,
                                   kaVoltSecondsSquaredPerMeter),
        kDriveKinematics,
        driveTrain::getWheelSpeeds,
        new PIDController(kPDriveVel, 0, 0),
        new PIDController(kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        driveTrain::tankDriveVolts,
        driveTrain
    );

    // Reset odometry to the starting pose of the trajectory.
    driveTrain.resetOdometry(exampleTrajectory.getInitialPose());

    
    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  }


    /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getBarrelPath() {
    
    
  //   // Create a voltage constraint to ensure we don't accelerate too fast
  //   var autoVoltageConstraint =
  //       new DifferentialDriveVoltageConstraint(
  //           new SimpleMotorFeedforward(ksVolts,
  //                                      kvVoltSecondsPerMeter,
  //                                      kaVoltSecondsSquaredPerMeter),
  //           kDriveKinematics,
  //           10);

  //   // Create config for trajectory
  //   TrajectoryConfig config =
  //       new TrajectoryConfig(kMaxSpeedMetersPerSecond,
  //                            kMaxAccelerationMetersPerSecondSquared)
  //           // Add kinematics to ensure max speed is actually obeyed
  //           .setKinematics(kDriveKinematics)
  //           // Apply the voltage constraint
  //           .addConstraint(autoVoltageConstraint);

  //   config.setReversed(false);
  //   // An example trajectory to follow.  All units in meters.
  //   Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
  //       // Start at the origin facing the +X direction
  //       new Pose2d(inchesToMeters(50), inchesToMeters(90), new Rotation2d(0)),
  //       List.of(
  //           // navigate around first barrel, centered at D5 (150,60)
  //     new Translation2d(inchesToMeters(150), inchesToMeters(90)),
  //     new Translation2d(inchesToMeters(180), inchesToMeters(60)),
  //     new Translation2d(inchesToMeters(150), inchesToMeters(30)),
  //     new Translation2d(inchesToMeters(120), inchesToMeters(60)),

  //     // navigate around B8  (240, 120)
  //     new Translation2d(inchesToMeters(150), inchesToMeters(90)),
  //     new Translation2d(inchesToMeters(240), inchesToMeters(90)),
  //     new Translation2d(inchesToMeters(270), inchesToMeters(120)),
  //     new Translation2d(inchesToMeters(240), inchesToMeters(150)),
  //     new Translation2d(inchesToMeters(205), inchesToMeters(120)),

  //     // navigate around D10 (300, 60)
  //     new Translation2d(inchesToMeters(210), inchesToMeters(60)),
  //     new Translation2d(inchesToMeters(300), inchesToMeters(30)),
  //     new Translation2d(inchesToMeters(330), inchesToMeters(60)),
  //     new Translation2d(inchesToMeters(300), inchesToMeters(90)), // shift left slightly 

  //     // avoid cones on the way back
  //     new Translation2d(inchesToMeters(240), inchesToMeters(80)), 
  //     new Translation2d(inchesToMeters(150), inchesToMeters(100))
  //       ),
  //       // End 3 meters straight ahead of where we started, facing forward
  //       new Pose2d(inchesToMeters(60), inchesToMeters(90), new Rotation2d(Math.PI)),
  //       // Pass config
  //       config
  //   );

  //   RamseteCommand ramseteCommand = new RamseteCommand(
  //       exampleTrajectory,
  //       driveTrain::getPose,
  //       new RamseteController(kRamseteB, kRamseteZeta),
  //       new SimpleMotorFeedforward(ksVolts,
  //                                  kvVoltSecondsPerMeter,
  //                                  kaVoltSecondsSquaredPerMeter),
  //       kDriveKinematics,
  //       driveTrain::getWheelSpeeds,
  //       new PIDController(kPDriveVel, 0, 0),
  //       new PIDController(kPDriveVel, 0, 0),
  //       // RamseteCommand passes volts to the callback
  //       driveTrain::tankDriveVolts,
  //       driveTrain
  //   );

  //   // Reset odometry to the starting pose of the trajectory.
  //   driveTrain.resetOdometry(exampleTrajectory.getInitialPose());

    
  //   // Run path following command, then stop at the end.
  //   return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  // }
    public Command getBarrelPath() {
      driveTrain.motorTest();
      return null;
    }
}
