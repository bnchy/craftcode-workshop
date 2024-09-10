package craftcode.workshop.beer.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseUser {
    private String username;
    private String email;
    private String password;
    private String role;
}
