package org.ishop.service;

import org.ishop.domain.Product;
import org.ishop.domain.Purchase;

import java.util.Date;

/**
 * Vouche rservice
 *
 * @author Alexander Burchak
 */
public interface ProductService {
    /**
     * Save product
     *
     * @param product product
     */
    void save(Product product);

    /**
     * Find product
     *
     * @param id product id
     * @return product or null
     */
    Product getProduct(Long id);

    /**
     * Make purchase
     *
     * @param productId product id
     * @param paymentDate payment date
     * @return purchase
     */
    Purchase purchase(Long productId, Date paymentDate);
}
