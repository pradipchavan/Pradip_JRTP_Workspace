package in.pradip.repo;

import in.pradip.dto.StateDto;
import in.pradip.entity.CityEntity;
import in.pradip.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<CityEntity, Integer> {

    public List<CityEntity> findByStateStateId(Integer stateId);
}
