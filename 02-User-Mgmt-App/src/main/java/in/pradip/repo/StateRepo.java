package in.pradip.repo;

import in.pradip.dto.StateDto;
import in.pradip.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepo extends JpaRepository<StateEntity, Integer> {

    public List<StateEntity> findByCountryCountryId(Integer countryId);
}
