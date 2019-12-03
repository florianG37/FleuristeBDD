-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  mar. 03 déc. 2019 à 23:06
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.3.10

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

CREATE TABLE `client` (
  `IdClient` int(11) NOT NULL,
  `Nom` varchar(254) NOT NULL,
  `Prenom` varchar(254) NOT NULL,
  `Adresse` varchar(254) NOT NULL,
  `Ville` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`IdClient`, `Nom`, `Prenom`, `Adresse`, `Ville`) VALUES
(22, 'Gibeau', 'Yannick', '29 rue chemin bleu', 'Kervignac'),
(23, 'Marec', 'François', '5 rue des lavandes', 'Hennebont'),
(24, 'Denis', 'Michel', '3 avenue Jules George', 'Nostang'),
(25, 'Brual', 'Yann', '3 rue Saint Michel', 'Lorient');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `IdCommande` int(11) NOT NULL,
  `Date` date NOT NULL,
  `IdClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`IdCommande`, `Date`, `IdClient`) VALUES
(24, '2019-12-02', 25),
(25, '2019-12-02', 23),
(26, '2019-12-02', 22),
(27, '2019-12-02', 25),
(28, '2019-12-02', 22),
(29, '2019-12-02', 23);

-- --------------------------------------------------------

--
-- Structure de la table `commander`
--

CREATE TABLE `commander` (
  `IdCommande` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commander`
--

INSERT INTO `commander` (`IdCommande`, `IdProduit`, `Quantite`) VALUES
(24, 15, 2),
(24, 18, 1),
(25, 16, 2),
(26, 17, 3),
(26, 18, 1),
(27, 15, 1),
(27, 16, 1),
(27, 19, 1),
(28, 15, 5),
(29, 15, 3),
(29, 16, 1);

-- --------------------------------------------------------

--
-- Structure de la table `fournir`
--

CREATE TABLE `fournir` (
  `IdFournisseur` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `fournir`
--

INSERT INTO `fournir` (`IdFournisseur`, `IdProduit`) VALUES
(7, 15),
(7, 16),
(7, 19),
(8, 15),
(8, 16),
(8, 17);

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `IdFournisseur` int(11) NOT NULL,
  `Nom` varchar(254) NOT NULL,
  `Prenom` varchar(254) NOT NULL,
  `Adresse` varchar(254) NOT NULL,
  `Ville` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`IdFournisseur`, `Nom`, `Prenom`, `Adresse`, `Ville`) VALUES
(7, 'Fernandez', 'Leonard', '101 avenue St Michel', 'Hennebont'),
(8, 'Michard', 'Jerome', '1 rue de la mairie', 'Ploemeur');

-- --------------------------------------------------------

--
-- Structure de la table `fourniture`
--

CREATE TABLE `fourniture` (
  `IdFourniture` int(11) NOT NULL,
  `Date` date NOT NULL,
  `IdFournisseur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `fourniture`
--

INSERT INTO `fourniture` (`IdFourniture`, `Date`, `IdFournisseur`) VALUES
(15, '2019-12-02', 8),
(16, '2019-12-02', 8);

-- --------------------------------------------------------

--
-- Structure de la table `livrer`
--

CREATE TABLE `livrer` (
  `IdFourniture` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `livrer`
--

INSERT INTO `livrer` (`IdFourniture`, `IdProduit`, `Quantite`) VALUES
(15, 15, 10),
(15, 16, 10),
(16, 15, 5),
(16, 16, 15),
(16, 17, 10);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `IdProduit` int(11) NOT NULL,
  `Nom` varchar(254) NOT NULL,
  `Categorie` varchar(254) NOT NULL,
  `Espece` varchar(254) NOT NULL,
  `Prix` double NOT NULL,
  `Quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`IdProduit`, `Nom`, `Categorie`, `Espece`, `Prix`, `Quantite`) VALUES
(15, 'Eucalyptus', 'Plante', 'Ibisca', 7, 44),
(16, 'Bonzai', 'Plante', 'Zekova', 12, 56),
(17, 'Rose', 'Fleur', 'Epinea', 3, 57),
(18, 'Calamondin', 'Plante', 'Agrume', 45, 17),
(19, 'Candide', 'Plante', 'Orchidée', 15, 28);

-- --------------------------------------------------------

--
-- Structure de la table `reduction`
--

CREATE TABLE `reduction` (
  `IdReduction` int(11) NOT NULL,
  `IdClient` int(11) NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date DEFAULT NULL,
  `BonAchat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reduction`
--

INSERT INTO `reduction` (`IdReduction`, `IdClient`, `DateDebut`, `DateFin`, `BonAchat`) VALUES
(13, 22, '2019-12-02', NULL, 15),
(14, 23, '2019-12-02', NULL, 10),
(15, 24, '2019-12-02', NULL, 15),
(16, 25, '2019-12-02', NULL, 20);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`IdClient`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`IdCommande`),
  ADD KEY `commande_ibfk_1` (`IdClient`);

--
-- Index pour la table `commander`
--
ALTER TABLE `commander`
  ADD PRIMARY KEY (`IdCommande`,`IdProduit`),
  ADD KEY `IdProduit` (`IdProduit`);

--
-- Index pour la table `fournir`
--
ALTER TABLE `fournir`
  ADD PRIMARY KEY (`IdFournisseur`,`IdProduit`),
  ADD KEY `IdProduit` (`IdProduit`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`IdFournisseur`);

--
-- Index pour la table `fourniture`
--
ALTER TABLE `fourniture`
  ADD PRIMARY KEY (`IdFourniture`),
  ADD KEY `IdFournisseur` (`IdFournisseur`);

--
-- Index pour la table `livrer`
--
ALTER TABLE `livrer`
  ADD PRIMARY KEY (`IdFourniture`,`IdProduit`),
  ADD KEY `IdProduit` (`IdProduit`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`IdProduit`);

--
-- Index pour la table `reduction`
--
ALTER TABLE `reduction`
  ADD PRIMARY KEY (`IdReduction`),
  ADD KEY `IdClient` (`IdClient`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `IdClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `IdCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `IdFournisseur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `fourniture`
--
ALTER TABLE `fourniture`
  MODIFY `IdFourniture` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `IdProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `reduction`
--
ALTER TABLE `reduction`
  MODIFY `IdReduction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

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
  ADD CONSTRAINT `livrer_ibfk_2` FOREIGN KEY (`IdProduit`) REFERENCES `produit` (`IdProduit`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `livrer_ibfk_3` FOREIGN KEY (`IdFourniture`) REFERENCES `fourniture` (`IdFourniture`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reduction`
--
ALTER TABLE `reduction`
  ADD CONSTRAINT `reduction_ibfk_1` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
