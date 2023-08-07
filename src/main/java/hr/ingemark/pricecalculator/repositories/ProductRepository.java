package hr.ingemark.pricecalculator.repositories;

import hr.ingemark.pricecalculator.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    long deleteProductById(Long id);
}
