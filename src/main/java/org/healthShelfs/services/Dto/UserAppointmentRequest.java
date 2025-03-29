package org.healthShelfs.services.Dto;

import lombok.Getter;
import lombok.Setter;
import org.healthShelfs.data.models.appointment.Appointment;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.User;

@Setter
@Getter
public class UserAppointmentRequest {
    private Appointment appointment;
    private User user;
    private Doctor doctor;
}
