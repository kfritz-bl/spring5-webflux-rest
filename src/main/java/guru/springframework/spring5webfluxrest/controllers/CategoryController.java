package guru.springframework.spring5webfluxrest.controllers;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * The type Category controller.
 */
@RestController
public class CategoryController {

    private final CategoryRepository categoryRepo;

    /**
     * Instantiates a new Category controller.
     *
     * @param categoryRepo the category repository
     */
    public CategoryController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    /**
     * List flux.
     *
     * @return the flux
     */
    @GetMapping("/api/v1/categories")
    Flux<Category> list() {
        return categoryRepo.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/api/v1/categories/{id}")
    Mono<Category> getById(@PathVariable String id) {
        return categoryRepo.findById(id);
    }

    /**
     * Create mono.
     *
     * @param categoryStream the category stream
     * @return the mono
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/categories")
    Mono<Void> create(@RequestBody Publisher<Category> categoryStream) {
        return categoryRepo.saveAll(categoryStream).then();
    }

    /**
     * Update mono.
     *
     * @param id       the id
     * @param category the category
     * @return the mono
     */
    @PutMapping("/api/v1/categories/{id}")
    Mono<Category> update(@PathVariable String id, @RequestBody Category category) {
        category.setId(id);
        return categoryRepo.save(category);
    }

    /**
     * Patch mono.
     *
     * @param id       the id
     * @param category the category
     * @return the mono
     */
    @PatchMapping("/api/v1/categories/{id}")
    Mono<Category> patch(@PathVariable String id, @RequestBody Category category) {

        Category foundC = categoryRepo.findById(id).block();

        if (foundC.getDescription() != category.getDescription()) {
            foundC.setDescription(category.getDescription());
            return categoryRepo.save(foundC);
        }

        return Mono.just(foundC);
    }
}
