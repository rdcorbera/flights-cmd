package com.skyxairlines.services.flightcmd.api.events;

import com.skyxairlines.services.flightcmd.business.domain.FlightKey;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightCreatedEvent {
  private FlightKey key;
}
