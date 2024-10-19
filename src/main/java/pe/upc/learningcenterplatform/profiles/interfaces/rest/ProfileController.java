package pe.upc.learningcenterplatform.profiles.interfaces.rest;

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

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource){
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if(profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId){
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if(profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

}
