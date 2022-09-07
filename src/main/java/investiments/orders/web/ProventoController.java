package investiments.orders.web;

import investiments.orders.dtos.ProventoDTO;
import investiments.orders.service.ProventoService;
import investiments.orders.web.form.ProventoForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provento")
@AllArgsConstructor
public class ProventoController {

    private ProventoService service;

    @PostMapping
    public ResponseEntity<ProventoDTO> salvaProvento(@RequestBody ProventoForm form) {
        return ResponseEntity.ok(service.salvaProvento(form));
    }
}
