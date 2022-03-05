package frc.robot.subsystems;

import static frc.robot.Constants.LimelightConstants.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  private NetworkTable table;
  private NetworkTableEntry tx, ty, ta, tv, ts;
  private double x, y, area, valid, skew;

  public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");
    ts = table.getEntry("ts");
  }

  public void update() {
    x = tx.getDouble(0);
    y = ty.getDouble(0);
    area = ta.getDouble(0);
    valid = tv.getDouble(0);
    skew = ts.getDouble(0);
  }

  public double getTx() {
    update();
    return x;
  }

  public double getTv() {
    update();
    return valid;
  }

  public double getDistaceToTarget() {
    update();
    // return 0.0;
    // Add constants once we have them
    // Note: targetHeight and limelightHeight are in feet!
    // Also, the output is in feet
    double distance = (targetHeight - limelightHeight) / Math.tan(Math.toRadians(ANGLE + y));
    SmartDashboard.putNumber("distanceToTarget", distance);
    System.out.println("Distance: " + distance);
    return distance + 2 + 1.5; // 1.5 is accounting for slight offset from the center
  }

  // Not working physx equation
  // public double getTargetFlyWheelSpeed(double metersToTarget) {
  //   return ShooterConstants.velocityConstant
  //       * Math.sqrt(
  //           ((-4.9) * (Math.pow(metersToTarget, 2)))
  //               / Math.pow(Math.cos(Math.toRadians(ShooterConstants.shootAngle)), 2)
  //               * (LimelightConstants.targetHeight
  //                   - (ShooterConstants.ballStartHeight
  //                       + (metersToTarget
  //                           * Math.tan(
  //                               Math.toRadians(
  //                                   ShooterConstants.shootAngle)))))); // dont question; it works
  // :)
  // }

  // "better" line of best fit
  public int getDesiredSpeed() {
    double distance = getDistaceToTarget();
    int desiredSpeed = (int) (11420 + (194.15 * distance) + 13.64375 * (distance * distance));
    return desiredSpeed;
  }
}
