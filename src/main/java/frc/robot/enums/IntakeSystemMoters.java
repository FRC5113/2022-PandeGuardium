package frc.robot.enums;

public enum IntakeSystemMoters {
  IndexerForwardOnly(true, false, true),
  IntakeForwardOnly(false, true, true),
  IndexerIntakeForward(true, true, true),
  IndexerBackwardOnly(true, false, false),
  IntakeBackwardOnly(false, true, false),
  IndexerIntakeBackward(true, true, false);

  private final boolean indexer;
  private final boolean intake;
  private final boolean forward;

  IntakeSystemMoters(boolean indexer, boolean intake, boolean forward) {
    this.indexer = indexer;
    this.intake = intake;
    this.forward = forward;
  }

  public boolean usingIndexer() {
    return indexer;
  }

  public boolean usingIntake() {
    return intake;
  }

  public boolean isForward() {
    return forward;
  }

  public boolean isBackward() {
    return !forward;
  }
}
