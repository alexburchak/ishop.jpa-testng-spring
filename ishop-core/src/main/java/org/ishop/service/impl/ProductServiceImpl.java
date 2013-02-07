package org.ishop.service.impl;

import org.ishop.service.ProductService;
import org.ishop.domain.Product;
import org.ishop.domain.Purchase;
import org.ishop.dao.ProductDAO;
import org.ishop.dao.PurchaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * {@link ProductService} implementation
 *
 * @author Alexander Burchak
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private PurchaseDAO purchaseDAO;

    @Transactional
    public void save(Product product) {
        productDAO.save(product);
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productDAO.get(id);
    }

    @Transactional
    public Purchase purchase(Long productId, Date paymentDate) {
        Product product = productDAO.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Invalid product id: " + productId);
        }

        Purchase purchase = new Purchase();
        purchase.setPaymentDate(paymentDate);
        purchase.setProduct(product);

        product.addPurchase(purchase);

        purchaseDAO.save(purchase);
        productDAO.save(product);

        try {
            if (paymentDate.before(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2000"))) {
                throw new IllegalArgumentException("Invalid date: " + paymentDate);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return purchase;
    }
}
