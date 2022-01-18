package com.krishna.TexasHamburger.repository;

import com.krishna.TexasHamburger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("From User  where  userName = ?1")
    User findByUserName(@Param("user_name") String userName);
}
