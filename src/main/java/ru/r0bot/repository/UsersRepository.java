package ru.r0bot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.r0bot.entity.User;


@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

}

