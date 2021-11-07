package com.skyxairlines.services.flightcmd.api.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Data
@AllArgsConstructor
public class Leg {
  private String origin;
  private String destination;
}
