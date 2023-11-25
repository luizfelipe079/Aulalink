package com.desafioFinal.DesafioFinal.repositories.google;

import com.desafioFinal.DesafioFinal.models.google.CalendarEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarEventRepository extends CrudRepository<CalendarEvent, String> {

    Optional<List<CalendarEvent>> findByUserID(String userID);

    Boolean existsByEventID(String eventID);

    Boolean existsCalendarEventByEventID(String eventID);

    CalendarEvent findByEventID(String eventID);


}
