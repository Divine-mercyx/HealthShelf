package org.healthShelfs.data.models.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.healthShelfs.data.models.doctors.Doctor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {

    @Id
    private String id;

    @DBRef
    private User patient;

    @DBRef
    private Doctor doctor;

    private String medicalHistoryDetail;
}
