-- Örnek Müşteriler

-- Şifre: ali123
INSERT INTO customers (id, name, username, email, password, phone, balance)
VALUES (1, 'Ali Veli','aliveli', 'ali@example.com', '$2a$10$xrG1FmbRemWNRLOvVDDSTO5G8xnoYe0f1syaPJPyX0bdHEP01lMha', '05001234567', 10000.00);

INSERT INTO customers (id, name, username, email, password, phone, balance)
VALUES (2, 'Ayşe Yılmaz', 'ayse123','ayse@example.com', '$2a$10$xrG1FmbRemWNRLOvVDDSTO5G8xnoYe0f1syaPJPyX0bdHEP01lMha', '05007654321', 5000.00);

-- Örnek Varlıklar
INSERT INTO assets (id, customer_id, asset_name, total_size, usable_size)
VALUES (1, 1, 'GARAN', 100.000000, 80.000000);

INSERT INTO assets (id, customer_id, asset_name, total_size, usable_size)
VALUES (2, 2, 'AKBNK', 200.000000, 200.000000);

-- Örnek İşlemler
INSERT INTO transactions (id, customer_id, type, amount, iban)
VALUES (1, 1, 'DEPOSIT', 10000.00, 'TR000000000000000000000001');

INSERT INTO transactions (id, customer_id, type, amount, iban)
VALUES (2, 2, 'DEPOSIT', 5000.00, 'TR000000000000000000000002');

-- Örnek Emirler
INSERT INTO orders (id, customer_id, asset_name, order_side, price, size, status)
VALUES (1, 1, 'GARAN', 'BUY', 45.50, 10.00, 'PENDING');

INSERT INTO orders (id, customer_id, asset_name, order_side, price, size, status)
VALUES (2, 2, 'AKBNK', 'SELL', 15.20, 20.00, 'PENDING');
