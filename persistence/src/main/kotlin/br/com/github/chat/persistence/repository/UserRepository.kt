package br.com.github.chat.persistence.repository

import br.com.github.chat.persistence.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long>