package hr.tstrelar.fluxsse.controller;

import hr.tstrelar.fluxsse.service.SseService;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController()
@AllArgsConstructor
public class Sse {

    final SseService service;
    @GetMapping("/sse/{id}")
    public Flux<ServerSentEvent<String>> sse(@PathVariable String id) {
        return service.process(id);
    }

    @GetMapping("/stop/{id}")
    public String stop(@PathVariable String id) {
        return service.stop(id);
    }
}
