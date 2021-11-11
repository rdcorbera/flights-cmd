package com.skyxairlines.services.flightcmd.gateway.flights;

import lombok.Data;

@Data
public class UpdateGateRequest {
  private String origin;
  private String gate;
  private int terminal;
}
