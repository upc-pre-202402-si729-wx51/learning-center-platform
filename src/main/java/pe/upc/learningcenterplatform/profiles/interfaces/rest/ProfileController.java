package pe.upc.learningcenterplatform.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.learningcenterplatform.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.upc.learningcenterplatform.profiles.domain.services.ProfileCommandService;
import pe.upc.learningcenterplatform.profiles.domain.services.ProfileQueryService;
import pe.upc.learningcenterplatform.profiles.interfaces.rest.resources.CreateProfileResource;
import pe.upc.learningcenterplatform.profiles.interfaces.rest.resources.ProfileResource;
import pe.upc.learningcenterplatform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import pe.upc.learningcenterplatform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;

/**
 * ProfileController
 * <p>
 *     This class represents the REST controller for the Profile domain.
 *     It exposes the endpoints for creating and retrieving profiles.
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfileController {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Creates a new profile
     * @param resource the resource containing the data to create the profile
     * @return the created profile
     */
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource){
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if(profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    /**
     * Retrieves a profile by its id
     * @param profileId the id of the profile to retrieve
     * @return the Profile resource associated with the given Profile id
     */
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId){
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if(profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

}
