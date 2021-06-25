package guru.springframework.spring5webfluxrest.repositories;

import guru.springframework.spring5webfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


/**
 * The interface Category repository.
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
