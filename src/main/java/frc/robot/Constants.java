/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final class FlagConstants {
    /** Use the stand alone joystick (true) over xbox thumbpads (false) */
    public static final boolean driveTrainUseJoystick = true;
  }

  public static final class JoystickConstants {

    public static final int xboxAButton = 1;
    public static final int xboxBButton = 2;
    public static final int xboxXButton = 3;
    public static final int xboxYButton = 4;
    public static final int xboxLeftBumper = 5;
    public static final int xboxRightBumper = 6;
    public static final int xboxBackButton = 7;
    public static final int xboxStartButton = 8;
  }

  public static final class DriveConstants {

    public static final double ksVolts = 0.02;
    public static final double kvVoltSecondsPerMeter = 0.13;
    public static final double kaVoltSecondsSquaredPerMeter = 0.04;

    public static final double kPDriveVel = 0.09;

    public static final double kTrackwidthMeters = 0.6720082224258779;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 1.5;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1.5;

    // Reasonable baseline values for a RAMSETE follower in units of meters and
    // seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;

    // scale polynomial constants
    public static final double SCALE_FACTOR =
        0.5; // TODO: Make sure this allows for the control required

    // robot constants
    public static final double gearBoxRatio = 11.5;
    public static final double wheelDiameterMeters = Units.inchesToMeters(6.25);

    // auton constants
    public static final double autonDistance = 2;
    public static final double autonSpeed = 0.5;

    public static final double encoderConversionFactor = 2048.0;
  }

  public static final class LimelightConstants {

    public static final double ANGLE = 34.0;
    public static final double targetHeight = 8.666;
    public static final double limelightHeight = 1.3333;

    public static final double kP = 0.05;
    public static final double kI = 0.0005;
    public static final double kD = 0.1;
  }

  public static final class DriveTrainConstants {

    public static final int LEFT_PARENT_ID = 11;
    public static final int RIGHT_PARENT_ID = 12;
    public static final int LEFT_CHILD_ID = 21;
    public static final int RIGHT_CHILD_ID = 22;

    public static final double DRIVE_MAX_VOLTAGE = 12.0;
    public static final double RAMP_RATE = 0.6;
    public static final double DEADBAND = 0.1;

    public static final double CONVERSTIONRATETEST =
        1 / 2048 / 11.5 * Units.inchesToMeters(6.5) * Math.PI;

    public static final double CONVERSION_RATE_VELOCITY =
        Units.inchesToMeters(6.5) * Math.PI / 2048 / 10 / 11.5;

    public static final double CONVERSION_RATE_POSITION =
        Units.inchesToMeters(6.5) * Math.PI / 2048 / 11.5;
    public static final double rightResistanceAdjustment = 1;

    public static final double rampUpRate = 0.05;
  }

  // VLAD: 2/27: TURN THE SPEED WAY DOWN
  public static final class IntakeConstants {

    public static final int INTAKE_ID = 30;
    public static final double INTAKE_MAX_VOLTAGE = 12.3;
    public static final int INTAKE_CURRENT_LIMIT = 60;

    public static final double INTAKE_SPEED = -0.4;
  }

  public static final class IndexerConstants {

    public static final int INDEXER_ID = 31; // the can id of the indexer

    // public static final int BEAM_BREAK_INPUT_ID = 5;
    // public static final int BEAM_BREAK_OUTPUT_ID = 6;

    public static final int INDEXER_MAX_CURRENT = 40;
    public static final double INDEXER_MAX_VOLTAGE = 12.3;
    public static final double INDEXER_SPEED =
        -.4; // This may need to be lower, otherwise the ball may fly too fast
    // INDEXER_SPEED is negative because we are intaking (and not outtaking, which
    // would be forward)
    // the amount of times the command to set the moter speed will be called in
    // periodic
    public static final int PERIODIC_TICKS = 50;
  }

  public static final class ClimberConstants {
    // Can IDs of the four motors: 2 for rotating and 2 for extanding
    public static final int HANGER_RIGHT_ROTATE_ID = 51;
    public static final int HANGER_LEFT_ROTATE_ID = 52;
    public static final int HANGER_RIGHT_EXTEND_ID = 53;
    public static final int HANGER_LEFT_EXTEND_ID = 54;

    // Maximum voltage / amp states
    public static final int MAXIMUM_VOLTAGE = 12;
    public static final int MAXIMUM_CURRENT = 40;

    // Speeds to get something done
    public static final double EXTEND_SPEED = 0.2;
    public static final double RETRACT_SPEED = -0.2;

    public static final double ROTATE_FORWARD_SPEED = 0.2;
    public static final double ROTATE_BACKWARD_SPEED = -0.2;
  }

  public static final class ShooterConstants {

    public static final int SHOOTER_PARENT_ID = 42;
    public static final int SHOOTER_CHILD_ID = 41;
    public static final double MAX_VOLTAGE = 12.0;
    // public static final double RAMP_RATE = 5;

    public static final double CONVERSION_RATE = 600.0 / 2048; // check on this

    public static final double kP = 8;
    public static final double kI = 0.000001;
    public static final double kD = 500;

    public static final int rampUpRate = 30;
    public static final int rampDownRate = 100;

    public static final double testVal = 1000;
    public static final double lowSpinSpeed = 6000;

    /** How much of a difference we will tolerate when spinning to a value */
    public static final double spinUpTolerance = 200;
  }

  public static final class LEDConstants {
    public static final int LED_PORT = 9;
    public static final int LED_LENGTH = 60;
  }
}
