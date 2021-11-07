package com.skyxairlines.services.flightcmd.business.exceptions;

public class LegAlreadyExistsException extends Exception {
  public LegAlreadyExistsException(String message) {
    super(message);
  }
}
