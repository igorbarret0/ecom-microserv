INSERT INTO tb_categories (name, description)
VALUES
('Electronics', 'Electronic devices and gadgets'),
('Books', 'Various genres of books'),
('Clothing', 'Apparel and accessories'),
('Furniture', 'Home and office furniture'),
('Sports', 'Sporting goods and equipment');

INSERT INTO tb_products (name, description, available_quantity, price, category_id)
VALUES
('Smartphone', 'Latest model smartphone with advanced features', 50, 999.99, 1),
('Novel', 'Bestselling fiction novel', 120, 19.99, 2),
('T-shirt', 'Cotton t-shirt in various sizes', 200, 9.99, 3),
('Office Chair', 'Ergonomic office chair', 30, 149.99, 4),
('Soccer Ball', 'Professional quality soccer ball', 80, 29.99, 5);
