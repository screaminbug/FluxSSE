package hr.tstrelar.fluxsse.event;

import hr.tstrelar.fluxsse.service.NotificationEventProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class TickSpringEventListener implements ApplicationListener<TickSpringEvent> {

    private final NotificationEventProcessor processor;
    @Override
    public void onApplicationEvent(TickSpringEvent event) {
        processor.getListeners().forEach($ -> {
            log.info("Notifying listener: {} with event: {}", $, event);
            $.onMessage(event.getMessage());
        });
    }
}