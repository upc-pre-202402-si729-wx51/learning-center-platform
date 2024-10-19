package pe.upc.learningcenterplatform.profiles.application;

import org.springframework.stereotype.Service;
import pe.upc.learningcenterplatform.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenterplatform.profiles.domain.model.commands.CreateProfileCommand;
import pe.upc.learningcenterplatform.profiles.domain.model.valueobjetcs.EmailAddress;
import pe.upc.learningcenterplatform.profiles.domain.services.ProfileCommandService;
import pe.upc.learningcenterplatform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {

        var emailAddress = new EmailAddress(command.email());

        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile withe email " + command.email() + " already exists");
        });

        var profile = new Profile(command);
        var createdProfile = profileRepository.save(profile);
        return Optional.of(createdProfile);
    }
}
