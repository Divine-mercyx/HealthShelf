package org.healthShelfs.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String address;
    private String phone;
    private MedicalField medicalField;
}
