package pe.upc.learningcenterplatform.profiles.domain.model.queries;

import pe.upc.learningcenterplatform.profiles.domain.model.valueobjetcs.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
