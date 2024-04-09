/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  phron
 * Created: Mar 27, 2024
 */

DROP DATABASE IF EXISTS finalproject;
CREATE DATABASE finalproject;
USE finalproject;
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255),
    UserType INT NOT NULL
);

CREATE TABLE FoodItems (
    ItemID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255),
    Quantity INT,
    ExpirationDate DATE,
    Price DECIMAL(10, 2),
    DiscountRate DECIMAL(3, 2),
    IsForDonation BOOLEAN,
    UserID INT,
    foreign key(userID) references Users(UserID)
);

CREATE TABLE Transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    ItemID INT,
    UserID INT,
    TransactionType ENUM('Purchase', 'Claim'),
    TransactionDate TIMESTAMP,
    FOREIGN KEY (ItemID) REFERENCES FoodItems(ItemID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Subscriptions (
    SubscriptionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    Location VARCHAR(255),
    CommunicationMethod ENUM('Email', 'Phone'),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE FoodWasteTracker (
    WasteID INT AUTO_INCREMENT PRIMARY KEY,
    ItemID INT,
    WastedQuantity INT,
    DateReported DATE,
    FOREIGN KEY (ItemID) REFERENCES FoodItems(ItemID)
);