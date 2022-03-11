package frc.robot.subsystems;

import static frc.robot.Constants.HangerConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hanger extends SubsystemBase {

  private CANSparkMax rightRotate;
  private CANSparkMax leftRotate;
  private CANSparkMax rightExtend;
  private CANSparkMax leftExtend;

  public Hanger() {
    rightRotate = new CANSparkMax(HANGER_RIGHT_ROTATE_ID, MotorType.kBrushless);
    leftRotate = new CANSparkMax(HANGER_LEFT_ROTATE_ID, MotorType.kBrushless);
    rightExtend = new CANSparkMax(HANGER_RIGHT_EXTEND_ID, MotorType.kBrushless);
    leftExtend = new CANSparkMax(HANGER_LEFT_EXTEND_ID, MotorType.kBrushless);

    configMotor(rightRotate);
    configMotor(leftRotate);
    configMotor(rightExtend);
    configMotor(leftExtend);

    // shooterParent.set
  }

  public void configMotor(CANSparkMax motor) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.enableVoltageCompensation(MAXIMUM_VOLTAGE);
    motor.setSmartCurrentLimit(MAXIMUM_CURRENT);
    motor.burnFlash();
  }

  public void extend(boolean right, double speed) {
    if (right) {
      rightExtend.set(speed);
    } else {
      leftExtend.set(speed);
    }
  }

  public void rotate(boolean right, double speed) {
    if (right) {
      rightRotate.set(speed);
    } else {
      leftRotate.set(speed);
    }
  }

  public void stopExtender(boolean right) {
    if (right) {
      rightExtend.set(0);
    } else {
      leftExtend.set(0);
    }
  }

  public void stopRotator(boolean right) {
    if (right) {
      rightRotate.set(0);
    } else {
      leftRotate.set(0);
    }
  }
}
