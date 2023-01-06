package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {

  // private WPI_TalonFX shooterChild;
  private WPI_TalonFX shooterParent;

  public Shooter() {
    shooterParent = new WPI_TalonFX(SHOOTER_PARENT_ID);
    // shooterChild = new WPI_TalonFX(SHOOTER_CHILD_ID);
    configMotor(shooterParent);

    shooterParent.config_kP(0, kP);
    shooterParent.config_kI(0, kI);
    shooterParent.config_kD(0, kD);

    shooterParent.setInverted(true);
  }

  public void configMotor(WPI_TalonFX motor) {
    motor.configFactoryDefault();
    motor.configVoltageCompSaturation(MAX_VOLTAGE);
    motor.enableVoltageCompensation(true);
    motor.configClosedloopRamp(RAMP_RATE);
    motor.setNeutralMode(NeutralMode.Coast);
    motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  public void setSpeed(double speed) {
    shooterParent.set(ControlMode.Velocity, speed);
    SmartDashboard.putNumber("FlyWheelVelocity", shooterParent.getSelectedSensorVelocity());
  }

  public void coast() {
    shooterParent.set(0);
  }

  public double getSpeed() {
    return shooterParent.getSelectedSensorVelocity();
  }

  public void getCurrent() {}

  /**
   * @param desiredSpeed Set the speed at which the shooter should run when it is done
   * @param marginOfError The difference that is allowed before the spin up is complete
   * @return Is the shooter at the desired rate
   */
  public boolean spinToRate(float desiredSpeed, float marginOfError) {
    if (Math.abs(getSpeed() - desiredSpeed) <= ShooterConstants.spinUpTolerance) return true;

    setSpeed(getSpeed() + ShooterConstants.rampUpRate);
    return false;
  }
}
