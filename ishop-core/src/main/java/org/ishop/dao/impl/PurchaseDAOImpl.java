package org.ishop.dao.impl;

import org.ishop.dao.PurchaseDAO;
import org.ishop.domain.Purchase;
import org.springframework.stereotype.Service;

/**
 * {@link PurchaseDAO} implementation
 *
 * @author Alexander Burchak
 */
@Service("purchaseDAO")
public class PurchaseDAOImpl extends AbstractDAO<Purchase> implements PurchaseDAO {
    public PurchaseDAOImpl() {
        super(Purchase.class);
    }
}