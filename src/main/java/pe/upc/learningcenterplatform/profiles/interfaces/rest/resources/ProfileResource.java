package pe.upc.learningcenterplatform.profiles.interfaces.rest.resources;

public record ProfileResource(Long id,
                              String fullName,
                              String email,
                              String streetAddress) {
}
