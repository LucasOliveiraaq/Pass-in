package lucaspo.com.passin.services;

import lombok.RequiredArgsConstructor;
import lucaspo.com.passin.domain.event.Event;
import lucaspo.com.passin.dto.event.EventResponseDTO;
import lucaspo.com.passin.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
        return new EventResponseDTO(event, 0);
    }
}
