/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.FlagConstants.*;

import com.frc5113.library.primative.SmartTimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ClimbCancelExtendCommand;
import frc.robot.commands.DriveCommand;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends SmartTimedRobot {
  private Command autonomousCommand;

  private RobotContainer robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our autonomous chooser on the dashboard (if it exists).
    robotContainer = new RobotContainer();
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
    // newly-scheduled commands, running already-scheduled commands, removing finished or
    // interrupted commands, and running subsystem periodic() methods. This must be called from the
    // robot's periodic, block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    autonomousCommand = robotContainer.getAutoCommand();
    robotContainer.driveTrain.resetEncoders();
    robotContainer.driveTrain.setAllToBrake();

    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    CommandScheduler.getInstance().cancelAll();
    robotContainer.driveTrain.setAllToBrake();
    robotContainer.driveTrain.resetEncoders();

    robotContainer.climber.setDefaultCommand(
        new ClimbCancelExtendCommand(robotContainer.climber));

    if (driveTrainUseJoystick) {
      // stand-alone joystick
      robotContainer.driveTrain.setDefaultCommand(
          new DriveCommand(
              robotContainer.driveTrain,
              () -> robotContainer.getJoysticksVal(false),
              () -> robotContainer.getJoysticksVal(true),
              () ->
                  useYButtonToggle
                      && robotContainer.xboxPad.yButton.get())); // m_robotContainer.yButton.get() &&
    } else {
      // xbox controller
      robotContainer.driveTrain.setDefaultCommand(
          new DriveCommand(
              robotContainer.driveTrain,
              () -> robotContainer.getControllerLeftY(),
              () -> robotContainer.getControllerRightX(),
              () ->
                  robotContainer.xboxPad.yButton.get()
                      && useYButtonToggle));
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands and runs the indexer
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
