-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema rh-db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rh-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rh-db` DEFAULT CHARACTER SET utf8 ;
USE `rh-db` ;

-- -----------------------------------------------------
-- Table `rh-db`.`departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rh-db`.`departamento` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `departamento` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_DEPARTAMENTO` (`departamento` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rh-db`.`cargo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rh-db`.`cargo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cargo` VARCHAR(80) NOT NULL,
  `id_departamento` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CARGO_DEPARTAMENTO_IDX` (`id_departamento` ASC),
  CONSTRAINT `FK_CARGO_DEPARTAMENTO`
    FOREIGN KEY (`id_departamento`)
    REFERENCES `rh-db`.`departamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rh-db`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rh-db`.`endereco` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(80) NOT NULL,
  `numero` INT(4) NOT NULL,
  `complemento` VARCHAR(12) NULL DEFAULT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `rh-db`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rh-db`.`funcionario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `data_entrada` DATE NOT NULL,
  `data_saida` DATE NULL DEFAULT NULL,
  `salario` DOUBLE(8,2) NOT NULL,
  `id_cargo` INT(11) NOT NULL,
  `id_endereco` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UQ_NOME` (`nome` ASC),
  INDEX `FK_FUNCIONARIO_CARGO_IDX` (`id_cargo` ASC),
  INDEX `FK_FUNCIONARIO_ENDERECO_IDX` (`id_endereco` ASC),
  CONSTRAINT `FK_FUNCIONARIO_CARGO`
    FOREIGN KEY (`id_cargo`)
    REFERENCES `rh-db`.`cargo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FUNCIONARIO_ENDERECO`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `rh-db`.`endereco` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
