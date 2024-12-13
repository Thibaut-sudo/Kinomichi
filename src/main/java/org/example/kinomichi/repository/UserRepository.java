package org.example.kinomichi.repository;

import org.example.kinomichi.model._User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<_User, Long> {



    // Trouver un utilisateur par prénom
    List<_User> findByFirstName(String firstName);

    // Trouver un utilisateur par statut
    List<_User> findByStatut(String statut);

    // Requête personnalisée pour récupérer un utilisateur par son nom complet
    @Query("SELECT u FROM _User u WHERE u.firstName = :firstName AND u.lastName = :lastName")
    Optional<_User> findByFullName(String firstName, String lastName);

    // Récupérer tous les utilisateurs ayant un certain statut (par exemple "user", "admin", etc.)
    List<_User> findByStatutContaining(String statut);

    // Requête personnalisée pour récupérer tous les utilisateurs triés par leur nom
    @Query("SELECT u FROM _User u ORDER BY u.lastName ASC")
    List<_User> findAllSortedByLastName();

    //requete personnalisée pour virifier si un utilisateur existe email
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM _User u WHERE u.email = :email")
    boolean existsByEmail(String email);

    //verifie le statut de l'utilisateur pour savoir s'il est admin
    @Query("SELECT CASE WHEN u.statut = 'admin' THEN true ELSE false END FROM _User u WHERE u.email = :email")
    boolean isAdmin(String email);



}
