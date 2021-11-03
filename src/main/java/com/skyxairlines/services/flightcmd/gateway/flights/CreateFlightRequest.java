package com.skyxairlines.services.flightcmd.gateway.flights;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateFlightRequest {
  private String airlineCode;
  private int flightNumber;
  private String origin;
  private LocalDate departureDate;
}
