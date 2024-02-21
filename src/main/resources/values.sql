USE kesmarkiDb;

INSERT INTO person(name)
VALUES ('John Doe'), ('Jane Doe');

INSERT INTO address(address_type, person_id)
VALUES ('PERMANENT', 1), ('TEMPORARY', 1), ('PERMANENT', 2);

INSERT INTO contact_information(contact_info, contact_information_type, address_id)
VALUES ('+36301112222', 'PHONE', 1), ('me@example.com', 'EMAIL', 1), ('you@example.com', 'EMAIL', 2)