package investiments.orders.web;

import investiments.orders.dtos.OrdemDTO;
import investiments.orders.service.OrdemService;
import investiments.orders.web.form.OrdemForm;
import investiments.orders.entities.Ordem;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
@AllArgsConstructor
public class OrdensController {

    private final OrdemService ordemService;

    @PostMapping
    public ResponseEntity<OrdemDTO> novo(@RequestBody OrdemForm form){
        return ResponseEntity.ok(ordemService.salvaOrdem(form));
    }

    @PostMapping("/many")
    public ResponseEntity<List<OrdemDTO>> cadastraOrdens(@RequestBody List<OrdemForm> form){
        return ResponseEntity.ok(ordemService.salvaOrdens(form));
    }



}
