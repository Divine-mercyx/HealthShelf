package org.healthShelfs.data.models.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
public class Appointment {
    @Id
    private String id;

    @DBRef
    private User patient;

    @DBRef
    private Doctor doctor;

    private String appointmentDetail;

    private LocalDateTime date;
}
