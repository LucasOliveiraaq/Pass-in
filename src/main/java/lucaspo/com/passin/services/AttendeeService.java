package lucaspo.com.passin.services;

import lombok.RequiredArgsConstructor;
import lucaspo.com.passin.domain.attendee.Attendee;
import lucaspo.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import lucaspo.com.passin.domain.checkin.CheckIn;
import lucaspo.com.passin.dto.attendee.AttendeeDetails;
import lucaspo.com.passin.dto.attendee.AttendeesListResponseDTO;
import lucaspo.com.passin.repositories.AttendeeRepository;
import lucaspo.com.passin.repositories.CheckInRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId)  {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);
        List<AttendeeDetails> attendeeDetails = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
            return new AttendeeDetails(attendee.getId(), attendee.getNome(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetails);
    }

    public void verifyAttendeeSubscripton(String email, String eventId) {
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);
        if(isAttendeeRegistered.isPresent()) throw  new AttendeeAlreadyExistException("Atendee is already registered");
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }
}
