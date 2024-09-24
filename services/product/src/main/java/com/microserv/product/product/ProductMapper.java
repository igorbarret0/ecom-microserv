package com.microserv.product.product;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest request) {

        var product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setAvailableQuantity(request.availableQuantity());
        product.setPrice(request.price());
        product.setCategory(request.category());

        return product;
    }

    public ProductResponse toProductResponse(Product product) {

        ProductResponse productResponse = new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );

        return productResponse;
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {

        ProductPurchaseResponse productPurchaseResponse = new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );

        return productPurchaseResponse;
    }

}
