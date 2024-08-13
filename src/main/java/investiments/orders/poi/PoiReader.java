package investiments.orders.poi;

import investiments.orders.dtos.ProventoImportadoDTO;
import investiments.orders.enums.HeaderArquivoProventos;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Component
@Slf4j
public class PoiReader {
    public List<ProventoImportadoDTO> getProventos(InputStream is){
        List<ProventoImportadoDTO> proventos = new ArrayList<>();
        try {
            //Create Workbook instance for xlsx/xls file input stream
            Workbook workbook = new XSSFWorkbook(is);

            //Get the number of sheets in the xlsx file
            int numberOfSheets = workbook.getNumberOfSheets();

            //loop through each of the sheets
            for (int i = 0; i < numberOfSheets; i++) {

                //Get the nth sheet from the workbook
                Sheet sheet = workbook.getSheetAt(i);

                //every sheet has rows, iterate over them
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    //Get the row object
                    Row row = rowIterator.next();
                    //Every row has columns, get the column iterator and iterate over them
                    Iterator<Cell> cellIterator = row.cellIterator();
                    Map<HeaderArquivoProventos, Object> mapaValores = new HashMap<>();

                    int countColumn = 0;
                    while (cellIterator.hasNext()) {
                        //Get the Cell object
                        Cell cell = cellIterator.next();
                        //check the cell type and process accordingly
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                mapaValores.put(HeaderArquivoProventos.values()[countColumn], cell.getStringCellValue());
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                mapaValores.put(HeaderArquivoProventos.values()[countColumn], BigDecimal.valueOf(cell.getNumericCellValue()));
                        }
                        countColumn++;
                    } //end of cell iterator
                    proventos.add(new ProventoImportadoDTO(mapaValores));
                } //end of rows iterator
            } //end of sheets for loop
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return proventos.subList(0, proventos.size() - 1);
    }
}
