package com.example.myapplication

class ApiResult : ArrayList<Match>()
data class Match(
    val constestation: List<Int>,
    val heure_debut: String,
    val joueur1: Joueur,
    val joueur2: Joueur,
    val nombre_coup_dernier_echange: Int,
    val pointage: Pointage,
    val serveur: Int,
    val temps_partie: Int,
    val terrain: String,
    val tournoi: String,
    val vitesse_dernier_service: Int
)

data class Joueur(
    val age: Int,
    val nom: String,
    val pays: String,
    val prenom: String,
    val rang: Int
)

data class Pointage(
    val echange: List<Int>,
    val `final`: Boolean,
    val jeu: List<List<Int>>,
    val manches: List<Int>
)