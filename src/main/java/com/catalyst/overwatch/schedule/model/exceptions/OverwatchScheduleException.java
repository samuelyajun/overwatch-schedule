package com.catalyst.overwatch.schedule.model.exceptions;

/**
 * An exception class for the Schedule Service.
 *
 * @author hmccardell
 */
public class OverwatchScheduleException extends RuntimeException {

  Exception exception;
  String overwatchMessage;

  public OverwatchScheduleException(){}

  public OverwatchScheduleException(Exception exception, String overwatchMessage) {
    this.exception = exception;
    this.overwatchMessage = overwatchMessage;
  }

  /**
   * Sets new message.
   *
   * @param overwatchMessage New value of message.
   */
  public void setOverwatchMessage(String overwatchMessage) {
    this.overwatchMessage = overwatchMessage;
  }

  /**
   * Sets new exception.
   *
   * @param exception New value of exception.
   */
  public void setException(Exception exception) {
    this.exception = exception;
  }

  /**
   * Gets exception.
   *
   * @return Value of exception.
   */
  public Exception getException() {
    return exception;
  }

  /**
   * Gets message.
   *
   * @return Value of message.
   */
  public String getOverwatchMessage() {
    return overwatchMessage;
  }

  @Override
  public String toString() {
    return "OverwawtchScheduleException{" +
            "exception=" + exception +
            ", overwatchMessage='" + overwatchMessage + '\'' +
            '}';
  }
}
