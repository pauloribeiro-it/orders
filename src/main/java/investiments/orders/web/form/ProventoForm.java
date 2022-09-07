package investiments.orders.web.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import investiments.orders.TipoProventoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ProventoForm {

    private Float valor;

    private String codigoAtivo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataProvento;

    private TipoProventoEnum tipoProvento;
}
