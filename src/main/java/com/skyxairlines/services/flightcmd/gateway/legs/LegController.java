package com.skyxairlines.services.flightcmd.gateway.legs;

import com.skyxairlines.libs.flightsdomain.domain.FlightKey;
import com.skyxairlines.services.flightcmd.api.commands.AddLegCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/flights")
@RestController
public class LegController {
  private final CommandGateway commandGateway;

  public LegController(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @PostMapping("/{flightId}/legs")
  public ResponseEntity addLeg(
      @PathVariable String flightId,
      @RequestBody AddLegRequest legRequest
  ) {
    commandGateway.send(new AddLegCommand(
        flightId,
        legRequest.getOrigin(),
        legRequest.getDestination(),
        legRequest.getDeparture(),
        legRequest.getArrival()
    ));

    return ResponseEntity.ok().build();
  }
}
