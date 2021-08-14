CREATE USER  'dev'@'localhost' IDENTIFIED BY "Passe123";

CREATE DATABASE IF NOT EXISTS DB_CRYPTO;

GRANT ALL ON DB_CRYPTO.* TO 'dev'@'localhost';

FLUSH PRIVILEGES;


DROP TABLE IF EXISTS `secret_keys`;

CREATE TABLE `secret_keys` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `nom` VARCHAR(255) NOT NULL UNIQUE,
    `algorithme` VARCHAR(255) NOT NULL,
    `taille` INTEGER NOT NULL,
    `provider`  VARCHAR(255),
    `encodage_hex`  TEXT NOT NULL
);


DROP TABLE IF EXISTS `key_pairs`;

CREATE TABLE `key_pairs` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `nom` VARCHAR(255) NOT NULL UNIQUE,
    `algorithme` VARCHAR(255) NOT NULL,
    `taille` INTEGER NOT NULL,
    `provider`  VARCHAR(255),
    `public_key_encodage_hex`  TEXT NOT NULL,
    `private_key_encodage_hex`  TEXT NOT NULL
);

