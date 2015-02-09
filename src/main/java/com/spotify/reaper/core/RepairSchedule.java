package com.spotify.reaper.core;

import org.joda.time.DateTime;

/**
 * Represents a repair schedule for single RepairRun instance.
 */
public class RepairSchedule {

  private final long id;
  private final long repairUnitId;
  private final DateTime lastActivation;
  private final DateTime nextActivation;
  private final int daysBetween;

  private RepairSchedule(Builder builder, long id) {
    this.id = id;
    this.repairUnitId = builder.repairUnitId;
    this.lastActivation = builder.lastActivation;
    this.nextActivation = builder.nextActivation;
    this.daysBetween = builder.daysBetween;
  }

  public long getId() {
    return id;
  }

  public long getRepairUnitId() {
    return repairUnitId;
  }

  public DateTime getLastActivation() {
    return lastActivation;
  }

  public DateTime getNextActivation() {
    return nextActivation;
  }

  public int getDaysBetween() {
    return daysBetween;
  }

  public DateTime getFollowingActivation() {
    return getNextActivation().plusDays(getDaysBetween());
  }

  public Builder with() {
    return new Builder(this);
  }

  public static class Builder {

    public final long repairUnitId;
    public final int daysBetween;
    public DateTime lastActivation;
    public DateTime nextActivation;

    public Builder(long repairUnitId, int daysBetween, DateTime nextActivation) {
      this.repairUnitId = repairUnitId;
      this.daysBetween = daysBetween;
      this.nextActivation = nextActivation;
    }

    private Builder(RepairSchedule original) {
      repairUnitId = original.repairUnitId;
      lastActivation = original.lastActivation;
      nextActivation = original.nextActivation;
      daysBetween = original.daysBetween;
    }

    public Builder lastActivation(DateTime lastActivation) {
      this.lastActivation = lastActivation;
      return this;
    }

    public Builder nextActivation(DateTime nextActivation) {
      this.nextActivation = nextActivation;
      return this;
    }

    public RepairSchedule build(long id) {
      return new RepairSchedule(this, id);
    }
  }

}
