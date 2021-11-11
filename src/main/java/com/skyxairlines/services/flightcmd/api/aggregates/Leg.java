package com.skyxairlines.services.flightcmd.api.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Leg {
  private String origin;
  private String destination;
  private String gate;
  private int terminal;
}
