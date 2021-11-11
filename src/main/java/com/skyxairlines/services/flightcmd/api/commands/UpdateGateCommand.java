package com.skyxairlines.services.flightcmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class UpdateGateCommand {
  @TargetAggregateIdentifier
  private String flightId;
  private String origin;
  private String gate;
  private int terminal;
}
