-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  ven. 29 nov. 2019 à 15:46
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
  `Ville` varchar(254) NOT NULL,
  `BonAchat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`IdClient`, `Nom`, `Prenom`, `Adresse`, `Ville`, `BonAchat`) VALUES
(1, 'nom', 'prenom', 'Adresse', 'Ville', 50);

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
(2, '1905-02-26', 1),
(3, '3920-10-26', 1),
(4, '2019-11-27', 1);

-- --------------------------------------------------------

--
-- Structure de la table `commander`
--

CREATE TABLE `commander` (
  `IdCommande` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fournir`
--

CREATE TABLE `fournir` (
  `IdFournisseur` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(2, 'nom2', 'prenom', 'Adresse', 'Ville'),
(3, 'nom2', 'prenom', 'Adresse', 'Ville');

-- --------------------------------------------------------

--
-- Structure de la table `fourniture`
--

CREATE TABLE `fourniture` (
  `IdFourniture` int(11) NOT NULL,
  `Date` date NOT NULL,
  `IdFournisseur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `livrer`
--

CREATE TABLE `livrer` (
  `IdFournisseur` int(11) NOT NULL,
  `IdProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(4, 'nom', 'Plante', 'espece', 14.199999809265137, 12),
(5, 'nom', 'Plante', 'espece', 14.199999809265137, 12);

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
  ADD PRIMARY KEY (`IdFournisseur`,`IdProduit`),
  ADD KEY `IdProduit` (`IdProduit`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`IdProduit`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `IdClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `IdCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `IdFournisseur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `fourniture`
--
ALTER TABLE `fourniture`
  MODIFY `IdFourniture` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `IdProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
