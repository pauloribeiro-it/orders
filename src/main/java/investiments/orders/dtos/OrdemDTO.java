package investiments.orders.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class OrdemDTO {

    private final Integer idOrdem;
    private final String codigoAtivo;
    private final Float totalOrdem;
    private final Float precoAtivo;
    private final Integer qtdItens;
    private final LocalDate dataOrdem;

}
