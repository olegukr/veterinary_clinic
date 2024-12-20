
INSERT INTO users (id, username, password, usertype, phone) VALUES 
(default, 'john_doe', 'password123', 'ADMIN', '1234567890'),
(default, 'jane_smith', 'securepass', 'USER', '0987654321'),
(default, 'mike_jones', 'mike123', 'USER', '1122334455'),
(default, 'susan_white', 'whitepassword', 'ADMIN', '5566778899'),
(default, 'emma_black', 'emmablackpass', 'USER', '6677889900');

INSERT INTO patients (id, user_id, name, age, breed, gender, image_url) VALUES
(default, 1, 'Baxic', 5, 'Golden Retriever', 'M', 'https://example.com/images/baxic.jpg'),
(default, 2, 'Tequila', 3, 'Chihuahua', 'W', 'https://example.com/images/tequila.jpg'),
(default, 3, 'Blanchet', 8, 'Labrador', 'W', 'https://example.com/images/blanchet.jpg'),
(default, 4, 'Daisy', 3, 'Bulldog', 'W', 'https://example.com/images/daisy.jpg'),
(default, 5, 'Max', 2, 'Poodle', 'M', 'https://example.com/images/max.jpg');

INSERT INTO appointments (id, appointment_date, consultation, reason, status, patient_id) VALUES
(default, '2024-12-03', 'STANDARD', 'Routine health check-up', 'SCHEDULED', 1),
(default, '2024-12-05', 'EMERGENCY', 'High fever and chest pain', 'COMPLETED', 2),
(default, '2024-12-07', 'FOLLOW_UP', 'Follow-up for blood test results', 'SCHEDULED', 3),
(default, '2024-12-10', 'TELECONSULTATION', 'Remote consultation for flu symptoms', 'CANCELLED', 4),
(default, '2024-12-15', 'HOME_VISIT', 'Check-up for bedridden patient', 'SCHEDULED', 5);

INSERT INTO treatments (id, treatment_type, description, treatment_date, patient_id) VALUES
(default, 'Vaccination', 'Annual rabies vaccination', '2024-12-01', 1),  
(default, 'Surgery', 'Spaying procedure', '2024-12-05', 2), 
(default, 'Check-up', 'Routine health check-up', '2024-12-07', 3),  
(default, 'Emergency', 'Treatment for chest pain', '2024-12-10', 4), 
(default, 'Follow-up', 'Follow-up after vaccination', '2024-12-15', 5); 
