package pe.upc.learningcenterplatform.profiles.application;

import org.springframework.stereotype.Service;
import pe.upc.learningcenterplatform.profiles.domain.model.aggregates.Profile;
import pe.upc.learningcenterplatform.profiles.domain.model.queries.GetAllProfilesQuery;
import pe.upc.learningcenterplatform.profiles.domain.model.queries.GetProfileByEmailQuery;
import pe.upc.learningcenterplatform.profiles.domain.model.queries.GetProfileByIdQuery;
import pe.upc.learningcenterplatform.profiles.domain.services.ProfileQueryService;
import pe.upc.learningcenterplatform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.emailAddress());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}
