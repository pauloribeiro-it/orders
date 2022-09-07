package investiments.orders.dtos;

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
    private LocalDate dataProvento;

}
