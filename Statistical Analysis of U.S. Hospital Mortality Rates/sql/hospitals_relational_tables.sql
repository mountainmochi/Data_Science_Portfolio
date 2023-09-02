-- Disable unique checks, foreign key checks, and set SQL mode for compatibility
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- Enable local infile to read CSV from local machine
SHOW GLOBAL VARIABLES LIKE 'local_infile';
SET GLOBAL local_infile = 'ON';
SHOW GLOBAL VARIABLES LIKE 'local_infile';

-- Create database and table for group_project
CREATE SCHEMA IF NOT EXISTS `group_project` DEFAULT CHARACTER SET latin1;
USE `group_project`;

-- Load data from CSV file into the table
LOAD DATA LOCAL INFILE '/Users/utsavthota/Library/CloudStorage/OneDrive-TheUniversityofChicago/Courses/MSCA 31012 Data Engineering Platforms/Final Project/Readmissions_and_Deaths_-_Hospital.csv'
INTO TABLE group_project.readmissions_and_deaths_hospital
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS;

-- Create tables for Hospitals table
CREATE TABLE IF NOT EXISTS `Hospitals` (
  `provider_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `zip_code` VARCHAR(255) NOT NULL,
  `county_name` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`provider_id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- Create tables for Measures Table
CREATE TABLE IF NOT EXISTS `Measures` (
    `measure_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `national_comparison_data` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`measure_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;
    
-- Create table for Hospital_Measures table
CREATE TABLE IF NOT EXISTS `Hospital_Measures` (
  `hospital_measure_id` INT NOT NULL AUTO_INCREMENT,
  `provider_id` INT NOT NULL,
  `measure_id` INT NOT NULL,
  PRIMARY KEY (`hospital_measure_id`),
  FOREIGN KEY (`provider_id`) 
    REFERENCES `Hospitals` (`provider_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
  FOREIGN KEY (`measure_id`) 
    REFERENCES `Measures` (`measure_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for Measure_Date table
CREATE TABLE IF NOT EXISTS `Measure_Date` (
  `measure_date_id` INT NOT NULL AUTO_INCREMENT,
  `measure_id` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  PRIMARY KEY (`measure_date_id`),
  FOREIGN KEY (`measure_id`) 
    REFERENCES `Measures` (`measure_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for Locations table
CREATE TABLE IF NOT EXISTS `Locations` (
  `location_id` INT NOT NULL,
  `location_name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `city` VARCHAR(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `zip_code` INT NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create the Readmissions_and_Deaths_Hospital table
CREATE TABLE IF NOT EXISTS `Readmissions_and_Deaths_Hospital` (
  `index` INT NOT NULL,
  `provider_id` INT NULL DEFAULT NULL,
  `measure_id` INT NOT NULL,
  `score` DECIMAL(10, 2) DEFAULT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`index`),
  FOREIGN KEY (`provider_id`)
    REFERENCES `Hospitals` (`provider_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`measure_id`)
    REFERENCES `Measures` (`measure_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`location_id`)
    REFERENCES `Locations` (`location_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;


