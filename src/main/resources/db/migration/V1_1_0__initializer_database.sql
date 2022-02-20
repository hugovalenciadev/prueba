CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE users (
	id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
	email VARCHAR ( 250 ) UNIQUE NOT NULL,
	password VARCHAR ( 250 ) NOT NULL,
	role VARCHAR ( 100 ) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN NOT NULL DEFAULT true 
);

CREATE TABLE clients (
	id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
	name VARCHAR ( 250 ) UNIQUE NOT NULL,
    email VARCHAR ( 250 ) UNIQUE NOT NULL,
	address VARCHAR ( 500 ) NOT NULL,
	phone VARCHAR ( 100 ) NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE products (
	id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
	name VARCHAR ( 250 ) UNIQUE NOT NULL,
	description VARCHAR ( 500 ) NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE units_measure (
	id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
	name VARCHAR ( 250 ) UNIQUE NOT NULL,
	description VARCHAR ( 500 ) NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE warehouses (
	id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
	name VARCHAR ( 250 ) UNIQUE NOT NULL,
	address VARCHAR ( 500 ) NOT NULL,
    phone VARCHAR ( 100 ) NULL,
    type VARCHAR ( 100 ) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE deliveries (
	id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
	type VARCHAR ( 100 ) NOT NULL,
    client_id uuid NOT NULL,
    product_id uuid NOT NULL,
    unit_measure_id uuid NOT NULL,
    quantity INT NOT NULL,
    delivery_date TIMESTAMP NOT NULL,
    warehouse_id uuid NOT NULL,
    price NUMERIC(10,4) NOT NULL,
    discount NUMERIC(10,4) NOT NULL,
    total  NUMERIC(10,4) NOT NULL,
	vehicle_number VARCHAR ( 100 ) NOT NULL,
    tracking_number VARCHAR ( 100 ) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    enabled BOOLEAN NOT NULL DEFAULT true,
    FOREIGN KEY (client_id)
      REFERENCES clients (id),
    FOREIGN KEY (product_id)
      REFERENCES products (id),
    FOREIGN KEY (unit_measure_id)
      REFERENCES units_measure (id),
    FOREIGN KEY (warehouse_id)
      REFERENCES warehouses (id)
);