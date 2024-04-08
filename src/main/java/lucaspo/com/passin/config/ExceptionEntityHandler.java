package lucaspo.com.passin.config;

import lucaspo.com.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import lucaspo.com.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import lucaspo.com.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import lucaspo.com.passin.domain.event.exceptions.EventFullException;
import lucaspo.com.passin.domain.event.exceptions.EventNotFoundException;
import lucaspo.com.passin.dto.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFound(EventNotFoundException eventNotFoundException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<ErrorResponseDTO> handleEventFullException(EventFullException eventFullException) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(eventFullException.getMessage()));
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handleAttendeeNotFound(AttendeeNotFoundException attendeeNotFoundException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handleAttendeeAlreadyExistException(AttendeeAlreadyExistException attendeeAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handleCheckInAlreadyExistException(CheckInAlreadyExistsException attendeeAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
