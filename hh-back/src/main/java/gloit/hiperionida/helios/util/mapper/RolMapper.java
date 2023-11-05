package gloit.hiperionida.helios.util.mapper;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.mapper.dto.RolDTO;
import gloit.hiperionida.helios.util.model.RolModel;
import gloit.hiperionida.helios.util.model.enums.RolEnum;
import gloit.hiperionida.helios.util.repository.RolDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolMapper {
    public final RolDAO rolDAO;

    public RolModel toEntity(RolCreation rolCreation) {
        try {
            RolModel rolModel = new RolModel();

            if (Helper.getLong(rolCreation.getId()) != null)
                rolModel = rolDAO.findByIdAndEliminadaIsNull(Helper.getLong(rolCreation.getId())).get();
            rolModel.setRol(RolEnum.valueOf(rolCreation.getRol()));

            return rolModel;
        } catch (Exception e) {
            log.info("Rol creation to entity error. Exception: " + e);
            return null;
        }
    }

    public RolDTO toDto(RolModel rolModel) {
        try {
            RolDTO dto = new RolDTO();

            dto.setId(rolModel.getId().toString());
            dto.setRol(rolModel.getRol().toString());

            return dto;
        } catch (Exception e) {
            log.info("Rol entity to dto error. Exception: " + e);
            return null;
        }
    }
}