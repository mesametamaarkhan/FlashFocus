package com.flashfocus.flashfocus.dto;

import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * UserDTO is a Data Transfer Object (DTO) that represents the user data to be transferred between
 * different layers of the application. It contains fields for user ID, username, email, and
 * notification preferences.
 */
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private Map<String, Boolean> notificationPreferences;  
}
