CREATE TABLE attendees
(
    id         VARCHAR(255) NOT NULL PRIMARY KEY,
    nome      VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    event_id   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT attendess_event_id_fkey FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE RESTRICT ON UPDATE CASCADE
);