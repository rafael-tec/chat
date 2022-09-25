package br.com.github.chat.persistence.repository

import br.com.github.chat.persistence.entity.DeviceEntity
import br.com.github.chat.persistence.entity.PhoneNumberEntity
import br.com.github.chat.persistence.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Int>

interface PhoneRepository : CrudRepository<PhoneNumberEntity, Long>

interface DeviceRepository : CrudRepository<DeviceEntity, Long>