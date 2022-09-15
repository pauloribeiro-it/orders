package investiments.orders.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProventoDTO {

    private String codigoAtivo;
    private Float valor;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataProvento;
    private String tipoProvento;

}
