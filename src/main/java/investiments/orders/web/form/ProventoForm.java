package investiments.orders.web.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import investiments.orders.enums.TipoProventoEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class ProventoForm {

    private BigDecimal valor;

    private String codigoAtivo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataProvento;

    private TipoProventoEnum tipoProvento;
}
