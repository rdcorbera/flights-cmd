package com.skyxairlines.services.flightcmd.api.aggregates;

import com.skyxairlines.libs.flightsdomain.domain.FlightKey;
import com.skyxairlines.libs.flightsdomain.events.FlightCreatedEvent;
import com.skyxairlines.libs.flightsdomain.events.GateUpdatedEvent;
import com.skyxairlines.libs.flightsdomain.events.LegAddedEvent;
import com.skyxairlines.services.flightcmd.api.commands.AddLegCommand;
import com.skyxairlines.services.flightcmd.api.commands.CreateFlightCommand;
import com.skyxairlines.services.flightcmd.api.commands.UpdateGateCommand;
import com.skyxairlines.services.flightcmd.business.exceptions.LegAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateEntityNotFoundException;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Aggregate
@Slf4j
public class Flight {
  @AggregateIdentifier
  private String id;
  private FlightKey key;
  private List<Leg> legs;

  public Flight() {}

  @CommandHandler
  public Flight(CreateFlightCommand command) {
    AggregateLifecycle.apply(new FlightCreatedEvent(command.getKey()));
  }

  @CommandHandler
  public void handle(AddLegCommand command) throws LegAlreadyExistsException {
    Optional<Leg> legOptional = legs.stream()
        .filter(l -> l.getOrigin().equals(command.getOrigin()) && l.getDestination().equals(command.getDestination()))
        .findFirst();

    if (legOptional.isPresent()) {
      Leg leg = legOptional.get();
      throw new LegAlreadyExistsException(String.format("Leg %s-%s already exists", leg.getOrigin(), leg.getDestination()));
    }

    AggregateLifecycle.apply(new LegAddedEvent(
        command.getFlightId(),
        command.getOrigin(),
        command.getDestination(),
        command.getDepartureDateTime(),
        command.getArrivalDateTime()
    ));
  }

  @CommandHandler
  public void handle(UpdateGateCommand command) {
    Optional<Leg> legOptional = legs.stream()
        .filter(l -> l.getOrigin().equals(command.getOrigin()))
        .findFirst();

    if (!legOptional.isPresent()) {
      Leg leg = legOptional.get();
      throw new AggregateEntityNotFoundException("Leg not found in flight aggregate");
    }

    AggregateLifecycle.apply(new GateUpdatedEvent(
        command.getFlightId(),
        command.getOrigin(),
        command.getGate(),
        command.getTerminal()
    ));
  }

  @EventSourcingHandler
  public void on(FlightCreatedEvent event) {
    id = event.getKey().toString();
    key = event.getKey();
    legs = new ArrayList<>();
  }

  @EventSourcingHandler
  public void on(LegAddedEvent event) {
    this.legs.add(new Leg(event.getOrigin(), event.getDestination(), "", 0));
  }

  @EventSourcingHandler
  public void on(GateUpdatedEvent event) {
    Leg leg = legs.stream()
        .filter(l -> l.getOrigin().equals(event.getOrigin()))
        .findFirst()
        .get();

    leg.setGate(event.getGate());
    leg.setTerminal(event.getTerminal());
  }
}
