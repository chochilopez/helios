package gloit.hiperionida.helios.repository;

import gloit.hiperionida.helios.model.EventoModel;
import gloit.hiperionida.helios.util.repository.GenericDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoDAO extends GenericDTO<EventoModel> {
    @Query(value = "from EventoModel e where :hoy between e.recordatorioFecha AND e.fecha and e.habilitada AND e.recordatorioFecha is not null")
    List<EventoModel> findRecordatorios(@Param("hoy") LocalDateTime hoy);
    @Query(value = "from EventoModel e where :hoy between e.recordatorioFecha AND e.fecha and e.habilitada AND e.recordatorioFecha is not null and e.eliminada is null")
    List<EventoModel> findRecordatoriosAndEliminadaIsNull(@Param("hoy") LocalDateTime hoy);
}
