package guru.springframework.spring5webfluxrest.controllers;


import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * The type Vendor controller.
 */
@RestController
public class VendorController {

    private final VendorRepository vendorRepo;

    /**
     * Instantiates a new Vendor controller.
     *
     * @param vendorRepo the vendor repo
     */
    public VendorController(VendorRepository vendorRepo) {
        this.vendorRepo = vendorRepo;
    }

    /**
     * List flux.
     *
     * @return the flux
     */
    @GetMapping("/api/v1/vendors")
    Flux<Vendor> list() {
        return vendorRepo.findAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("api/v1/vendors/{id}")
    Mono<Vendor> getById(@PathVariable String id) {
        return vendorRepo.findById(id);
    }

    /**
     * Create mono.
     *
     * @param vendorStream the vendor stream
     * @return the mono
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/vendors")
    Mono<Void> create(@RequestBody Publisher<Vendor> vendorStream) {
        return vendorRepo.saveAll(vendorStream).then();
    }

    /**
     * Update mono.
     *
     * @param id     the id
     * @param vendor the vendor
     * @return the mono
     */
    @PutMapping("api/v1/vendors/{id}")
    Mono<Vendor> update(@PathVariable String id, @RequestBody Vendor vendor) {
        vendor.setId(id);
        return vendorRepo.save(vendor);
    }

    /**
     * Patch mono.
     *
     * @param id     the id
     * @param vendor the vendor
     * @return the mono
     */
    @PatchMapping("api/v1/vendors/{id}")
    Mono<Vendor> patch(@PathVariable String id, @RequestBody Vendor vendor) {
        Vendor foundV = vendorRepo.findById(id).block();

        if (!foundV.getFirstName().equals(vendor.getFirstName())) {
            foundV.setFirstName(vendor.getFirstName());

            return vendorRepo.save(foundV);
        }
        return Mono.just(foundV);
    }
}
