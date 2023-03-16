package org.acme;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.oracle.svm.core.annotate.Inject;

@ApplicationScoped
public class InventoryService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void createProduct(String title, Float cost) {
        Product product = new Product();
        product.title = title;
        product.cost = cost;
        entityManager.persist(product);
    }

    public List<Product> getProductList() {
        return entityManager.createNamedQuery("Products.findAll", Product.class).getResultList();
    }
}
