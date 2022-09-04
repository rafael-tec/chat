package br.com.github.chat.persistence.entity

import br.com.github.chat.usecases.user.model.PersonModel
import br.com.github.chat.usecases.user.model.PhoneNumberModel
import br.com.github.chat.usecases.user.model.UserCandidateModel
import br.com.github.chat.usecases.user.model.UserModel
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
) {
    fun toModel() = UserModel(
        id = this.id!!,
        person = this.person.toModel(),
        createdAt = this.createdAt
    )
}

fun UserCandidateModel.toEntity() = UserEntity(
    createdAt = LocalDateTime.now(),
    person = PersonEntity(
        name = this.person.name,
        birthDate = this.person.birthDate,
        email = this.person.email,
        phones = listOf(this.phoneNumber.toEntity()),
    )
)

fun PersonModel.toEntity() = PersonEntity(
    name = this.name,
    birthDate = this.birthDate,
    email = this.email
)

fun PhoneNumberModel.toEntity() = PhoneNumberEntity(
    number = this.number.toString(),
    areaCode = this.areaCode.toString(),
    countryCode = this.countryCode.toString()
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

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "person")
    @JoinColumn(name = "person_id", nullable = false)
    val phones: List<PhoneNumberEntity>? = null
) {
    fun toModel() = PersonModel(
        name = this.name,
        birthDate = this.birthDate,
        email = this.email
    )
}

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

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "person_id", nullable = false)
    val person: PersonEntity? = null,
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