package investiments.orders.web;

import investiments.orders.dtos.OrdemDTO;
import investiments.orders.entities.Ordem;
import investiments.orders.repositories.FiltroOrdem;
import investiments.orders.service.OrdemService;
import investiments.orders.web.form.OrdemForm;
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

    @GetMapping("/all")
    public ResponseEntity<List<OrdemDTO>> getAll(){
        return ResponseEntity.ok(ordemService.getAllOrdens());
    }

    @GetMapping("/filtra")
    public ResponseEntity<List<Ordem>> filtra(FiltroOrdem filtroOrdem){
        return ResponseEntity.ok(ordemService.filtraOrdem(filtroOrdem));
    }


}
