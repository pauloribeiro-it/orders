package investiments.orders.web;

import investiments.orders.dtos.AtivoDTO;
import investiments.orders.entities.Ativo;
import investiments.orders.service.AtivoService;
import investiments.orders.web.form.AtivoForm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ativo")
@RestController
@AllArgsConstructor
public class AtivoController {
    private final AtivoService ativoService;

    @PostMapping
    public ResponseEntity<AtivoDTO> criaAtivo(@RequestBody AtivoForm ativoForm){
        AtivoDTO ativo = this.ativoService.salvaAtivo(ativoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(ativo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ativo> recuperaAtivo(@PathVariable Integer id){
        return ResponseEntity.ok(this.ativoService.recuperaAtivoPorId(id));
    }


}
