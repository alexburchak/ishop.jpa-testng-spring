package org.ishop.dao.impl;

import org.ishop.dao.ProductDAO;
import org.ishop.domain.Product;
import org.springframework.stereotype.Service;

/**
 * {@link ProductDAO} implementation
 *
 * @author Alexander Burchak
 */
@Service("productDAO")
public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {
    public ProductDAOImpl() {
        super(Product.class);
    }
}
