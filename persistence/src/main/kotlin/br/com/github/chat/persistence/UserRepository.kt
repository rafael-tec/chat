package br.com.github.chat.persistence

import br.com.github.chat.entities.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {

}