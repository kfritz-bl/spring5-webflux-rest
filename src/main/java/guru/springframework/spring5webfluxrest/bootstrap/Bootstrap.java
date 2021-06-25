package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * The type Bootstrap.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepo;
    private final VendorRepository vendorRepo;

    /**
     * Instantiates a new Bootstrap.
     *
     * @param categoryRepo the category repository
     * @param vendorRepo   the vendor repository
     */
    public Bootstrap(CategoryRepository categoryRepo, VendorRepository vendorRepo) {
        this.categoryRepo = categoryRepo;
        this.vendorRepo = vendorRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepo.count().block() == 0) {
            //load data
            System.out.println("#### LOADING DATA ON BOOTSTRAP #####");

            categoryRepo.save(Category.builder()
                    .description("Fruits").build()).block();

            categoryRepo.save(Category.builder()
                    .description("Nuts").build()).block();

            categoryRepo.save(Category.builder()
                    .description("Breads").build()).block();

            categoryRepo.save(Category.builder()
                    .description("Meats").build()).block();

            categoryRepo.save(Category.builder()
                    .description("Eggs").build()).block();

            System.out.println("Loaded Categories: " + categoryRepo.count().block());

            vendorRepo.save(Vendor.builder()
                    .firstName("Joe")
                    .lastName("Buck").build()).block();

            vendorRepo.save(Vendor.builder()
                    .firstName("Micheal")
                    .lastName("Weston").build()).block();

            vendorRepo.save(Vendor.builder()
                    .firstName("Jessie")
                    .lastName("Waters").build()).block();

            vendorRepo.save(Vendor.builder()
                    .firstName("Bill")
                    .lastName("Nershi").build()).block();

            vendorRepo.save(Vendor.builder()
                    .firstName("Jimmy")
                    .lastName("Buffett").build()).block();

            System.out.println("Loaded Vendors: " + vendorRepo.count().block());
        }
    }
}
