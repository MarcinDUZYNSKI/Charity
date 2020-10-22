package pl.coderslab.charity.institution;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController // @Controller + @ResponseBody na wszystkich metodach
@RequestMapping("/api/institutions") // ścieżki odwłują się do nazwanych zasobów przetwarzanych przz apliakcję
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // standardowy (domyślny) kod odpowiedzi HTTP
    public List<Institution> getAll() {
        return institutionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity createOne(@Valid @RequestBody Institution institution) {
        institutionRepository.save(institution);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/institutions/"
                + institution.getId())).build();
//        return ResponseEntity.created(URI.create("/api/institutions/" + institution.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(id);
        if (optionalInstitution.isPresent()) {
            return ResponseEntity.ok(optionalInstitution.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    // Status 200 OK + stan usuniętego zasobu w treści odpowiedzi
    // Status 204 No Content i bez stanu zasobu w treści odpowiedzi
    public ResponseEntity deleteOne(@PathVariable Long id) {
        return institutionRepository.findById(id)
                .map(this::delete)
                .map(i -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Institution delete(Institution institution) {
        institutionRepository.delete(institution);
        return institution;
    }

    @PutMapping("/{id}")
    // 200 OK lub 404 Not Found
    public void updateOne(@RequestBody Institution institution, @PathVariable Long id) {

    }

    @PatchMapping("/{id}")
    // 200 OK lub 404 Not Found
    public void updatePartially(@PathVariable Long id, @RequestBody Map<String, Object> changes) {
        ;
    }

}
