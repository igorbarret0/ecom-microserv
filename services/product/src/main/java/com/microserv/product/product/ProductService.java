package com.microserv.product.product;

import com.microserv.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Long createProduct(ProductRequest request) {

        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchase(List<ProductPurchaseRequest> request) {


        var productsId = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productsId);

        if (productsId.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more product does not exists");
        }

        var storedRequests = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i=0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequests = storedRequests.get(i);

            if (product.getAvailableQuantity() < productRequests.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID: " + productRequests.productId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequests.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequests.quantity()));

        }

        return purchasedProducts;

    }

    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the provided id: " + id));
    }

    public List<ProductResponse> findAll() {

        return productRepository.findAll()
                .stream().map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
