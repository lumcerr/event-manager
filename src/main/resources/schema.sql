CREATE TABLE app_user (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100)
);

CREATE TABLE category (
  id INT PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE location (
  id INT PRIMARY KEY,
  address VARCHAR(255),
  city VARCHAR(100)
);

CREATE TABLE event (
  id INT PRIMARY KEY,
  event_name VARCHAR(150),
  description TEXT,
  start_time TIMESTAMP,
  end_time TIMESTAMP,
  organizer_id INT,
  category_id INT,
  location_id INT,
  CONSTRAINT fk_event_organizer FOREIGN KEY (organizer_id) REFERENCES app_user (id),
  CONSTRAINT fk_event_category FOREIGN KEY (category_id) REFERENCES category (id),
  CONSTRAINT fk_event_location FOREIGN KEY (location_id) REFERENCES location (id)
);

CREATE TABLE participant (
  id INT PRIMARY KEY,
  full_name VARCHAR(100),
  email VARCHAR(100)
);

CREATE TABLE event_participant (
  id INT PRIMARY KEY,
  event_id INT,
  participant_id INT,
  registered_at TIMESTAMP,
  CONSTRAINT fk_event_participant_event FOREIGN KEY (event_id) REFERENCES event (id),
  CONSTRAINT fk_event_participant_participant FOREIGN KEY (participant_id) REFERENCES participant (id)
);
