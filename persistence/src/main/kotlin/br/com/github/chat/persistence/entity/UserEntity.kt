package br.com.github.chat.persistence.entity

import br.com.github.chat.usecases.user.model.DeviceModel
import br.com.github.chat.usecases.user.model.PhoneNumberModel
import br.com.github.chat.usecases.user.model.UserCandidate
import br.com.github.chat.usecases.user.model.UserModel
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "email_confirmed", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    val emailConfirmed: Boolean = false,
) {

    fun toModel() = UserModel(
        name = this.name,
        email = this.email,
        birthDate = this.birthDate,
        id = this.id!!,
        createdAt = this.createdAt
    )
}

fun UserCandidate.toUserEntity() = UserEntity(
    createdAt = LocalDateTime.now(),
    name = this.name,
    birthDate = this.birthDate,
    email = this.email
)

fun PhoneNumberModel.toPhoneNumberEntity(userEntity: UserEntity) = PhoneNumberEntity(
    areaCode = this.areaCode,
    countryCode = this.countryCode,
    number = this.number,
    createdAt = LocalDateTime.now(),
    user = userEntity
)

fun DeviceModel.toDeviceEntity(user: UserEntity, phoneNumber: PhoneNumberEntity) = DeviceEntity(
    manufacturer = this.manufacturer,
    systemOperation = this.system.systemOperation,
    systemVersion = this.system.version,
    ip = this.ip,
    user = user,
    phoneNumber = phoneNumber
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
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime
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
    @JoinColumn(name = "phone_user_id", nullable = false)
    val user: UserEntity
)