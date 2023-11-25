package com.desafioFinal.DesafioFinal.models.google;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_calendar")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalendarEvent {

    @Id
    @Column(name = "event_id")
    private String eventID;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "description")
    private String description;

}
