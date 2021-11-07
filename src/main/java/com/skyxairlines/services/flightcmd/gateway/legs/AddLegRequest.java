package com.skyxairlines.services.flightcmd.gateway.legs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddLegRequest {
  private String origin;
  private String destination;
  private LocalDateTime departure;
  private LocalDateTime arrival;
}
