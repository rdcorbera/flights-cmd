package com.skyxairlines.services.flightcmd.api.commands;

import com.skyxairlines.libs.flightsdomain.domain.FlightKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AddLegCommand {
  @TargetAggregateIdentifier
  private String flightId;
  private String origin;
  private String destination;
  private LocalDateTime departureDateTime;
  private LocalDateTime arrivalDateTime;
}
