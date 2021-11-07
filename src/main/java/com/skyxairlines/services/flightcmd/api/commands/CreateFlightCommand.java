package com.skyxairlines.services.flightcmd.api.commands;

import com.skyxairlines.libs.flightsdomain.domain.FlightKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.RoutingKey;

@Data
@AllArgsConstructor
public class CreateFlightCommand {
  @RoutingKey
  private String flightId;
  private FlightKey key;
}
