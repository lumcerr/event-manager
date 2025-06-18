-- USERS
INSERT INTO app_user (id, name, email) VALUES
  (1, 'Alice', 'alice@example.com'),
  (2, 'Bob', 'bob@example.com'),
  (3, 'Charlie', 'charlie@example.com');

-- CATEGORIES
INSERT INTO category (id, name) VALUES
  (1, 'Concert'),
  (2, 'Conference'),
  (3, 'Workshop');

-- LOCATIONS
INSERT INTO location (id, address, city) VALUES
  (1, '123 Main St', 'Springfield'),
  (2, '456 Oak Ave', 'Shelbyville'),
  (3, '789 Pine Blvd', 'Capital City');

-- EVENTS
INSERT INTO event (id, event_name, description, start_time, end_time, organizer_id, category_id, location_id) VALUES
  (1, 'Spring Concert', 'Live music under the stars', '2025-07-10T18:00:00', '2025-07-10T21:00:00', 1, 1, 1),
  (2, 'Tech Conference 2025', 'The future of innovation', '2025-08-05T09:00:00', '2025-08-05T17:00:00', 2, 2, 2),
  (3, 'Creative Writing Workshop', 'Improve your writing craft', '2025-09-12T10:00:00', '2025-09-12T15:00:00', 3, 3, 3);

-- PARTICIPANTS
INSERT INTO participant (id, full_name, email) VALUES
  (1, 'Diana Prince', 'diana@example.com'),
  (2, 'Clark Kent', 'clark@example.com'),
  (3, 'Bruce Wayne', 'bruce@example.com'),
  (4, 'Barry Allen', 'barry@example.com');

-- EVENT PARTICIPANTS
INSERT INTO event_participant (id, event_id, participant_id, registered_at) VALUES
  (1, 1, 1, '2025-07-01T10:00:00'),
  (2, 1, 2, '2025-07-01T10:05:00'),
  (3, 2, 3, '2025-08-01T14:30:00'),
  (4, 3, 4, '2025-09-01T12:00:00');
