package br.com.github.chat.entities

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "person_id", nullable = false)
    val person: PersonEntity,

    @Column(name = "created", nullable = false)
    val createdAt: LocalDateTime
)

@Entity(name = "person")
data class PersonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate,

    @Column(name = "email", nullable = false)
    val email: String,
)

@Entity(name = "phone")
data class PhoneNumberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "area_code", nullable = false)
    val areaCode: String,

    @Column(name = "country_code", nullable = false)
    val countryCode: String,

    @Column(name = "number", nullable = false)
    val number: String,

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    val person: PersonEntity,
)

@Entity(name = "device")
data class DeviceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "manufacturer", nullable = false)
    val manufacturer: String,

    @Column(name = "system_version", nullable = false)
    val systemVersion: String,

    @Column(name = "system_operation", nullable = false)
    val systemOperation: String,

    @Column(name = "ip", nullable = false)
    val ip: String,

    @ManyToOne
    @JoinColumn(name = "phone_id", nullable = false)
    val phoneNumber: PhoneNumberEntity,

    @ManyToOne
    @JoinColumn(name = "phone_person_id", nullable = false)
    val person: PersonEntity
)