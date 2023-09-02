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

SHOW GLOBAL VARIABLES LIKE 'local_infile';
SET GLOBAL local_infile = 'ON';
SHOW GLOBAL VARIABLES LIKE 'local_infile';

drop schema `hospitals_dw`;
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








#drop table `hospitals_dw`.`readmissions_and_deaths_hospital_base`;

#CREATE TABLE IF NOT EXISTS `hospitals_dw`.`readmissions_and_deaths_hospital_base` (
#  `index` int DEFAULT NULL,
#  `provider_id` int DEFAULT NULL,
#  `hospital_name` varchar(255) DEFAULT NULL,
#  `address` varchar(255) DEFAULT NULL,
#  `city` varchar(255) DEFAULT NULL,
#  `state` varchar(255) DEFAULT NULL,
#  `zip_code` int DEFAULT NULL,
#  `county_name` varchar(255) DEFAULT NULL,
#  `phone_number` varchar(255) DEFAULT NULL,
#  `measure_name` varchar(255) DEFAULT NULL,
#  `measure_id` varchar(255) DEFAULT NULL,
#  `compared_to_national` varchar(255) DEFAULT NULL,
#  `denominator` int DEFAULT NULL,
#  `score` decimal(10,2) DEFAULT NULL,
#  `lower_estimate` decimal(10,2) DEFAULT NULL,
#  `higher_estimate` decimal(10,2) DEFAULT NULL,
#  `footnote` varchar(255) DEFAULT NULL,
#  `measure_start_date` varchar(255) DEFAULT NULL,
#  `measure_end_date` varchar(255) DEFAULT NULL,
#  `location` varchar(255) DEFAULT NULL
#)
#ENGINE = InnoDB
#DEFAULT CHARACTER SET = latin1;


#select * from group_project.readmissions_and_deaths_hospital_base ;
#select * from hospitals_dw.readmissions_and_deaths_hospital_base ;

#LOAD DATA LOCAL INFILE '/Users/nbarac/Documents/MScA/Q2/Data Engineering/Group Project/Readmissions_and_Deaths_-_Hospital.csv'
#INTO TABLE hospitals_dw.readmissions_and_deaths_hospital_base
#FIELDS TERMINATED BY ',' ENCLOSED BY '"'
#LINES TERMINATED BY '\n'
#IGNORE 1 ROWS;