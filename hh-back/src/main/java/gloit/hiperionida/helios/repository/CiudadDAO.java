package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.CiudadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadDAO extends JpaRepository<CiudadModel, Long> {
    List<CiudadModel> findAllByCiudadContainingIgnoreCase(String ciudad);
}
