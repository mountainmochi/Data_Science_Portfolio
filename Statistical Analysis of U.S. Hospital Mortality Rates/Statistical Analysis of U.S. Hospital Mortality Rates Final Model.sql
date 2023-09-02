#setup bs just run dont think about it
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

#this will allow mysql to look into your pc files and pull the CSV
Show GLObal variables like 'local_infile';
Set GLObal local_infile = 'ON';
#when you run this your local_infile should say on, if it doesnt try again
Show GLObal variables like 'local_infile';


#creating the schema and then table for our group project
CREATE SCHEMA IF NOT EXISTS `group_project` DEFAULT CHARACTER SET latin1 ;
USE `group_project`;
#creating the table with all of the proper data types for our data
CREATE TABLE IF NOT EXISTS `group_project`.`readmissions_and_deaths_hospital_base` (
  `index` INT NULL DEFAULT NULL,
  `provider_id` INT NULL DEFAULT NULL,
  `hospital_name` VARCHAR(255) NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `state` VARCHAR(255) NULL DEFAULT NULL,
  `zip_code` INT NULL DEFAULT NULL,
  `county_name` VARCHAR(255) NULL DEFAULT NULL,
  `phone_number` VARCHAR(255) NULL DEFAULT NULL,
  `measure_name` VARCHAR(255) NULL DEFAULT NULL,
  `measure_id` VARCHAR(255) NULL DEFAULT NULL,
  `compared_to_national` VARCHAR(255) NULL DEFAULT NULL,
  `denominator` INT NULL DEFAULT NULL,
  `score` DECIMAL(10, 2) DEFAULT NULL,
  `lower_estimate` DECIMAL(10, 2) DEFAULT NULL,
  `higher_estimate` DECIMAL(10, 2) DEFAULT NULL,
  `footnote` VARCHAR(255) NULL DEFAULT NULL,
  `measure_start_date` VARCHAR(255) NULL DEFAULT NULL,
  `measure_end_date` VARCHAR(255) NULL DEFAULT NULL,
  `location` VARCHAR(255) NULL DEFAULT NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;
#PLEASE CHANGE THE LOCATION TO THE LOCATION WHERE YOU ARE SAVING THE CSV!!!!!!!!!!!!!!!!!
#this will read the table in and it will take like 2 seconds
LOAD DATA LOCAL INFILE '/Users/nbarac/Documents/MScA/Q2/Data Engineering/Group Project/Readmissions_and_Deaths_-_Hospital.csv'
INTO TABLE group_project.readmissions_and_deaths_hospital_base
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS;


-- Create table for address table
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for city table
CREATE TABLE IF NOT EXISTS `city` (
  `city_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for state table
CREATE TABLE IF NOT EXISTS `state` (
  `state_id` INT NOT NULL AUTO_INCREMENT,
  `state` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for zip table
CREATE TABLE IF NOT EXISTS `zip_code` (
  `zip_code_id` INT NOT NULL AUTO_INCREMENT,
  `zip_code` INT NOT NULL,
  PRIMARY KEY (`zip_code_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for County table
CREATE TABLE IF NOT EXISTS `county` (
  `county_name_id` INT NOT NULL AUTO_INCREMENT,
  `county_name` varchar(255) NOT NULL,
  PRIMARY KEY (`county_name_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for Locations table
CREATE TABLE IF NOT EXISTS `locations` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(255) NOT NULL,
  `address_id` INT NOT NULL,
  `city_id` INT NOT NULL,
  `state_id` INT NOT NULL,
  `zip_code_id` INT NOT NULL,
  `county_name_id` INT NOT NULL,
  PRIMARY KEY (`location_id`),
  FOREIGN KEY (`address_id`) 
	REFERENCES `address` (`address_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
  FOREIGN KEY (`city_id`) 
	REFERENCES `city` (`city_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
  FOREIGN KEY (`state_id`) 
	REFERENCES `state` (`state_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
  FOREIGN KEY (`zip_code_id`) 
	REFERENCES `zip_code` (`zip_code_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
  FOREIGN KEY (`county_name_id`) 
	REFERENCES `county` (`county_name_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE  
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create tables for Hospitals table
CREATE TABLE IF NOT EXISTS `hospitals` (
  `provider_id` INT NOT NULL,
  `hospital_name` VARCHAR(255) NOT NULL,
  `location_id` INT NOT NULL,
  #`address` VARCHAR(255) NOT NULL,
  #`city` VARCHAR(255) NOT NULL,
  #`state` VARCHAR(255) NOT NULL,
  #`zip_code` VARCHAR(255) NOT NULL,
  #`county_name` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`provider_id`),
  FOREIGN KEY (`location_id`) 
	REFERENCES `locations` (`location_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- Create tables for Measures Table
CREATE TABLE IF NOT EXISTS `measures` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `measure_id` VARCHAR(255) NOT NULL,
    `measure_name` VARCHAR(255) NOT NULL,
    `compared_to_national` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;
    
-- Create table for Hospital_Measures table
CREATE TABLE IF NOT EXISTS `hospital_Measures` (
  `hospital_measure_id` INT NOT NULL AUTO_INCREMENT,
  `provider_id` INT NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`hospital_measure_id`),
  FOREIGN KEY (`provider_id`) 
    REFERENCES `hospitals` (`provider_id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE,
  FOREIGN KEY (`id`) 
    REFERENCES `measures` (`id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create table for Measure_Date table
CREATE TABLE IF NOT EXISTS `measure_Date` (
  `measure_date_id` INT NOT NULL AUTO_INCREMENT,
  `id` INT NOT NULL,
  `measure_start_date` DATE NOT NULL,
  `measure_end_date` DATE NOT NULL,
  PRIMARY KEY (`measure_date_id`),
  FOREIGN KEY (`id`) 
    REFERENCES `measures` (`id`) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;

-- Create the Readmissions_and_Deaths_Hospital table
CREATE TABLE IF NOT EXISTS `readmissions_and_deaths_hospital` (
  `index` INT NOT NULL,
  `provider_id` INT NULL DEFAULT NULL,
  `id` INT NOT NULL,
  `score` DECIMAL(10, 2) DEFAULT NULL,
  `denominator` INT NOT NULL,
  `lower_estimate` DECIMAL(10, 2) DEFAULT NULL,
  `higher_estimate` DECIMAL(10, 2) DEFAULT NULL,
  `footnote` varchar(255) NOT NULL,
  PRIMARY KEY (`index`),
  FOREIGN KEY (`provider_id`)
    REFERENCES `hospitals` (`provider_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`id`)
    REFERENCES `measures` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  #FOREIGN KEY (`location_id`)
   # REFERENCES `Locations` (`location_id`)
   # ON DELETE CASCADE
   # ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = latin1;


/***********************************************
**                MSc ANALYTICS 
**     DATA ENGINEERING PLATFORMS (MSCA 31012)
** File:   Final Project
** Desc:   Creating the dimensional model for Final Project
** Auth:   Group 1
** Date:   2/21/23
************************************************/

#Dimensions: Date, Location, Measure, Hospitals
#Fact: Scores



CREATE SCHEMA IF NOT EXISTS `hospitals_dw` DEFAULT CHARACTER SET latin1 ;
USE `hospitals_dw` ;



-- -----------------------------------------------------
-- Table `hospitals_dw`.`dim_location` ***connects FACT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospitals_dw`.`dim_location` (
  `location_key` INT NOT NULL AUTO_INCREMENT,
  `location_id` VARCHAR(255) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `county` VARCHAR(100),
  `state` VARCHAR(50) NOT NULL,
  `zipcode` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`location_key`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



INSERT INTO `hospitals_dw`.`dim_location` (`location_id`, `address`, `city`, `county`, `state`, `zipcode`)
SELECT DISTINCT
    IFNULL(CONCAT(`address`, ' ', `city`, ' ', `county_name`, ' ', `state`, ' ', `zip_code`), 'Unknown') AS `location_id`,
    IFNULL(`address`,'Unknown'),
    IFNULL(`city`,'Unknown'),
    IFNULL(`county_name`,'Unknown'),
    IFNULL(`state`,'Unknown'),
    IFNULL(`zip_code`,'Unknown')
FROM `group_project`.`readmissions_and_deaths_hospital_base`;


-- -----------------------------------------------------
-- Table `hospitals_dw`.`dim_date` ***connects to FACT
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `hospitals_dw`.`dim_date` (
  `date_key` BIGINT(20) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `day_of_week` CHAR(10) NULL DEFAULT NULL,
  `month` CHAR(10) NULL DEFAULT NULL,
  `month_day` INT NULL DEFAULT NULL,
  `year` INT NULL DEFAULT NULL,
  PRIMARY KEY (`date_key`),
  UNIQUE INDEX `date_key` (`date_key` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;




INSERT INTO `hospitals_dw`.`dim_date` (`date_key`, `start_date`, `end_date`, `day_of_week`, `month`, `month_day`, `year`)
SELECT DISTINCT
    UNIX_TIMESTAMP(STR_TO_DATE(`measure_start_date`, '%m/%d/%y')) AS `date_key`,
    STR_TO_DATE(`measure_start_date`, '%m/%d/%y') AS `start_date`,
    STR_TO_DATE(`measure_end_date`, '%m/%d/%y') AS `end_date`,
    DAYNAME(STR_TO_DATE(`measure_start_date`, '%m/%d/%y')) AS `day_of_week`,
    MONTHNAME(STR_TO_DATE(`measure_start_date`, '%m/%d/%y')) AS `month`,
    DAYOFMONTH(STR_TO_DATE(`measure_start_date`, '%m/%d/%y')) AS `month_day`,
    YEAR(STR_TO_DATE(`measure_start_date`, '%m/%d/%y')) AS `year`
FROM `group_project`.`readmissions_and_deaths_hospital_base`;




-- -----------------------------------------------------
-- Table `hospitals_dw`.`dim_hospital`. ****connects to FACT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospitals_dw`.`dim_provider` (
  `provider_key` INT NOT NULL AUTO_INCREMENT,
  `provider_id` INT NOT NULL,
  `provider_name` VARCHAR(100) NOT NULL,
  `provider_phone` VARCHAR(100),
  PRIMARY KEY (`provider_key`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


INSERT INTO `hospitals_dw`.`dim_provider` (`provider_id`, `provider_name`, `provider_phone`)
SELECT DISTINCT
    `provider_id`,
    `hospital_name`,
    `phone_number`
FROM `group_project`.`readmissions_and_deaths_hospital_base`;


-- -----------------------------------------------------
-- Table `hospitals_dw`.`dim_measure`. ****connects to FACT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospitals_dw`.`dim_measure` (
  `measure_key` INT NOT NULL AUTO_INCREMENT,
  `measure_id` VARCHAR(100) NOT NULL,
  `measure_name` VARCHAR(100) NOT NULL,
  `compared_to_national` VARCHAR(100) NOT NULL,
  `measure_start` DATE NOT NULL,
  `measure_end` DATE NOT NULL,
  PRIMARY KEY (`measure_key`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



INSERT INTO `hospitals_dw`.`dim_measure` (`measure_id`, `measure_name`,`compared_to_national` ,`measure_start`, `measure_end`)
SELECT DISTINCT `measure_id`, 
`measure_name`,
`compared_to_national`,
STR_TO_DATE(`measure_start_date`, '%m/%d/%Y'), 
STR_TO_DATE(`measure_end_date`, '%m/%d/%Y')
FROM `group_project`.`readmissions_and_deaths_hospital_base`;


-- -----------------------------------------------------
-- Table `hospitals_dw`.`fact_scores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospitals_dw`.`fact_scores` (
    `score_id` INT NOT NULL AUTO_INCREMENT,
    `provider_key` INT NOT NULL,
    `measure_key` INT NOT NULL,
    `date_key`BIGINT(20) NOT NULL,
    `location_key` INT NOT NULL,
    `denominator` INT,
    `score` INT,
    `lower_bound` INT,
    `upper_bound` INT,
    PRIMARY KEY (`score_id`),
    CONSTRAINT `fk_factScore_dimProvider` FOREIGN KEY (`provider_key`)
        REFERENCES `hospitals_dw`.`dim_provider` (`provider_key`),
    CONSTRAINT `fk_factScore_dimMeasure` FOREIGN KEY (`measure_key`)
        REFERENCES `hospitals_dw`.`dim_measure` (`measure_key`),
	CONSTRAINT `fk_factScore_dimDate` FOREIGN KEY (`date_key`)
        REFERENCES `hospitals_dw`.`dim_date` (`date_key`),
	CONSTRAINT `fk_factScore_dimLocation` FOREIGN KEY (`location_key`)
        REFERENCES `hospitals_dw`.`dim_location` (`location_key`)
    );


INSERT INTO `hospitals_dw`.`fact_scores` (`provider_key`, `measure_key`, `date_key`, `location_key`, `denominator`, `score`, `lower_bound`, `upper_bound`)
SELECT
    dp.`provider_key`,
    dm.`measure_key`,
    dd.`date_key`,
    dl.`location_key`,
    r.`denominator`,
    r.`score`,
    r.`lower_estimate`,
    r.`higher_estimate`
FROM
    `group_project`.`readmissions_and_deaths_hospital_base` r
    JOIN `hospitals_dw`.`dim_provider` dp ON r.`provider_id` = dp.`provider_id`
    JOIN `hospitals_dw`.`dim_measure` dm ON r.`measure_id` = dm.`measure_id`
    JOIN `hospitals_dw`.`dim_location` dl ON r.`zip_code` = dl.`zipcode`
    JOIN `hospitals_dw`.`dim_date` dd ON STR_TO_DATE(r.`measure_start_date`, '%m/%d/%y') = dd.`start_date`;

