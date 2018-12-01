DELETE FROM users;

--- Password is encrypted form of password
INSERT INTO users (id, email, password) VALUES (1, 'user@report.com', '$2a$10$e5gfGVzxUQnYebHcFoDwgO0PCPZ2xrVqfjxpDJ6gpBUo3pvPsqtxC');
