INSERT INTO `airport` (name, country, state, city, iata, icao) VALUES
('Hartsfield–Jackson Atlanta International Airport', 'United States', 'Georgia', 'Atlanta', 'ATL', 'KATL'),
('Dallas Fort Worth International Airport', 'United States', 'Texas', 'Dallas-Fort Worth', 'DFW', 'KDFW'),
('General Mariano Escobedo International', 'Mexico', 'Nuevo Leon', 'Monterrey', 'MTY', 'MMMY'),
('Aeropuerto Internacional Plan de Guadalupe', 'Mexico', 'Coahuila', 'Ramos Arizpe', 'SLW', 'MMIO'),
('Aeropuerto Alto Río Senguer', 'Argentina', 'Chubut', 'Alto Río Senguer', 'ARR', 'SAVR');
INSERT INTO `arrival_flight` (id_airport, arrival_time, status) VALUES
(3, {ts '2022-10-09 16:30:00'}, 1),
(3, {ts '2022-11-09 16:30:00'}, 1),
(4, {ts '2022-11-09 16:30:00'}, 1);
INSERT INTO `departure` (id_airport, departure_time, status) VALUES
(1, {ts '2022-10-09 20:10:00'}, 1),
(2, {ts '2022-11-09 20:10:00'}, 1),
(5, {ts '2022-11-09 20:10:00'}, 1);
INSERT INTO `passenger` (last_names, first_name, birth_date, email, telephone_number, country, state, city, passport_number, expiration_date, nationality, status) VALUES
('Ramirez Flores', 'Jose', DATE '1970-05-08', 'joseramirez1@random.names', '5550657078', 'Mexico', 'Coahuila', 'Torreon', 6549647689, DATE '2024-11-28', 'MEX', 1),
('Hernandez Sanchez', 'Maria', DATE '1999-09-25', 'mariahernandez1@random.names', '5546460968', 'Mexico', 'Nuevo Leon', 'Monterrey', 3453476534, DATE '2026-03-12', 'MEX', 1);
INSERT INTO `travel` (id_arrival_flight, id_departure, price, status) VALUES
(1, 1, 10000.00, 1),
(2, 2, 10000.00, 1),
(3, 3, 20000.00, 1);
INSERT INTO `ticket` (id_passenger, id_travel, boarding_time) VALUES
(1, 1, {ts '2022-10-09 16:00:00'}),
(1, 2, {ts '2022-11-19 11:30:00'}),
(2, 3, {ts '2022-11-19 11:30:00'});
INSERT INTO `passengers_travels` (id_passenger, id_travel) VALUES
(1, 1),
(1, 2),
(2, 3);
