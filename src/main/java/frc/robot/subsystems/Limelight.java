package frc.robot.subsystems;

import static frc.robot.Constants.LimelightConstants.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
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
    return 0.0;
    // Add constants once we have them
    /*
    double distance = (targetHeight - limelightHeight ) / Math.tan(ANGLE+y);
    return distance;
    */
  }

  public double getDesiredSpeed() {
    return 0.0;
  }
}
