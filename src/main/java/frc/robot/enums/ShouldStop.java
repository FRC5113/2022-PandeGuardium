package frc.robot.enums;

public enum ShouldStop {
  Yes(true),
  No(false);

  private boolean shouldStop;

  ShouldStop(boolean shouldStop) {
    this.shouldStop = shouldStop;
  }

  public boolean shouldStop() {
    return shouldStop;
  }
}
