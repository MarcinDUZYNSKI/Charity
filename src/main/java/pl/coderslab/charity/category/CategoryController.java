package pl.coderslab.charity.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity createOne(@Valid @RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.created(URI.create("/api/category/" + category.getId())).build();
    }

    @GetMapping("/{id")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(optionalCategory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(this::delete)
                .map(i -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Category delete(Category category) {
        categoryRepository.delete(category);
        return category;
    }
    @PutMapping("/{id}")
    public void updateOne(@RequestBody Category category, @PathVariable Long id){

    }
    @PatchMapping("/{id}")
    public void updatePartially(@PathVariable Long id, @RequestBody Map<String, String> change){

    }

}
