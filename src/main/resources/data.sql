INSERT INTO appointments (id, appointment_date, consultation, reason, status) VALUES (default, '2024-12-03', 'STANDARD', 'Routine health check-up', 'SCHEDULED');
INSERT INTO appointments (id, appointment_date, consultation, reason, status) VALUES (default, '2024-12-05', 'EMERGENCY', 'High fever and chest pain', 'COMPLETED');
INSERT INTO appointments (id, appointment_date, consultation, reason, status) VALUES (default, '2024-12-07', 'FOLLOW_UP', 'Follow-up for blood test results', 'SCHEDULED');
INSERT INTO appointments (id, appointment_date, consultation, reason, status) VALUES (default, '2024-12-10', 'TELECONSULTATION', 'Remote consultation for flu symptoms', 'CANCELLED');
INSERT INTO appointments (id, appointment_date, consultation, reason, status) VALUES (default, '2024-12-15', 'HOME_VISIT', 'Check-up for bedridden patient', 'SCHEDULED');

INSERT INTO users (id, username, password, usertype, phone) VALUES 
(default, 'john_doe', 'password123', 'ADMIN', '1234567890'),
(default, 'jane_smith', 'securepass', 'USER', '0987654321'),
(default, 'mike_jones', 'mike123', 'USER', '1122334455'),
(default, 'susan_white', 'whitepassword', 'ADMIN', '5566778899'),
(default, 'emma_black', 'emmablackpass', 'USER', '6677889900');