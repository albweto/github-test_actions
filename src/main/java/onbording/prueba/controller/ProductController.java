package onbording.prueba.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import onbording.prueba.dto.ProductDto;
import onbording.prueba.entity.Product;
import onbording.prueba.service.ProductService;

import java.awt.*;
import java.util.List;

@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;


    @GET
    public Response listAllProducts() {
        List<Product> products = productService.ListProduct();
        return Response.ok(products).build();
    }

    @POST
    public Response saveProduct(ProductDto dto) {
        Product product = productService.save(dto);
        return Response.ok(product).status(201).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProduct(@PathParam("id") Long id, ProductDto dto) {
        productService.updateProduct(id, dto);
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    public Response rewmoveProduct(@PathParam("id") Long id) {
        productService.removeProduct(id);

        return Response.status(204).build();

    }
}
