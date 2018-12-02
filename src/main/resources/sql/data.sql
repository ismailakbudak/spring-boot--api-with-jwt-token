DELETE FROM merchants;

--- Password is encrypted form of password
INSERT INTO merchants (id, email, password, name) VALUES (1, 'merchant@dev.com', '$2a$10$e5gfGVzxUQnYebHcFoDwgO0PCPZ2xrVqfjxpDJ6gpBUo3pvPsqtxC', 'Dev - Merchant');
INSERT INTO merchants (id, email, password, name) VALUES (2, 'merchant@test.com', '$2a$10$e5gfGVzxUQnYebHcFoDwgO0PCPZ2xrVqfjxpDJ6gpBUo3pvPsqtxC', 'Test - Merchant');
INSERT INTO merchants (id, email, password, name) VALUES (3, 'merchant@prod.com', '$2a$10$e5gfGVzxUQnYebHcFoDwgO0PCPZ2xrVqfjxpDJ6gpBUo3pvPsqtxC', 'Prod - Merchant');
