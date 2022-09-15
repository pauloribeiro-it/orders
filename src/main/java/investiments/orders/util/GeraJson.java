package investiments.orders.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import investiments.orders.dtos.ProventoDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GeraJson {

    private enum Posicao {
        codigo, data, tipo_provento, valor
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        List<String> conteudos = obtemConteudoArquivoCsv(new File("C:\\Users\\paulo\\OneDrive\\Área de Trabalho\\Projetos\\orders\\src\\main\\resources\\ORDENS.txt"));
        List<ProventoDTO> proventos = obtemProventos(conteudos);
        System.out.println(objectMapper.writeValueAsString(proventos));
    }

    private static List<String> obtemConteudoArquivoCsv(File file) {
        final List<String> linhas = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(file.toPath())) {
            while (br.ready()) {
                linhas.add(br.readLine());
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return linhas;
    }

    private static List<ProventoDTO> obtemProventos(List<String> conteudos) {
        List<ProventoDTO> proventos = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (String linha : conteudos) {
            String[] colunas = linha.split(";");
            ProventoDTO proventoDTO = new ProventoDTO();
            proventoDTO.setDataProvento(LocalDate.parse(colunas[Posicao.data.ordinal()], dateTimeFormatter));
            proventoDTO.setTipoProvento(colunas[Posicao.tipo_provento.ordinal()]);
            proventoDTO.setValor(Float.valueOf(colunas[Posicao.valor.ordinal()]));
            proventoDTO.setCodigoAtivo(colunas[Posicao.codigo.ordinal()]);
            proventos.add(proventoDTO);
        }

        return proventos;
    }
}
