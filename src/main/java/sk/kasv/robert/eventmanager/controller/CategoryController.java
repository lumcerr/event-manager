package sk.kasv.robert.eventmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.robert.eventmanager.entity.Category;
import sk.kasv.robert.eventmanager.repository.CategoryRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create new category",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"name\": \"Concert\"\n}")
                    )
            )
    )
    public ResponseEntity<Category> createCategory(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        Category category = new Category();
        category.setName(name);
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\n  \"name\": \"Conference\"\n}")
                    )
            )
    )
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(body.get("name"));
            return ResponseEntity.ok(categoryRepository.save(category));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
