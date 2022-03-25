package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  // private WPI_TalonFX rightRotate;
  private WPI_TalonFX leftRotate;
  // private WPI_TalonFX rightArmLeftExtend;
  // private WPI_TalonFX rightArmRightExtend;
  private WPI_TalonFX leftArmLeftExtend;
  private WPI_TalonFX leftArmRightExtend;

  public Climber() {
    // rightRotate = new WPI_TalonFX(HANGER_RIGHT_ROTATE_ID);
    // leftRotate = new WPI_TalonFX(HANGER_LEFT_ROTATE_ID);

    // rightArmLeftExtend = new WPI_TalonFX(HANGER_RIGHT_ARM_LEFT_EXTEND_ID);
    // rightArmRightExtend = new WPI_TalonFX(HANGER_RIGHT_ARM_RIGHT_EXTEND_ID);
    leftArmLeftExtend = new WPI_TalonFX(HANGER_LEFT_ARM_LEFT_EXTEND_ID);
    leftArmRightExtend = new WPI_TalonFX(HANGER_LEFT_ARM_RIGHT_EXTEND_ID);

    // configureMotor(rightRotate);
    // configureMotor(leftRotate);

    // configureMotor(rightArmLeftExtend);
    // configureMotor(rightArmRightExtend);
    configureMotor(leftArmLeftExtend);
    configureMotor(leftArmRightExtend);

    // rightRotate.setInverted(true); //not sure if this line is needed
    // rightRotate.set(ControlMode.Follower, leftRotate.getDeviceID());

    // leftArmRightExtend.setInverted(true);
    leftArmRightExtend.set(ControlMode.Follower, leftArmLeftExtend.getDeviceID());

    // rightArmLeftExtend.set(ControlMode.Follower,
    // leftArmLeftExtend.getDeviceID());
    // rightArmRightExtend.set(ControlMode.Follower,
    // rightArmLeftExtend.getDeviceID());
  }

  private void configureMotor(WPI_TalonFX motor) {
    motor.configFactoryDefault(); // Resetting the motors to make sure there's no junk on there
    // before configuring
    // motor.configVoltageCompSaturation(DRIVE_MAX_VOLTAGE); // only use 12.3 volts
    // regardless of
    // battery voltage
    // motor.enableVoltageCompensation(true); // enable ^
    motor.setNeutralMode(
        NeutralMode.Brake); // set it so that when the motor is getting no input, it stops
    motor.configSelectedFeedbackSensor(
        FeedbackDevice.IntegratedSensor); // configure the encoder (it's inside)
    motor.setSelectedSensorPosition(0); // reset the encoder to have a value of 0
    motor.configOpenloopRamp(RAMP_RATE); // how long it takes to go from 0 to the set speed
    motor.setSensorPhase(true);
    // motor.config_kP(0, 0.001);
    // motor.config_kI(0, 0);
    // motor.config_kD(0, 0);
    // motor.config_kF(0, 0);
    // Make sure that both sides' encoders are getting positive values when going
    // forward
  }

  public void extend(double speed) {
    leftArmLeftExtend.set(speed);
  }

  // THE RIGHT COULD BE INVERSED
  public void rotate(double speed) {
    leftRotate.set(speed);
  }

  public void stopExtender() {
    leftArmLeftExtend.set(0);
  }

  public void stopRotator() {
    leftRotate.set(0);
  }
}
