-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CafeDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CafeDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CafeDB` DEFAULT CHARACTER SET utf8 ;
USE `CafeDB` ;

-- -----------------------------------------------------
-- Table `CafeDB`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CafeDB`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор клиента',
  `Role` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL COMMENT 'Email клиента для регистрации',
  `Password` VARCHAR(45) NOT NULL COMMENT 'Пароль от аккаунта клиента',
  `Login` VARCHAR(15) NOT NULL COMMENT 'Имя клиента в системе',
  `Points` INT NOT NULL DEFAULT 0 COMMENT 'Количество бонусных баллов клиента',
  `MoneyAmount` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CafeDB`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CafeDB`.`Order` (
  `idOrder` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор заказа',
  `idClient` INT NOT NULL COMMENT 'Id клиента, сделавшего заказ',
  `ReceivingTime` TIME NOT NULL COMMENT 'Время, указанное клиентом для получения заказа',
  `Status` VARCHAR(45) NOT NULL,
  `TotalPrice` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idOrder`),
  INDEX `fk_Order_Client_idx` (`idClient` ASC),
  CONSTRAINT `fk_Order_Client`
    FOREIGN KEY (`idClient`)
    REFERENCES `CafeDB`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CafeDB`.`Meal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CafeDB`.`Meal` (
  `idMeal` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор блюда',
  `MealName` VARCHAR(45) NOT NULL COMMENT 'Название блюда',
  `MealPrice` DECIMAL NOT NULL COMMENT 'Цена',
  `MealDescription` VARCHAR(300) NOT NULL,
  `MealImgPath` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idMeal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CafeDB`.`Dessert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CafeDB`.`Dessert` (
  `idDessert` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор десерта',
  `DessertName` VARCHAR(45) NOT NULL COMMENT 'Название десерта',
  `DessertPrice` DECIMAL NOT NULL COMMENT 'Цена',
  `DessertDescription` VARCHAR(500) NOT NULL,
  `DessertImgPath` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idDessert`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CafeDB`.`Drink`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CafeDB`.`Drink` (
  `idDrink` INT NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор напитка',
  `DrinkName` VARCHAR(45) NOT NULL COMMENT 'Название напитка',
  `DrinkPrice` DECIMAL NOT NULL COMMENT 'Цена',
  `DrinkDescription` VARCHAR(500) NOT NULL,
  `DrinkImgPath` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idDrink`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CafeDB`.`FoodInOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CafeDB`.`FoodInOrder` (
  `idFoodInOrder` INT NOT NULL AUTO_INCREMENT,
  `idOrder` INT NOT NULL,
  `idDrink` INT NULL,
  `DrinkAmount` INT NULL,
  `idDessert` INT NULL,
  `DessertAmount` INT NULL,
  `idMeal` INT NULL,
  `MealAmount` INT NULL,
  INDEX `fk_FoodInOrder_Drink1_idx` (`idDrink` ASC),
  INDEX `fk_FoodInOrder_Dessert1_idx` (`idDessert` ASC),
  INDEX `fk_FoodInOrder_Meal1_idx` (`idMeal` ASC),
  PRIMARY KEY (`idFoodInOrder`),
  INDEX `fk_FoodInOrder_Order1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_FoodInOrder_Drink1`
    FOREIGN KEY (`idDrink`)
    REFERENCES `CafeDB`.`Drink` (`idDrink`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FoodInOrder_Dessert1`
    FOREIGN KEY (`idDessert`)
    REFERENCES `CafeDB`.`Dessert` (`idDessert`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FoodInOrder_Meal1`
    FOREIGN KEY (`idMeal`)
    REFERENCES `CafeDB`.`Meal` (`idMeal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FoodInOrder_Order1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `CafeDB`.`Order` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



