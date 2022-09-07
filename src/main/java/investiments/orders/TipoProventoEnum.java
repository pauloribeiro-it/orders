package investiments.orders;

import lombok.Getter;

@Getter
public enum TipoProventoEnum {

    RENDIMENTO(1, "Rendimento"),
    DIVIDENDO(2, "Dividendo"),
    JCP(3, "Juros sobre capital próprio");

    private final Integer id;
    private final String descricao;

    TipoProventoEnum(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
}
