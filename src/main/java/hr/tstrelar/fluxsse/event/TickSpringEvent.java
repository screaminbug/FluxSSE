package hr.tstrelar.fluxsse.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class TickSpringEvent extends ApplicationEvent {
    private final String message;

    public TickSpringEvent(Object source, String message) {
        super(source);
        //log.info("Generating tick event. Source: {}, message: {}", source.getClass().getName(), message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Event reference: " + super.toString() + " message: " + message;
    }
}