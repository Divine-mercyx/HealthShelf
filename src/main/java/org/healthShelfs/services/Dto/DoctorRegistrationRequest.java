package org.healthShelfs.services.Dto;

import lombok.Getter;
import lombok.Setter;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.doctors.DoctorProfile;

@Setter
@Getter
public class DoctorRegistrationRequest {
    private Doctor doctor;
    private DoctorProfile doctorProfile;
}
