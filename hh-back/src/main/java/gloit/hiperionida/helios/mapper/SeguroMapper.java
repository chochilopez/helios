package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.mapper.dto.SeguroDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.AcopladoDAO;
import gloit.hiperionida.helios.repository.CamionDAO;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.repository.ProveedorDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeguroMapper {
    private final AcopladoDAO acopladoDAO;
    private final CamionDAO camionDAO;
    private final EventoDAO eventoDAO;
    private final ProveedorDAO proveedorDAO;
    private final UsuarioServiceImpl usuarioService;

    public SeguroModel toEntity(SeguroCreation creation) {
        try {
            SeguroModel model = new SeguroModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getLong(creation.getAcopladoId()) != null)
                model.setAcopladoId(Helper.getLong(creation.getAcopladoId()));
            if (Helper.getLong(creation.getAseguradoraId()) != null)
                model.setAseguradoraId(Helper.getLong(creation.getAseguradoraId()));
            if (Helper.getLong(creation.getCamionId()) != null)
                model.setCamionId(Helper.getLong(creation.getCamionId()));
            if (creation.getVencimiento() != null) {
                String marcaModelo = "";
                if (model.getCamionId() != null) {
                    CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getCamionId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el camión."));
                    marcaModelo = marcaModelo + camionModel.getMarcaModelo();
                } else {
                    AcopladoModel acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getAcopladoId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el acoplado."));
                    marcaModelo = marcaModelo + acopladoModel.getMarcaModelo();
                }
                EventoModel evento = eventoDAO.save(new EventoModel(
                        Helper.stringToLocalDateTime("00:00:00 " + creation.getVencimiento(), ""),
                        "Seguro para " + marcaModelo,
                        null,
                        null,
                        "Presupuesto",
                        Helper.getNow(""),
                        usuarioService.obtenerUsuario().getId()
                ));
                model.setVencimientoId(evento.getId());
            }

            if (Helper.getLong(creation.getCreador_id()) != null)
                model.setCreador_id(Helper.getLong(creation.getCreador_id()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), ""));
            if (Helper.getLong(creation.getModificador_id()) != null)
                model.setModificador_id(Helper.getLong(creation.getModificador_id()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), ""));
            if (Helper.getLong(creation.getEliminador_id()) != null)
                model.setEliminador_id(Helper.getLong(creation.getEliminador_id()));
            if (!Helper.isEmptyString(creation.getEliminada()))
                model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), ""));

            return model;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public SeguroDTO toDto(SeguroModel model) {
        try {
            SeguroDTO dto = new SeguroDTO();

            dto.setId(model.getId().toString());
            dto.setNotas(model.getNotas());
            if (model.getAcopladoId() != null) {
                AcopladoModel acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(model.getAcopladoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el acoplado con id: " + model.getAcopladoId() + "."));
                dto.setAcoplado(acopladoModel.getMarcaModelo());
            }
            if (model.getAseguradoraId() != null) {
                ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(model.getAseguradoraId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la asegurador con id: " + model.getAseguradoraId() + "."));
                dto.setAseguradora(proveedorModel.getNombre());
            }
            if (model.getCamionId() != null) {
                CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(model.getCamionId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el camión con id: " + model.getCamionId() + "."));
                dto.setCamion(camionModel.getMarcaModelo());
            }
            if (model.getVencimientoId() != null) {
                EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(model.getVencimientoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el vencimiento con id: " + model.getVencimientoId() + "."));
                dto.setVencimiento(eventoModel.getFecha().toString());
            }

            if (model.getCreador_id() != null)
                dto.setCreador(usuarioService.buscarPorId(model.getCreador_id()).getNombre());
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null)
                dto.setModificador(usuarioService.buscarPorId(model.getModificador_id()).getNombre());
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null)
                dto.setEliminador(usuarioService.buscarPorId(model.getEliminador_id()).getNombre());
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}