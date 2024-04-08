package lucaspo.com.passin.controllers;

import lombok.RequiredArgsConstructor;
import lucaspo.com.passin.dto.attendee.AttendeeBagdeResponseDTO;
import lucaspo.com.passin.services.AttendeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBagdeResponseDTO> getAttendeeBagde(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeBagdeResponseDTO response =  this.attendeeService.getAttendeeBagde(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok(response);
    }
}
