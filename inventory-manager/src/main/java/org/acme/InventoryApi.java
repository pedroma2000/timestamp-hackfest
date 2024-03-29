package org.acme;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("product")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class InventoryApi {

    private static final Logger LOGGER = Logger.getLogger(InventoryApi.class);

    @Inject
    EntityManager entityManager;

    @GET
    public List<Product> getAllProducts() {
        return entityManager.createNamedQuery("Products.findAll", Product.class).getResultList();
    }

    @POST
    @Transactional
    public Response createProduct(Product product) {
        if (product.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        entityManager.persist(product);
        return Response.ok(product).status(201).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
