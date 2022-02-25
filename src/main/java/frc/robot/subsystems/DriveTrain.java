package frc.robot.subsystems;

import static frc.robot.Constants.DriveTrainConstants.*;
import static frc.robot.Constants.DriveTrainConstants.DEADBAND;
import static frc.robot.Constants.DriveTrainConstants.DRIVE_MAX_VOLTAGE;
import static frc.robot.Constants.DriveTrainConstants.LEFT_CHILD_ID;
import static frc.robot.Constants.DriveTrainConstants.LEFT_PARENT_ID;
import static frc.robot.Constants.DriveTrainConstants.RAMP_RATE;
import static frc.robot.Constants.DriveTrainConstants.RIGHT_CHILD_ID;
import static frc.robot.Constants.DriveTrainConstants.RIGHT_PARENT_ID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

    private final WPI_TalonFX leftParent;
    private final WPI_TalonFX rightParent;
    private final WPI_TalonFX leftChild;
    private final WPI_TalonFX rightChild;
    private final DifferentialDrive driveBase;

    private NetworkTableEntry m_xEntry = NetworkTableInstance.getDefault().getTable("troubleshooting").getEntry("X");
    private NetworkTableEntry m_yEntry = NetworkTableInstance.getDefault().getTable("troubleshooting").getEntry("Y");
    private final DifferentialDriveOdometry odometry;
    private final AHRS gyro;

    public DriveTrain() {
        leftParent = new WPI_TalonFX(LEFT_PARENT_ID);
        rightParent = new WPI_TalonFX(RIGHT_PARENT_ID);
        leftChild = new WPI_TalonFX(LEFT_CHILD_ID);
        rightChild = new WPI_TalonFX(RIGHT_CHILD_ID);


        configureMotor(leftParent, true);
        configureMotor(rightParent, false);
        configureMotor(leftChild, true);
        configureMotor(rightChild, false);

        rightChild.set(ControlMode.Follower, rightParent.getDeviceID());
        leftChild.set(ControlMode.Follower, leftParent.getDeviceID());

        driveBase = new DifferentialDrive(leftParent, rightParent);
        driveBase.setDeadband(DEADBAND);
        driveBase.setSafetyEnabled(false);

        gyro = new AHRS(SPI.Port.kMXP);
        gyro.enableLogging(true);
        odometry = new DifferentialDriveOdometry(gyro.getRotation2d());
        
    }

    private void configureMotor(WPI_TalonFX motor, boolean left) {
        motor.configFactoryDefault(); // Resetting the motors to make sure there's no junk on there before configuring
        //motor.configVoltageCompSaturation(DRIVE_MAX_VOLTAGE); // only use 12.3 volts regardless of battery voltage
        //motor.enableVoltageCompensation(true); // enable ^
        motor.setNeutralMode(NeutralMode.Brake); // set it so that when the motor is getting no input, it stops
        motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor); // configure the encoder (it's inside)
        motor.setSelectedSensorPosition(0); // reset the encoder to have a value of 0
        motor.configOpenloopRamp(RAMP_RATE); // how long it takes to go from 0 to the set speed
        motor.setSensorPhase(true);
        // Make sure that both sides' encoders are getting positive values when going
        // forward
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        // Controlling the left side and the right side seperately
        //driveBase.tankDrive(leftSpeed, rightSpeed);
        leftParent.set(leftSpeed);
        rightParent.set(rightSpeed);
    }

    public void tankDriveVolts(double leftSpeed, double rightSpeed) {
        // Tank drive, but in the case we want to use volts, it's here
        leftParent.setVoltage(DRIVE_MAX_VOLTAGE * leftSpeed);
        rightParent.setVoltage(-DRIVE_MAX_VOLTAGE * rightSpeed);
        driveBase.feed();
    }

    public void arcadeDrive(double speed, double rotation) {
        // How fast you want to go forward, and how much you want to turn
        driveBase.arcadeDrive(speed, rotation);
    }

    public void curvatureDrive(double speed, double rotation) {
        // Curvature drive is subset of arcade drive seems interesting ... I'll try
        // testing it
        if (speed < 0.25)
            driveBase.curvatureDrive(speed, rotation, true);
        else
            driveBase.curvatureDrive(speed, rotation, false);

        /**
         * These should help to control curvature drive, but testing needs to be done
         * driveBase.setQuickStopAlpha(something);
         * driveBase.setQuickStopThreshold(something);
         */
    }

    public void triggerDrive(double forward, double reverse, double rotation) {
        // Basically how driving works in Forza, uses triggers
        driveBase.arcadeDrive(forward - reverse, rotation);
    }

    public void setMaxOutput(double max) {
        driveBase.setMaxOutput(max);
    }

    public void motorTest() {
        rightParent.set(0.5);
    }

    public void setAllToCoast() {
        leftParent.setNeutralMode(NeutralMode.Coast);
        rightParent.setNeutralMode(NeutralMode.Coast);
        leftChild.setNeutralMode(NeutralMode.Coast);
        rightChild.setNeutralMode(NeutralMode.Coast);
    }

    public void setAllToBrake() {
        leftParent.setNeutralMode(NeutralMode.Brake);
        rightParent.setNeutralMode(NeutralMode.Brake);
        leftChild.setNeutralMode(NeutralMode.Brake);
        rightChild.setNeutralMode(NeutralMode.Brake);
    }

    public void resetEncoders() {
        leftParent.setSelectedSensorPosition(0);
        leftChild.setSelectedSensorPosition(0);
        rightParent.setSelectedSensorPosition(0);
        rightChild.setSelectedSensorPosition(0);
    }

    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        odometry.resetPosition(pose, gyro.getRotation2d());
      }

    public void periodic() {
        // Update the odometry in the periodic block
        odometry.update(gyro.getRotation2d(), 
                        leftParent.getSelectedSensorPosition()*CONVERSION_RATE_POSITION,
                        rightParent.getSelectedSensorPosition()*-CONVERSION_RATE_POSITION);
        
        var translation = odometry.getPoseMeters().getTranslation();
        m_xEntry.setNumber(translation.getX());
        m_yEntry.setNumber(translation.getY());
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds
            (leftParent.getSelectedSensorVelocity()*CONVERSION_RATE_VELOCITY, 
            rightParent.getSelectedSensorVelocity()*-CONVERSION_RATE_VELOCITY);
      }

    public double getAngle() {
        return gyro.getRotation2d().getDegrees();
    }

    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    public void showAngle() {
        SmartDashboard.putNumber("Angle", gyro.getAngle());
    }

    public void putSpeed() {
        SmartDashboard.putNumber("LeftSpeed", leftParent.getSelectedSensorVelocity()*CONVERSION_RATE_VELOCITY);
        SmartDashboard.putNumber("RightSpeed", rightParent.getSelectedSensorVelocity()*-CONVERSION_RATE_VELOCITY);
        SmartDashboard.putNumber("LeftEncoder", leftParent.getSelectedSensorPosition());
        SmartDashboard.putNumber("RightEncoder", rightParent.getSelectedSensorVelocity());
        
    }

}