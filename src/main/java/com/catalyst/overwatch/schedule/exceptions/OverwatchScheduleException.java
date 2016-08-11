package com.catalyst.overwatch.schedule.exceptions;

/**
 * An exception class for the Schedule Service.
 *
 * @author hmccardell
 */
public class OverwatchScheduleException extends RuntimeException {

  private static final long serialVersionUID = -4346173583895663903L;

  public OverwatchScheduleException(){}

  public OverwatchScheduleException(String overwatchMessage, Exception exception) {
    super(overwatchMessage,exception);
  }

  public OverwatchScheduleException(String overwatchMessage) {
    super(overwatchMessage);
  }

  public OverwatchScheduleException(Exception exception) {
    super(exception);
  }
}
