package pe.upc.learningcenterplatform.profiles.interfaces.rest.transform;

import pe.upc.learningcenterplatform.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.learningcenterplatform.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource){

        return new CreateProfileCommand(resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.postalCode(),
                resource.country());
    }
}
