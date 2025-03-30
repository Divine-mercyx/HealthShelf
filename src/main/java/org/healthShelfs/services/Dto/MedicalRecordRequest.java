package org.healthShelfs.services.Dto;

import lombok.Getter;
import lombok.Setter;
import org.healthShelfs.data.models.doctors.Doctor;
import org.healthShelfs.data.models.users.MedicalHistory;
import org.healthShelfs.data.models.users.User;

@Setter
@Getter
public class MedicalRecordRequest {
    private MedicalHistory medicalHistory;
    private String patientId;
    private String doctorId;
}
