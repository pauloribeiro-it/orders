package investiments.orders.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="ordem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ordem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ordem")
    private Integer idOrdem;

    @Column(name="data_ordem")
    private LocalDate dataOrdem;

    @Column(name="preco")
    private Float preco;

    @Column(name="quantidade")
    private Integer quantidade;

    @Column(name="total")
    private Float total;

    @ManyToOne
    @JoinColumn(name="id_ativo")
    private Ativo ativo;

}
