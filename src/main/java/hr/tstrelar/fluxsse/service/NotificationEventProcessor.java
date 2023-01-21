package hr.tstrelar.fluxsse.service;

import hr.tstrelar.fluxsse.event.NotificationEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class NotificationEventProcessor {
    final Map<String, NotificationEventListener> listeners = new ConcurrentHashMap<>();

    public void register(String id, NotificationEventListener listener) {
        log.info("Registering new NotificationEventListener with id: {}", id);
        listeners.put(id, listener);
        logCurrentListeners();
    }

    public Collection<NotificationEventListener> getListeners() {
        if (!listeners.isEmpty()) {
            log.info("Retrieving all listeners: {}", listeners.keySet());
        }
        return listeners.values();
    }

    public Optional<NotificationEventListener> getListener(String id) {
        log.info("Retrieving listener with id: {}", id);
        return Optional.ofNullable(listeners.get(id));
    }

    public void remove(String id) {
        log.info("Removing listener with id: {}", id);
        listeners.remove(id);
        logCurrentListeners();
    }

    private void logCurrentListeners() {
        log.info("Now there are total of {} listeners, ids: {}", listeners.size(), listeners.keySet());
    }
}
