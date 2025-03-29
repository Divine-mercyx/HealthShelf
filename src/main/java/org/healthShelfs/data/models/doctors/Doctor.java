package org.healthShelfs.data.models.doctors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String username;

    private String password;

    @DBRef
    private DoctorProfile doctorProfile;
}
