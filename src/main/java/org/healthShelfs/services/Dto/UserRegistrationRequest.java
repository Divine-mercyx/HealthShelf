package org.healthShelfs.services.Dto;

import lombok.Getter;
import lombok.Setter;
import org.healthShelfs.data.models.User;
import org.healthShelfs.data.models.UserProfile;

@Setter
@Getter
public class UserRegistrationRequest {
    private User user;
    private UserProfile profile;
}