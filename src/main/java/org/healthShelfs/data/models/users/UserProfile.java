package org.healthShelfs.data.models.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.healthShelfs.data.models.Gender;
import org.healthShelfs.data.models.JobField;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String age;
    private Gender gender;
    private String address;
    private String phone;
}
