package com.skyxairlines.services.flightcmd.api.commands;

import com.skyxairlines.services.flightcmd.business.domain.FlightKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.RoutingKey;

@Data
@AllArgsConstructor
public class CreateFlightCommand {
  @RoutingKey
  private FlightKey key;
}
