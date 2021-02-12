package auth;

import domain.Email;
import domain.Roles;

import java.io.Serializable;

public interface UserLoggableInterface extends Serializable {
    Roles getRole();
    Email getEmail();
}
