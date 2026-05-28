package in.pradip.repo;

import in.pradip.dto.CountryDto;
import in.pradip.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepo extends JpaRepository<CountryEntity, Integer> {
    public List<CountryEntity> findAll();
}
