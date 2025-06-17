INSERT INTO app_user (id, name, email) VALUES (1, 'Alice', 'alice@example.com');
INSERT INTO app_user (id, name, email) VALUES (2, 'Bob', 'bob@example.com');
INSERT INTO app_user (id, name, email) VALUES (3, 'Charlie', 'charlie@example.com');

INSERT INTO category (id, name) VALUES (1, 'Concert');
INSERT INTO category (id, name) VALUES (2, 'Conference');
INSERT INTO category (id, name) VALUES (3, 'Workshop');
INSERT INTO category (id, name) VALUES (4, 'Meetup');

INSERT INTO location (id, address, city) VALUES (1, '123 Main St', 'Springfield');
INSERT INTO location (id, address, city) VALUES (2, '456 Oak Ave', 'Shelbyville');
INSERT INTO location (id, address, city) VALUES (3, '789 Pine Rd', 'Ogdenville');
INSERT INTO location (id, address, city) VALUES (4, '321 Cedar Ln', 'North Haverbrook');

INSERT INTO event (id, event_name, description, start_time, end_time, organizer_id, category_id, location_id)
VALUES (1, 'Spring Concert', 'A delightful evening of classical music.', '2025-06-20 19:00:00', '2025-06-20 21:00:00', 1, 1, 1);
INSERT INTO event (id, event_name, description, start_time, end_time, organizer_id, category_id, location_id)
VALUES (2, 'Tech Conference', 'Annual conference on technology trends and innovation.', '2025-07-15 09:00:00', '2025-07-15 17:00:00', 2, 2, 2);
INSERT INTO event (id, event_name, description, start_time, end_time, organizer_id, category_id, location_id)
VALUES (3, 'Creative Workshop', 'Hands-on workshop for creative minds.', '2025-08-05 13:00:00', '2025-08-05 16:00:00', 3, 3, 3);
INSERT INTO event (id, event_name, description, start_time, end_time, organizer_id, category_id, location_id)
VALUES (4, 'Neighborhood Meetup', 'Local community meetup for networking and fun.', '2025-09-01 18:00:00', '2025-09-01 20:00:00', 2, 4, 4);
