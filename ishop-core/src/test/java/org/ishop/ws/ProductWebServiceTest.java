package org.ishop.ws;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.ishop.AbstractPersistenceTest;
import org.ishop.service.ProductService;
import org.ishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.NamingException;

/**
 * Test for {@link ProductWebService}
 *
 * @author Alexander Burchak
 */
public class ProductWebServiceTest extends AbstractPersistenceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductWebService productWebService;

    private Product product;

    @BeforeMethod(groups = "database")
    public void createProduct() {
        product = new Product();
        product.setCode("ABCDEF");
    }

    @Test(groups = "database")
    public void testInterceptor() throws NamingException {
        productService.save(product);

        ProductBean copy = productWebService.getProduct(product.getId());

        Assert.assertNotNull(copy);
        Assert.assertEquals(copy.getId(), (long) product.getId());
        Assert.assertEquals(copy.getCode(), product.getCode());
        Assert.assertNull(copy.getLastPaid());
    }
}
