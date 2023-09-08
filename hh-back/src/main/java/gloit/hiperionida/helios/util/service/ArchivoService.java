package gloit.hiperionida.helios.util.service;

import gloit.hiperionida.helios.util.mapper.creation.ArchivoCreation;
import gloit.hiperionida.helios.util.model.ArchivoModel;

import java.io.IOException;

public interface ArchivoService extends GenericService<ArchivoModel, ArchivoCreation> {
    ArchivoModel guardarArchivo(byte[] file, String nombre, String tipo, String descripcion, String tamanio) throws IOException;
}
