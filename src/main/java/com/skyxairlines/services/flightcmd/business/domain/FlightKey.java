package com.skyxairlines.services.flightcmd.business.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FlightKey {
  private String airlineCode;
  private int flightNumber;
  private String origin;
  private LocalDate departureDate;

  public String toString() {
    return String.format("%s.%s.%s.%s",
        airlineCode,
        flightNumber,
        origin,
        departureDate
    );
  }
}
