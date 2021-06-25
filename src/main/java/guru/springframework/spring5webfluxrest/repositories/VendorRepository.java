package guru.springframework.spring5webfluxrest.repositories;

import guru.springframework.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


/**
 * The interface Vendor repository.
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
