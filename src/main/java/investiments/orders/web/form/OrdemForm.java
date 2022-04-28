package investiments.orders.web.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrdemForm {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataOrdem;
    private Float preco;
    private Integer quantidade;
    private String codAtivo;

}
