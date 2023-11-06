package gloit.hiperionida.helios.mapper.creation;

import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.util.mapper.creation.AbsAuditoriaCreation;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PagoCreation extends AbsAuditoriaCreation {
    private String id;
    private String monto;
    private String notas;
    private String tipoPago;

    private String compraId;
    private String facturaId;
    private String reciboId;
}
