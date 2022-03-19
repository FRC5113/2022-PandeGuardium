/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.FlagConstants.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DriveCommand;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    System.out.println("Initialized robot");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    // m_robotContainer.driveTrain.setAllToCoast();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonCommand();
    m_robotContainer.driveTrain.resetEncoders();
    m_robotContainer.driveTrain.setAllToBrake();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // m_robotContainer.driveTrain.getPose();
    // m_robotContainer.driveTrain.updateSmartDashboardEncoderValues();

    /*if (x.get() > 5) {
      m_robotContainer.driveTrain.tankDrive(-1, 1);
    } else {
      m_robotContainer.driveTrain.tankDrive(1, -1);
    }
    m_robotContainer.*/
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    CommandScheduler.getInstance().cancelAll();
    m_robotContainer.driveTrain.setAllToBrake();
    m_robotContainer.driveTrain.resetEncoders();

    // implement xbox or joystick
    if (driveTrainUseJoystick) {
      // stand alone joystick
      m_robotContainer.driveTrain.setDefaultCommand(
          new DriveCommand(
              m_robotContainer.driveTrain,
              () -> m_robotContainer.getJoysticksVal(false),
              () -> m_robotContainer.getJoysticksVal(true)));
    } else {
      // xbox controller
      m_robotContainer.driveTrain.setDefaultCommand(
          new DriveCommand(
              m_robotContainer.driveTrain,
              () -> m_robotContainer.getControllerLeftY(),
              () -> m_robotContainer.getControllerRightX()));
      // m_robotContainer.shooter.setDefaultCommand(new SpinDownCommand(m_robotContainer.shooter));
    }

    // m_robotContainer.shooter.setDefaultCommand(
    //   new SpinUpCommand(m_robotContainer.shooter, m_robotContainer.limelight, true)
    // );
    // m_robotContainer.shooter.setDefaultCommand(
    // new ShooterPulseCommand(m_robotContainer.shooter, 2000));
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    /*
    System.out.println(
        "Left is being sent "
            + m_robotContainer.getControllerLeftY()
            + " Right is being sent "
            + m_robotContainer.getControllerRightX());
    m_robotContainer.shooter.getSpeed();
    m_robotContainer.shooter.getCurrent();
    */
    // m_robotContainer.driveTrain.putSpeed();
    // m_robotContainer.driveTrain.showAngle();
    // m_robotContainer.driveTrain.getPose();
    // m_robotContainer.driveTrain.updateSmartDashboardEncoderValues();

    // System.out.println(m_robotContainer.getControllerLeftY());
    // System.out.println(m_robotContainer.getControllerRightX());
    // m_robotContainer.led.rainbow();
  }

  @Override
  public void testInit() {
    // Cancels all running commands and runs the indexer
    CommandScheduler.getInstance().cancelAll();
    // m_robotContainer.driveTrain.setDefaultCommand(
    //     new DriveCommand(m_robotContainer.driveTrain, () -> 0.2, () -> 0.2));
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    // m_robotContainer.driveTrain.tankDrive(1, -1);
  }
}
