package investiments.orders.web;

import investiments.orders.dtos.ProventoDTO;
import investiments.orders.filtros.FiltroProvento;
import investiments.orders.service.ProventoService;
import investiments.orders.web.form.ProventoForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provento")
@AllArgsConstructor
public class ProventoController {

    private ProventoService service;

    @PostMapping
    public ResponseEntity<ProventoDTO> salvaProvento(@RequestBody ProventoForm form) {
        return ResponseEntity.ok(service.salvaProvento(form));
    }

    @GetMapping("/filtra")
    public ResponseEntity<List<ProventoDTO>> filtra(FiltroProvento filtro, Pageable pageable) {
        return ResponseEntity.ok(service.filtraProventos(filtro, pageable));
    }

}
