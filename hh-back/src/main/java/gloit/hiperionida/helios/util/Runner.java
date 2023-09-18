package gloit.hiperionida.helios.util;

import gloit.hiperionida.helios.model.ConciliacionBancoModel;
import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.repository.ConciliacionBancoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Runner implements CommandLineRunner {
//    @Autowired
//    ConciliacionBancoDAO conciliacionBancoDAO;

    @Override
    public void run(String... args) throws Exception {
//        conciliacionBancoDAO.save(new ConciliacionBancoModel((long)1, MovimientoEnum.CREDITO, Helper.getNow(""),"Algo", 852.54, null));
//        conciliacionBancoDAO.save(new ConciliacionBancoModel((long)2, MovimientoEnum.DEBITO, Helper.getNow(""),"Algo ffff", 852222.54, null));
//        String entradaTeclado = "";
//        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
//        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
//        entradaTeclado = "WHATSAPP_ERROR_CODE_" + entradaTeclado;
//        System.out.println(WhatsappErrorCodeEnum.valueOf(entradaTeclado).getError());
    }
}
