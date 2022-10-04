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
  private WPI_TalonFX rightArmMasterExtend;
  private WPI_TalonFX rightArmSlaveExtend;
  private WPI_TalonFX leftArmMasterExtend;
  private WPI_TalonFX leftArmSlaveExtend;

  public Climber() {
    // rightRotate = new WPI_TalonFX(HANGER_RIGHT_ROTATE_ID);
    // leftRotate = new WPI_TalonFX(HANGER_LEFT_ROTATE_ID);

    rightArmMasterExtend = new WPI_TalonFX(HANGER_RIGHT_ARM_MASTER_EXTEND_ID);
    rightArmSlaveExtend = new WPI_TalonFX(HANGER_RIGHT_ARM_SLAVE_EXTEND_ID);
    leftArmMasterExtend = new WPI_TalonFX(HANGER_LEFT_ARM_MASTER_EXTEND_ID);
    leftArmSlaveExtend = new WPI_TalonFX(HANGER_LEFT_ARM_SLAVE_EXTEND_ID);

    configureMotor(rightArmMasterExtend);
    configureMotor(rightArmSlaveExtend);
    configureMotor(leftArmMasterExtend);
    configureMotor(leftArmSlaveExtend);

    leftArmSlaveExtend.set(ControlMode.Follower, leftArmMasterExtend.getDeviceID());
    rightArmMasterExtend.setInverted(true);
    rightArmSlaveExtend.set(ControlMode.Follower, rightArmMasterExtend.getDeviceID());
  }

  private void configureMotor(WPI_TalonFX motor) {
    motor.configFactoryDefault(); // Resetting the motors to make sure there's no junk on there
    // before configuring
    // motor.configVoltageCompSaturation(DRIVE_MAX_VOLTAGE); // only use 12.3 volts
    // regardless of
    // battery voltage
    // motor.enableVoltageCompensation(true); // enable ^
    motor.setNeutralMode(NeutralMode.Brake); // break for that nice controlability
    motor.configSelectedFeedbackSensor(
        FeedbackDevice.IntegratedSensor); // configure the encoder (it's inside)
    motor.setSelectedSensorPosition(0); // reset the encoder to have a value of 0
    // motor.configOpenloopRamp(RAMP_RATE); // how long it takes to go from 0 to the set speed
    motor.setSensorPhase(true);
    // motor.config_kP(0, 0.001);
    // motor.config_kI(0, 0);
    // motor.config_kD(0, 0);
    // motor.config_kF(0, 0);
    // Make sure that both sides' encoders are getting positive values when going
    // forward
  }

  public void extendLeft(double speed) {
    leftArmMasterExtend.set(speed);
  }

  public void extendRight(double speed) {
    rightArmMasterExtend.set(speed);
    rightArmSlaveExtend.set(-speed);
    // rightArmSlaveExtend.set(speed);
  }

  // THE RIGHT COULD BE INVERSED
  public void rotate(double speed) {
    leftRotate.set(speed);
  }

  public void stopLeftExtender() {
    leftArmMasterExtend.set(0);
  }

  public void stopRightExtender() {
    rightArmMasterExtend.set(0);
    rightArmSlaveExtend.set(0);
  }

  public void stopRotator() {
    leftRotate.set(0);
  }

  public double getExtenderLeftEncoderValue() {
    return leftArmMasterExtend.getSelectedSensorPosition();
  }

  public double getExtenderRightEncoderValue() {
    return rightArmMasterExtend.getSelectedSensorPosition();
  }

  public void resetEncoders() {
    rightArmMasterExtend.setSelectedSensorPosition(0);
    leftArmMasterExtend.setSelectedSensorPosition(0);
  }
}
