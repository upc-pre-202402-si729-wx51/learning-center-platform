package pe.upc.learningcenterplatform.profiles.interfaces.rest.transform;

import pe.upc.learningcenterplatform.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenterplatform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {

    public static ProfileResource toResourceFromEntity(Profile entity){
        return new ProfileResource(
                entity.getId(),
                entity.getFullName(),
                entity.getEmailAddress(),
                entity.getStreetAddress()
        );
    }
}
