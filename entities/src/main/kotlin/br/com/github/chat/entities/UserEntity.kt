package br.com.github.chat.entities

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserEntity(
    @Id
    val id: Long,
    val person: PersonEntity,
    val phoneNumber: PhoneNumberEntity,
    val device: DeviceEntity
)

@Entity
data class PersonEntity(
    @Id
    val id: Long,
    val name: String,
    val birthDate: LocalDate,
    val email: String
)

@Entity
data class PhoneNumberEntity(
    @Id
    val id: Long,
    val areaCode: String,
    val countryCode: String,
    val number: String
)

@Entity
data class DeviceEntity(
    @Id
    val id: Long,
    val manufacturer: String,
    val systemVersion: String,
    val systemOperation: String
)