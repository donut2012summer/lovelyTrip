DROP TABLE IF EXISTS lovely_trip.supplier CASCADE ;

CREATE TABLE lovely_trip.supplier(
                                     id           SERIAL PRIMARY KEY
    , company_name VARCHAR(255) NOT NULL
    , license_no   VARCHAR(255) NOT NULL
    , contact_name VARCHAR(255) NOT NULL
    , email        VARCHAR(255) NOT NULL
    , phone        VARCHAR(50) NOT NULL
    , created_time TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , status       INT NOT NULL
);

DROP TABLE IF EXISTS lovely_trip.location CASCADE;

CREATE TABLE lovely_trip.location(
                                     id SERIAL PRIMARY KEY
    , name	VARCHAR(255) NOT NULL
    , type	INT NOT NULL -- 0: country 1: city 2:district 3: landmark
    , parent_id	INT REFERENCES lovely_trip.location(id) ON DELETE SET NULL
    , latitude	DECIMAL(10, 6)
    , longitude	DECIMAL(10,6)
    , created_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , status	INT NOT NULL	-- 0: deleted, 1: active
);

DROP TABLE IF EXISTS lovely_trip.trip CASCADE ;

CREATE TABLE lovely_trip.trip(
                                 id            SERIAL PRIMARY KEY
    , title         VARCHAR(255) NOT NULL
    , description   TEXT NOT NULL
    , main_location_id   INT NOT NULL REFERENCES lovely_trip.location(id)
    , min_duration      INT NOT NULL
    , created_time  TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time  TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , supplier_id   INT NOT NULL
    , status        INT NOT NULL DEFAULT 1 -- 0: deleted, 1: active
    , FOREIGN KEY (supplier_id) REFERENCES lovely_trip.supplier(id)

);

DROP TABLE IF EXISTS lovely_trip.trip_location CASCADE;

CREATE TABLE lovely_trip.trip_location(
                                          trip_id	    INT NOT NULL REFERENCES lovely_trip.trip(id) ON DELETE CASCADE
    , location_id	INT NOT NULL REFERENCES lovely_trip.location(id) ON DELETE CASCADE
    , PRIMARY KEY (trip_id, location_id)
);

DROP TABLE IF EXISTS lovely_trip.tour_group CASCADE;

CREATE TABLE lovely_trip.tour_group(
                                       id                SERIAL PRIMARY KEY
    , trip_id           INT NOT NULL
    , title	            VARCHAR(255) NOT NULL
    , description       TEXT NOT NULL
    , departure_date    DATE NOT NULL
    , return_date	    DATE NOT NULL
    , unit_price    	DECIMAL(10,2) NOT NULL
    , availability	    INT NOT NULL
    , created_time	    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time	    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP -- TODO Auto update trigger
    , status            INT NOT NULL -- 0: cancelled, 1: open, 2: full
    , FOREIGN KEY (trip_id) REFERENCES lovely_trip.trip(id)
);

DROP TABLE IF EXISTS lovely_trip.user CASCADE;

CREATE TABLE  lovely_trip.user(
                                  id	        SERIAL PRIMARY KEY
    , first_name	VARCHAR(50) NOT NULL
    , last_name	    VARCHAR(50) NOT NULL
    , email	        VARCHAR(255) NOT NULL
    , password_hash	VARCHAR(255) NOT NULL
    , phone	        VARCHAR(20) NOT NULL
    , date_of_birth	DATE NOT NULL
    , user_role	    INT NOT NULL -- 0: user, 1: admin
    , status	    INT NOT NULL -- 0: inactive, 1: active
    , created_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);


DROP TABLE IF EXISTS lovely_trip.booking CASCADE;

CREATE TABLE lovely_trip.booking(
                                    id	    SERIAL PRIMARY KEY
    , user_id	INT NOT NULL
    , tour_group_id	INT NOT NULL
    , participant_count	INT NOT NULL
    , unit_price	DECIMAL(10,2) NOT NULL
    , total_amount	DECIMAL(10,2) NOT NULL
    , final_amount	DECIMAL(10,2) NOT NULL
    , discount_type	    VARCHAR(50) NOT NULL
    , discount_amount	DECIMAL(10,2) DEFAULT 0
    , created_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , status	    INT NOT NULL -- 0: cancelled, 1: processing, 2: paid, 3: completed
    , FOREIGN KEY (user_id)         REFERENCES lovely_trip.user(id)
    , FOREIGN KEY (tour_group_id)   REFERENCES lovely_trip.tour_group(id)
);

DROP TABLE IF EXISTS lovely_trip.image CASCADE ;

CREATE TABLE lovely_trip.image(
      id	             SERIAL PRIMARY KEY
    , reference_table    INT NOT NULL CHECK(reference_table IN (0, 1, 2)) -- 0: user, 1: trip, 2: tour_group
    , reference_id       INT NOT NULL
    , image_url	         TEXT NOT NULL
    , image_zone	     INT NOT NULL -- 0: banner, 1: gallery 2: content
    , stored_filename    VARCHAR(100) NOT NULL
    , created_time	     TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time	     TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , display_order	     INT NOT NULL
    , status             INT NOT NULL CHECK ( status IN (0, 1, 2)) -- 0: TEMPORARY, 1: VALID 2: DELETED
);

COMMENT ON COLUMN lovely_trip.image.status IS
'Image status:
  0 = TEMPORARY (uploaded but not linked),
  1 = VALID (linked to a trip or entity),
  2 = DELETED (soft-deleted or marked for cleanup)';

DROP TABLE IF EXISTS lovely_trip.review CASCADE ;

CREATE TABLE lovely_trip.review(
      id	        SERIAL PRIMARY KEY
    , trip_id	    INT NOT NULL
    , user_id	    INT NOT NULL
    , rating	    DECIMAL(2,1) CHECK (rating >= 1 AND rating <= 5) -- rate 1-5, can have half point e.g. 4.5, 4.8
    , comment	    TEXT
    , created_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , updated_time	TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
    , FOREIGN KEY (trip_id) REFERENCES lovely_trip.trip(id)
    , FOREIGN KEY (user_id) REFERENCES lovely_trip.user(id)
);


-- Suppliers
INSERT INTO lovely_trip.supplier (company_name, license_no, contact_name, email, phone, status)
VALUES
    ('Adventure Co', 'LIC123456', 'Alice Johnson', 'alice@adventureco.com', '123-456-7890', 1),
    ('TravelFun Inc', 'LIC654321', 'Bob Smith', 'bob@travelfun.com', '098-765-4321', 1);

-- Locations
INSERT INTO lovely_trip.location (name, type, parent_id, latitude, longitude, status)
VALUES
    ('Japan', 0, NULL, 36.2048, 138.2529, 1),                         -- Country
    ('Tokyo', 1, 1, 35.6895, 139.6917, 1),                            -- City
    ('Shinjuku', 2, 2, 35.6938, 139.7034, 1),                         -- District
    ('Tokyo Tower', 3, 2, 35.6586, 139.7454, 1);                      -- Landmark

-- Trips
INSERT INTO lovely_trip.trip (title, description, main_location_id, min_duration, supplier_id, status)
VALUES
    ('Explore Tokyo', 'A cultural and city life experience in Tokyo.', 2, 3, 1, 1),
    ('Landmarks of Japan', 'Visit famous landmarks across Japan.', 1, 5, 2, 1);

-- Trip Locations
INSERT INTO lovely_trip.trip_location (trip_id, location_id)
VALUES
    (1, 2), -- Tokyo
    (1, 3), -- Shinjuku
    (2, 1), -- Japan
    (2, 4); -- Tokyo Tower

-- Tour Groups
INSERT INTO lovely_trip.tour_group (trip_id, title, description, departure_date, return_date, unit_price, availability, status)
VALUES
    (1, 'Spring Tokyo Tour', 'Enjoy the cherry blossoms!', '2025-04-01', '2025-04-04', 499.99, 20, 1),
    (2, 'Japan Discovery Week', 'Discover historical and modern Japan.', '2025-05-10', '2025-05-15', 799.99, 15, 1);

-- Users
INSERT INTO lovely_trip.user (first_name, last_name, email, password_hash, phone, date_of_birth, user_role, status)
VALUES
    ('Emily', 'Chen', 'emily@example.com', 'hashed_password1', '0912-345-678', '1995-06-15', 0, 1),
    ('John', 'Doe', 'john@example.com', 'hashed_password2', '0922-456-789', '1990-01-01', 1, 1);

-- Bookings
INSERT INTO lovely_trip.booking (user_id, tour_group_id, participant_count, unit_price, total_amount, final_amount, discount_type, discount_amount, status)
VALUES
    (1, 1, 2, 499.99, 999.98, 899.98, 'SpringPromo', 100.00, 2),
    (2, 2, 1, 799.99, 799.99, 799.99, 'None', 0, 1);

-- Images
INSERT INTO lovely_trip.image (reference_table, reference_id, image_url, image_zone, display_order, stored_filename, status )
VALUES
    (1, 1, 'https://example.com/images/tokyo-banner.jpg', 0, 1, 'tokyo-banner.jpg',1),
    (1, 1, 'https://example.com/images/tokyo-gallery1.jpg', 1, 2, 'tokyo-gallery1.jpg',1),
    (2, 1, 'https://example.com/images/tourgroup-banner.jpg', 0,1, 'tourgroup-banner.jpg', 1);


-- Reviews
INSERT INTO lovely_trip.review (trip_id, user_id, rating, comment)
VALUES
    (1, 1, 4.5, 'Great experience, well organized!'),
    (2, 2, 5.0, 'Best trip I’ve ever had!');


-- TODO update_time trigger