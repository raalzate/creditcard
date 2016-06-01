-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cards
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cards` ;

-- -----------------------------------------------------
-- Schema cards
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cards` DEFAULT CHARACTER SET latin1 ;
USE `cards` ;

-- -----------------------------------------------------
-- Table `cards`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cards`.`client` ;

CREATE TABLE IF NOT EXISTS `cards`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cards`.`card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cards`.`card` ;

CREATE TABLE IF NOT EXISTS `cards`.`card` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(45) NOT NULL,
  `status` TINYINT(1) NOT NULL DEFAULT '1',
  `amount` DOUBLE NOT NULL DEFAULT '0',
  `bonus` DOUBLE NULL,
  `cut_date` DATETIME NULL DEFAULT now(),
  `client_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_card_client_idx` (`client_id` ASC),
  CONSTRAINT `fk_card_client`
    FOREIGN KEY (`client_id`)
    REFERENCES `cards`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `cards`.`client`
-- -----------------------------------------------------
START TRANSACTION;
USE `cards`;
INSERT INTO `cards`.`client` (`id`, `name`, `cedula`) VALUES (3, 'Raul Andres Alzate', '1115069076');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cards`.`card`
-- -----------------------------------------------------
START TRANSACTION;
USE `cards`;
INSERT INTO `cards`.`card` (`id`, `label`, `status`, `amount`, `bonus`, `cut_date`, `client_id`) VALUES (1, 'Tarjeta Raul', 0, 200000, 0, NULL, 3);
INSERT INTO `cards`.`card` (`id`, `label`, `status`, `amount`, `bonus`, `cut_date`, `client_id`) VALUES (2, 'Tarjeta 2 Raul', 0, 300000, 0, NULL, 3);

COMMIT;

