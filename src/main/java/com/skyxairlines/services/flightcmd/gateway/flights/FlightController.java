package com.skyxairlines.services.flightcmd.gateway.flights;

import com.skyxairlines.libs.flightsdomain.domain.FlightKey;
import com.skyxairlines.services.flightcmd.api.commands.CreateFlightCommand;
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
    FlightKey key = new FlightKey(
        request.getAirlineCode(),
        request.getFlightNumber(),
        request.getOrigin(),
        request.getDepartureDate());

    commandGateway.send(new CreateFlightCommand(key.toString(), key));

    return ResponseEntity.ok().build();
  }
}
