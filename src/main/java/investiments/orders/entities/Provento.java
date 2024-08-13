package investiments.orders.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "provento")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provento")
    private Integer id;

    @Column(name = "data_provento")
    private LocalDate dataProvento;

    @Column(name = "valor")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_ativo")
    private Ativo ativo;

    @ManyToOne
    @JoinColumn(name = "tipo_provento")
    private TipoProvento tipoProvento;

    @Column(name="qtd")
    private Integer qtd;

    @Column(name="total")
    private BigDecimal total;
}
