
INSERT INTO product (name, description, price, category, active) VALUES
('Majica bela', 'Klasična pamučna bela majica sa kratkim rukavima.', 1299.00, 'ODECA', TRUE),
('Majica crna', 'Crna majica od 100% pamuka, uniseks model.', 1399.00, 'ODECA', TRUE),
('Farmerke plave', 'Slim fit plave farmerke sa elastinom.', 4599.00, 'ODECA', TRUE),
('Jakna zimska', 'Topla zimska jakna sa kapuljačom i postavom.', 10999.00, 'ODECA', TRUE),
('Trenerka komplet', 'Komplet trenerka (donji deo + duks) za sportske aktivnosti.', 4999.00, 'ODECA', TRUE),
('Kožna jakna', 'Muška kožna jakna klasičnog kroja.', 13999.00, 'ODECA', TRUE),
('Haljina crvena', 'Elegantna crvena haljina midi dužine.', 6999.00, 'ODECA', TRUE),
('Čarape set (5 kom)', 'Pamučne čarape u setu od pet pari.', 599.00, 'ODECA', TRUE),
('Šorts sportski', 'Sportski šorts za trening i rekreaciju.', 1999.00, 'ODECA', TRUE),
('Košulja bela', 'Muška bela košulja dugih rukava.', 3499.00, 'ODECA', TRUE),

('Patike crne', 'Crne patike za svakodnevnu upotrebu.', 5499.99, 'OBUCA', TRUE),
('Patike bele', 'Bele patike sa gumom i mrežastim materijalom.', 5799.00, 'OBUCA', TRUE),
('Sandale', 'Ženske letnje sandale sa kaišićima.', 3499.00, 'OBUCA', TRUE),
('Čizme kožne', 'Kožne zimske čizme otporne na vodu.', 9999.00, 'OBUCA', TRUE),
('Papuče gumene', 'Uniseks gumene papuče za plažu i bazen.', 1299.00, 'OBUCA', TRUE),

('Kačket plavi', 'Plavi kačket sa podešavanjem veličine.', 899.50, 'DODACI', TRUE),
('Rukavice zimske', 'Pamučne rukavice za hladne dane.', 799.00, 'DODACI', TRUE),
('Kaiš kožni', 'Crni kožni kaiš sa metalnom kopčom.', 2499.00, 'DODACI', TRUE),
('Torba sportska', 'Sportska torba sa više pregrada.', 2999.00, 'DODACI', TRUE),
('Ranac gradski', 'Ranac za svakodnevnu upotrebu, zapremina 25L.', 4499.00, 'DODACI', TRUE),
('Naočare za sunce', 'Uniseks sunčane naočare sa UV zaštitom.', 1599.00, 'DODACI', TRUE),
('Sat digitalni', 'Digitalni sportski sat sa štopericom.', 3999.00, 'DODACI', TRUE),

('Telefon X100', 'Pametni telefon sa 6.5" ekranom i 128GB memorije.', 34999.00, 'TEHNIKA', TRUE),
('Laptop ProBook', 'Laptop sa Intel i5 procesorom i 16GB RAM-a.', 84999.00, 'TEHNIKA', TRUE),
('Bežične slušalice', 'Bluetooth slušalice sa dugim trajanjem baterije.', 5999.00, 'TEHNIKA', TRUE),
('Smartwatch Fit', 'Pametni sat sa merenjem otkucaja srca i koraka.', 12999.00, 'TEHNIKA', TRUE),
('Tablet 10"', 'Tablet uređaj sa 10-inčnim ekranom i 64GB memorije.', 27999.00, 'TEHNIKA', TRUE),
('Punjač brzi 30W', 'USB-C brzi punjač od 30W.', 1999.00, 'TEHNIKA', TRUE),
('Bežični miš', 'Optički bežični miš sa USB prijemnikom.', 1499.00, 'TEHNIKA', TRUE),
('Tastatura mehanička', 'Mehanička tastatura sa RGB osvetljenjem.', 7999.00, 'TEHNIKA', TRUE),
('Monitor 24"', 'Full HD monitor od 24 inča.', 19999.00, 'TEHNIKA', TRUE),
('Zvucnik Bluetooth', 'Prijenosni Bluetooth zvučnik sa bas pojačanjem.', 4999.00, 'TEHNIKA', TRUE);
ON CONFLICT DO NOTHING;

INSERT INTO app_user (id, email, full_name, password) VALUES
(1, 'admin1@example.com', 'Admin One',  'admin1'), -- admin123
(2, 'admin2@example.com', 'Admin Two',  'admin2'), -- admin123
(3, 'user1@example.com',  'User One',   'user1'), -- user123
(4, 'user2@example.com',  'User Two',   'user2'), -- user123
(5, 'user3@example.com',  'User Three', 'user3'); -- user123
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role) VALUES
(1, 'ADMIN'),
(2, 'ADMIN'),
(3, 'USER'),
(4, 'USER'),
(5, 'USER');
ON CONFLICT DO NOTHING;