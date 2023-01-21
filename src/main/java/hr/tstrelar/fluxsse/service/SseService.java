package hr.tstrelar.fluxsse.service;

import hr.tstrelar.fluxsse.event.NotificationEventListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
@Slf4j
public class SseService {
    private final NotificationEventProcessor processor;
    public Flux<ServerSentEvent<String>> process(String id) {
        return Flux.create(sink ->
            processor.register(id, new NotificationEventListener() {
                @Override
                public void onMessage(String message) {
                    log.info("Adding message to sink for id {}, message: {}", id, message);
                    sink.next(
                        ServerSentEvent.<String>builder()
                            .event("tick")
                            .id(id)
                            .data(message)
                            .build()
                    );
                }

                @Override
                public void onComplete() {
                    log.info("Completing SSE channel with id: {}", id);
                    sink.complete();
                    processor.remove(id);
                }
            })
        );
    }

    public String stop(String id) {
        log.info("Got signal to stop channel with id: {}", id);
        return processor.getListener(id)
                .map($ -> {$.onComplete(); return "SSE channel stopped, id: " + id; })
                .orElse("No SSE channel with id: " + id + " found");
    }
}
