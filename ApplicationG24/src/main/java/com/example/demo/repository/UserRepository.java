package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/* UserRepository estende l'interfaccia di repository JPA.
 * JpaRepository permette di effettuare diverse operazioni, quali Create,
 * Read, Update e Delete su di un'entità JPA. 
 * In particolare, in questo caso accetta due parametri, l'entità User e il tipo
 * di chiave primaria dell'entità, Long.
 */

public interface UserRepository extends JpaRepository<User, Long>
{
	/* Metodo findByEmail - Utilizza la funzionalità di creazione di query del 
	 *                      metodo di Spring Data JPA per generare query di
	 *                      ricerca di un utente in base all'indirizzo email.
	 *                      Il metodo accetta un argomento, l'indirizzo email,
	 *                      e restituisce un oggetto User o null, nel caso non venga
	 *                      trovata una corrispondenza.
	 */
	                  
	User findByEmail(String email);
	
	//@Modifying
	/*@Query("insert into users_roles values(:user_id, :role_id)")
	void insertUserRole(@Param("user_id") Long user_id, @Param("role_id") Long role_id);*/
	
	
    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);
}