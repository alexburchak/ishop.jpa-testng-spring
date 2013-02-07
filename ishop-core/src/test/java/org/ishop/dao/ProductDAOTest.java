package org.ishop.dao;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.ishop.AbstractPersistenceTest;
import org.ishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test for {ProductDAO}
 *
 * @author Alexander Burchak
 */
public class ProductDAOTest extends AbstractPersistenceTest {
    @Autowired
    private ProductDAO productDAO;

    private Product product;

    @BeforeMethod(groups = "database")
    public void createProduct() {
        product = new Product();
        product.setCode("ABCDEF");
    }

    @Test(groups = "database")
    public void testSave() {
        // can not test since transaction is not in progress
    }

    @Test(groups = "database")
    public void testGet() {
        // can not test since transaction is not in progress
    }
}
