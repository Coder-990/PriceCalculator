package hr.ingemark.pricecalculator.repositories;

import hr.ingemark.pricecalculator.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    long deleteProductById(final Long id);

    Optional<Product> findByCode(final String code);
    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.code = :#{#current.code} " +
            "AND  p.id <> :#{#current.id}")
    List<Product> existsAllByCodeAndIdIsNotLike(@Param("current") Product current);
}
