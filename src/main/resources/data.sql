INSERT INTO customers (id, name, username, email, password, phone, balance)
VALUES (1, 'Ali Veli','aliveli', 'ali@example.com', '$2a$10$xrG1FmbRemWNRLOvVDDSTO5G8xnoYe0f1syaPJPyX0bdHEP01lMha', '05001234567', 10000.00);

INSERT INTO customers (id, name, username, email, password, phone, balance)
VALUES (2, 'Ayşe Yılmaz', 'ayse123','ayse@example.com', '$2a$10$xrG1FmbRemWNRLOvVDDSTO5G8xnoYe0f1syaPJPyX0bdHEP01lMha', '05007654321', 5000.00);

INSERT INTO assets (asset_name, total_size, usable_size, price)
VALUES
  ('AAPL', 10000, 850000, 175.30),
  ('TSLA', 500000, 450000, 192.70),
  ('GOOGL', 300000, 290000, 134.55),
  ('BTC', 21000000, 19000000, 27000.00),
  ('ETH', 120000000, 115000000, 1800.00),
  ('BIST100', 100000, 95000, 2500.00),
  ('XAU', 10000, 9000, 1960.50),
  ('NVDIA', 100000000, 95000000, 1.00);


INSERT INTO customer_assets (customer_id, asset_id, total_size, usable_size, last_updated)
VALUES
  (1, (SELECT id FROM assets WHERE asset_name = 'AAPL'), 150.00, 150.00, CURRENT_TIMESTAMP),
  (1, (SELECT id FROM assets WHERE asset_name = 'BTC'), 2500, 1000, CURRENT_TIMESTAMP),
  (1, (SELECT id FROM assets WHERE asset_name = 'NVDIA'), 10000, 10000, CURRENT_TIMESTAMP);

INSERT INTO customer_assets (customer_id, asset_id, total_size, usable_size, last_updated)
VALUES
  (2, (SELECT id FROM assets WHERE asset_name = 'ETH'), 50000, 50.000000, CURRENT_TIMESTAMP),
  (2, (SELECT id FROM assets WHERE asset_name = 'TSLA'), 1000, 750, CURRENT_TIMESTAMP),
  (2, (SELECT id FROM assets WHERE asset_name = 'NVDIA'), 500, 500, CURRENT_TIMESTAMP);


INSERT INTO transactions (id, customer_id, type, amount, iban)
VALUES (2, 2, 'DEPOSIT', 5000.00, 'TR000000000000000000000002');

INSERT INTO orders (id, customer_id, asset_name, order_side, price, size, status)
VALUES (1, 1, 'GARAN', 'BUY', 45.50, 10.00, 'PENDING');

INSERT INTO orders (id, customer_id, asset_name, order_side, price, size, status)
VALUES (2, 2, 'AKBNK', 'SELL', 15.20, 20.00, 'PENDING');
