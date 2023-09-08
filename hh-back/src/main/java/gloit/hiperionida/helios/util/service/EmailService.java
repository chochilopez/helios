package gloit.hiperionida.helios.util.service;

import gloit.hiperionida.helios.util.mapper.creation.EmailCreation;
import gloit.hiperionida.helios.util.model.EmailModel;

public interface EmailService extends GenericService<EmailModel, EmailCreation>{
    EmailModel enviarEmailSimple(EmailCreation emailCreation);
}

