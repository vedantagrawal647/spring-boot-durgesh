package com.durgesh.durgesh1_Jpa;

import com.durgesh.durgesh1_Jpa.entites.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;


public interface UserRepo extends CrudRepository<UserEntity,Integer> {

   //custom query/drived query
    public List<UserEntity> findByName(String name);

    public List<UserEntity> findByNameAndCity(String name,String city);

    List<UserEntity> findByNameOrCity(String name, String city);

    List<UserEntity> findByNameStartingWith(String prefix);

    List<UserEntity> findByNameEndingWith(String suffix);

    List<UserEntity> findByNameContaining(String words);

    List<UserEntity> findByIdLessThan(int id);

    List<UserEntity> findByIdGreaterThanEqual(int id);

    List<UserEntity> findByIdIn(Collection<Integer> ids);

    List<UserEntity> findByCityOrderByNameDesc(String city);

    //jpql query
    @Query("select u from UserEntity u ")
    public List<UserEntity> getAllUser();

    @Query("select u from UserEntity u Where u.name =:n ")
    public List<UserEntity> getUserByName(@Param("n") String name);

    @Query("select u from UserEntity u Where u.name =:n and u.city =:c ")
    public List<UserEntity> getUserByNameAndCity(@Param("n") String name,@Param("c") String city);

    //native query --
    @Query(value = "select * from user_entity",nativeQuery = true)
    public  List<UserEntity> getUsersNative();
}
