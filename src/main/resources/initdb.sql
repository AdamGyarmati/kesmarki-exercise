CREATE DATABASE kesmarkiDb;
USE kesmarkiDb;

CREATE TABLE person (
                        id INT PRIMARY KEY IDENTITY,
                        name VARCHAR(255)
)

CREATE TABLE address (
                         id INT PRIMARY KEY IDENTITY,
                         address_type VARCHAR(50),
                         person_id INT,
                         FOREIGN KEY (person_id) REFERENCES person(id)
)

CREATE TABLE contact_information (
                                     id INT PRIMARY KEY IDENTITY,
                                     contact_info VARCHAR(255),
                                     contact_information_type VARCHAR(50),
                                     address_id INT,
                                     FOREIGN KEY (address_id) REFERENCES address(id)
)