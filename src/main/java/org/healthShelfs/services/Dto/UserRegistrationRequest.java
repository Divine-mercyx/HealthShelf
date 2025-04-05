package org.healthShelfs.services.Dto;

import lombok.Getter;
import lombok.Setter;
import org.healthShelfs.data.models.users.User;
import org.healthShelfs.data.models.users.UserProfile;

@Setter
@Getter
public class UserRegistrationRequest {
    private User user;
    private UserProfile profile;
}