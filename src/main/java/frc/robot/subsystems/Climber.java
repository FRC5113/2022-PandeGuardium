package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private WPI_TalonFX rightRotate;
  private WPI_TalonFX leftRotate;
  private WPI_TalonFX rightExtend;
  private WPI_TalonFX leftExtend;

  public Climber() {
    rightRotate = new WPI_TalonFX(HANGER_RIGHT_ROTATE_ID);
    leftRotate = new WPI_TalonFX(HANGER_LEFT_ROTATE_ID);
    rightExtend = new WPI_TalonFX(HANGER_RIGHT_EXTEND_ID);
    leftExtend = new WPI_TalonFX(HANGER_LEFT_EXTEND_ID);

    configureMotor(rightRotate);
    configureMotor(leftRotate);
    configureMotor(rightExtend);
    configureMotor(leftExtend);

    leftRotate.set(ControlMode.Follower, rightRotate.getDeviceID());
    leftExtend.set(ControlMode.Follower, rightExtend.getDeviceID());
  }

  private void configureMotor(WPI_TalonFX motor) {
    motor.configFactoryDefault(); // Resetting the motors to make sure there's no junk on there
    // before configuring
    // motor.configVoltageCompSaturation(DRIVE_MAX_VOLTAGE); // only use 12.3 volts regardless of
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
    rightExtend.set(speed);
  }

  // THE RIGHT COULD BE INVERSED
  public void rotate(double speed) {
    rightRotate.set(speed);
  }

  public void stopExtender(boolean right) {
    rightExtend.set(0);
  }

  public void stopRotator(boolean right) {
    rightRotate.set(0);
  }
}
