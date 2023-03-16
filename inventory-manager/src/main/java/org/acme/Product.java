package org.acme;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@NamedQuery(name = "Products.findAll", query = "SELECT p FROM Product p ORDER BY p.title", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Product {

    @Id
    @GeneratedValue
    public Integer id;

    @Column
    public String title;

    @Column
    public float cost;

}
