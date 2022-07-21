package br.com.github.chat.usecases.user.repository

import br.com.github.chat.entities.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {

}