package com.skyxairlines.services.flightcmd.gateway.flights;

import com.skyxairlines.services.flightcmd.api.commands.CreateFlightCommand;
import com.skyxairlines.services.flightcmd.business.domain.FlightKey;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/flights")
@RestController
public class FlightController {
  private final CommandGateway commandGateway;

  public FlightController(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping()
  public ResponseEntity create(@RequestBody CreateFlightRequest request) {
    commandGateway.send(new CreateFlightCommand(new FlightKey(
        request.getAirlineCode(),
        request.getFlightNumber(),
        request.getOrigin(),
        request.getDepartureDate())));

    return ResponseEntity.ok().build();
  }
}
