package org.ishop.service;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.ishop.AbstractPersistenceTest;
import org.ishop.support.OpenSessionInViewTemplate;
import org.ishop.domain.Product;
import org.ishop.domain.Purchase;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Test for {@link ProductService}
 *
 * @author Alexander Burchak
 */
public class ProductServiceTest extends AbstractPersistenceTest {
    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeMethod(groups = "database")
    public void createProduct() {
        product = new Product();
        product.setCode("ABCDEF");
    }

    @Test(groups = "database")
    public void testSave() {
        productService.save(product);

        Assert.assertNotNull(product.getId());
    }

    @Test(groups = "database")
    public void testGet() {
        productService.save(product);

        Product copy = productService.getProduct(product.getId());

        Assert.assertNotNull(copy);
        Assert.assertEquals(copy.getId(), product.getId());
        Assert.assertEquals(copy.getCode(), product.getCode());

        copy = productService.getProduct(product.getId() + 1);

        Assert.assertNull(copy);
    }

    @Test(groups = "database", expectedExceptions = LazyInitializationException.class)
    public void testLazyLoading() {
        productService.save(product);

        Product copy = productService.getProduct(product.getId());

        Assert.assertNotNull(copy);
        // test will fail on this line while trying to initialize collection
        Assert.assertEquals(copy.getPurchases().size(), 0);
    }

    @Test(groups = "database")
    public void testPurchase() {
        OpenSessionInViewTemplate.invoke(new Runnable() {
            public void run() {
                productService.save(product);

                Product copy = productService.getProduct(product.getId());

                Assert.assertNotNull(copy);
                Assert.assertEquals(copy.getPurchases().size(), 0);

                Date paymentDate = new Date();
                Purchase purchase = productService.purchase(copy.getId(), paymentDate);

                copy = productService.getProduct(product.getId());

                Assert.assertNotNull(purchase);
                Assert.assertEquals(purchase.getPaymentDate(), paymentDate);
                Assert.assertNotNull(copy);
                Assert.assertSame(purchase.getProduct(), copy);
                Assert.assertEquals(copy.getPurchases().size(), 1);
                Assert.assertSame(copy.getPurchases().iterator().next(), purchase);
            }
        });
    }

    @Test(groups = "database", expectedExceptions = IllegalArgumentException.class)
    public void testRollback() {
        productService.save(product);

        Product copy = productService.getProduct(product.getId());
        Assert.assertNotNull(copy);

        try {
            productService.purchase(copy.getId(), new Date(0));
        } finally {
            OpenSessionInViewTemplate.invoke(new Runnable() {
                public void run() {
                    Product copy = productService.getProduct(product.getId());

                    Assert.assertNotNull(copy);
                    Assert.assertEquals(copy.getPurchases().size(), 0);
                }
            });
        }
    }
}
