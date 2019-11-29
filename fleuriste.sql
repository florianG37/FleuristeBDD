-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 28 nov. 2019 à 18:00
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `fleuriste`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `IdClient` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(254) NOT NULL,
  `Prenom` varchar(254) NOT NULL,
  `Adresse` varchar(254) NOT NULL,
  `Ville` varchar(254) NOT NULL,
  `BonAchat` int(11) NOT NULL,
  PRIMARY KEY (`IdClient`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`IdClient`, `Nom`, `Prenom`, `Adresse`, `Ville`, `BonAchat`) VALUES
(1, 'nom', 'prenom', 'Adresse', 'Ville', 50);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `IdCommande` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `IdClient` int(11) NOT NULL,
  PRIMARY KEY (`IdCommande`),
  KEY `commande_ibfk_1` (`IdClient`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`IdCommande`, `Date`, `IdClient`) VALUES
(2, '1905-02-26', 1),
(3, '3920-10-26', 1),
(4, '2019-11-27', 1);

-- --------------------------------------------------------

--
-- Structure de la table `commander`
--

DROP TABLE IF EXISTS `commander`;
CREATE TABLE IF NOT EXISTS `commander` (
  `IdCommande` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL,
  PRIMARY KEY (`IdCommande`,`IdProduit`),
  KEY `IdProduit` (`IdProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fournir`
--

DROP TABLE IF EXISTS `fournir`;
CREATE TABLE IF NOT EXISTS `fournir` (
  `IdFournisseur` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  PRIMARY KEY (`IdFournisseur`,`IdProduit`),
  KEY `IdProduit` (`IdProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
  `IdFournisseur` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(254) NOT NULL,
  `Prenom` varchar(254) NOT NULL,
  `Adresse` varchar(254) NOT NULL,
  `Ville` varchar(254) NOT NULL,
  PRIMARY KEY (`IdFournisseur`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`IdFournisseur`, `Nom`, `Prenom`, `Adresse`, `Ville`) VALUES
(2, 'nom2', 'prenom', 'Adresse', 'Ville'),
(3, 'nom2', 'prenom', 'Adresse', 'Ville');

-- --------------------------------------------------------

--
-- Structure de la table `fourniture`
--

DROP TABLE IF EXISTS `fourniture`;
CREATE TABLE IF NOT EXISTS `fourniture` (
  `IdFourniture` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `IdFournisseur` int(11) NOT NULL,
  PRIMARY KEY (`IdFourniture`),
  KEY `IdFournisseur` (`IdFournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `livrer`
--

DROP TABLE IF EXISTS `livrer`;
CREATE TABLE IF NOT EXISTS `livrer` (
  `IdFournisseur` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL,
  PRIMARY KEY (`IdFournisseur`,`IdProduit`),
  KEY `IdProduit` (`IdProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `IdProduit` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(254) NOT NULL,
  `Categorie` varchar(254) NOT NULL,
  `Espece` varchar(254) NOT NULL,
  `Prix` float NOT NULL,
  `Quantite` int(11) NOT NULL,
  PRIMARY KEY (`IdProduit`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`IdProduit`, `Nom`, `Categorie`, `Espece`, `Prix`, `Quantite`) VALUES
(4, 'nom', 'Plante', 'espece', 14.2, 12),
(5, 'nom', 'Plante', 'espece', 14.2, 12);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commander`
--
ALTER TABLE `commander`
  ADD CONSTRAINT `commander_ibfk_1` FOREIGN KEY (`IdCommande`) REFERENCES `commande` (`IdCommande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commander_ibfk_2` FOREIGN KEY (`IdProduit`) REFERENCES `produit` (`IdProduit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fournir`
--
ALTER TABLE `fournir`
  ADD CONSTRAINT `fournir_ibfk_1` FOREIGN KEY (`IdFournisseur`) REFERENCES `fournisseur` (`IdFournisseur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fournir_ibfk_2` FOREIGN KEY (`IdProduit`) REFERENCES `produit` (`IdProduit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fourniture`
--
ALTER TABLE `fourniture`
  ADD CONSTRAINT `fourniture_ibfk_1` FOREIGN KEY (`IdFournisseur`) REFERENCES `fournisseur` (`IdFournisseur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `livrer`
--
ALTER TABLE `livrer`
  ADD CONSTRAINT `livrer_ibfk_1` FOREIGN KEY (`IdFournisseur`) REFERENCES `fournisseur` (`IdFournisseur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `livrer_ibfk_2` FOREIGN KEY (`IdProduit`) REFERENCES `produit` (`IdProduit`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
