package onbording.prueba.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import onbording.prueba.dto.ProductDto;
import onbording.prueba.entity.Product;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService {

    public List<Product> ListProduct(){
        return Product.listAll();
    }

    @Transactional
    public Product save(ProductDto dto){

        Product product = new Product();
        product.setNombre(dto.getNombre());
        product.setStock(dto.getStock());
        product.setPrecio(dto.getPrecio());
        product.persist();

        return product;

    }

    @Transactional
    public void updateProduct(Long id, ProductDto dto){
        Product product = new Product();

        Optional<Product> optionalProduct = Product.findByIdOptional(id);
        if(optionalProduct.isEmpty()){
            throw  new WebApplicationException("Producto no encontrado", 404);
        }

        product = optionalProduct.get();

        product.setNombre(dto.getNombre());
        product.setStock(dto.getStock());
        product.setPrecio(dto.getPrecio());

        product.persist();
    }


    @Transactional
    public void removeProduct(long id){
        Optional<Product> optionalProduct = Product.findByIdOptional(id);
        if(optionalProduct.isEmpty()){
            throw  new WebApplicationException("Producto no encontrado",404);
        }

        Product product = optionalProduct.get();
        product.delete();
    }
}
