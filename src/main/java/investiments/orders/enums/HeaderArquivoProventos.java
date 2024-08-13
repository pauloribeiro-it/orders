package investiments.orders.enums;

import lombok.Getter;

@Getter
public enum HeaderArquivoProventos {
    PRODUTO("Produto"),
    DATA_PAGAMENTO("Pagamento"),
    TIPO_EVENTO("Tipo de Evento"),
    INSTITUICAO("Instituição"),
    QUANTIDADE("Quantidade"),
    PRECO_UNITARIO("Preço unitário"),
    VALOR_LIQUIDO("Valor líquido");

    private String descricao;

    HeaderArquivoProventos(String descricao){
        this.descricao = descricao;
    }
}
