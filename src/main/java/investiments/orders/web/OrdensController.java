package investiments.orders.web;

import investiments.orders.web.form.OrdemForm;
import investiments.orders.entities.Ordem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordens")
public class OrdensController {

    @PostMapping
    public ResponseEntity<Ordem> novo(@RequestBody OrdemForm form){
        return null;
    }
}
