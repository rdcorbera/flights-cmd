package com.skyxairlines.services.flightcmd.api.aggregates;

import com.skyxairlines.services.flightcmd.api.commands.CreateFlightCommand;
import com.skyxairlines.services.flightcmd.api.events.FlightCreatedEvent;
import com.skyxairlines.services.flightcmd.business.domain.FlightKey;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class Flight {
  @AggregateIdentifier
  private FlightKey key;

  public Flight() {}

  @CommandHandler
  public Flight(CreateFlightCommand command) {
    AggregateLifecycle.apply(new FlightCreatedEvent(command.getKey()));
  }

  @EventSourcingHandler
  public void on(FlightCreatedEvent event) {
    key = event.getKey();
  }
}
