package hr.tstrelar.fluxsse.scheduled;

import hr.tstrelar.fluxsse.event.TickSpringEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
@Slf4j
public class ScheduledEvents {
    private final ApplicationEventPublisher publisher;
    @Scheduled(fixedRate = 1234)
    public void clockTick() {
        final var time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        //log.info("Publishing tick event: {}", time);
        publisher.publishEvent(new TickSpringEvent(this, time));
    }
}
