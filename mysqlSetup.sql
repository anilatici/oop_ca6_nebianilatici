DROP DATABASE IF EXISTS formula1;
CREATE DATABASE IF NOT EXISTS `formula1`;
USE `formula1`;

CREATE TABLE teams (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    powerUnit VARCHAR(255) NOT NULL,
    wins int(10) NOT NULL,
    budget float(15,3)
    );

INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Mercedes', 'Germany', 'Mercedes', 125, 484000000.243);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Red Bull Racing', 'Austria', 'Honda RBPT', 94, 463000000.653);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Alfa Romeo Racing', 'Switzerland', 'Ferrari', 10, 132000000.346);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('AlphaTauri', 'Italy', 'Honda RBPT', 1, 138000000.712);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Aston Martin Cognizant F1 Team', 'United Kingdom', 'Mercedes', 0, 188000000.234);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Ferrari', 'Italy', 'Ferrari', 243, 463000000.123);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Haas F1 Team', 'United States', 'Ferrari', 0, 17300000.423);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('McLaren F1 Team', 'United Kingdom', 'Mercedes', 183, 269000000.832);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Williams Racing', 'United Kingdom', 'Mercedes', 114, 141000000.346);
INSERT INTO teams (name, country, powerUnit, wins, budget) VALUES ('Alpine F1 Team', 'France', 'Renault', 1, 272000000.872);
