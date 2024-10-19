package pe.upc.learningcenterplatform.profiles.domain.model.valueobjetcs;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailAddress(@Email String address) {
    public EmailAddress() { this(null); }
}
