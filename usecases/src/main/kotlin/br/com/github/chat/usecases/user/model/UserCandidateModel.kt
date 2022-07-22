package br.com.github.chat.usecases.user.model

import br.com.github.chat.entities.PersonEntity
import br.com.github.chat.entities.PhoneNumberEntity
import br.com.github.chat.entities.UserEntity
import java.time.LocalDateTime

data class UserCandidateModel(
    val person: PersonModel,
    val phoneNumber: PhoneNumberModel,
    val device: DeviceModel
) {
   fun toEntity(): UserEntity {
       val phones = listOf(
           PhoneNumberEntity(
               number = this.phoneNumber.number.toString(),
               areaCode = this.phoneNumber.areaCode.toString(),
               countryCode = this.phoneNumber.countryCode.toString(),
               person = PersonEntity(
                   name = this.person.name,
                   birthDate = this.person.birthDate,
                   email = this.person.email,
               )
           )
       )

       return UserEntity(
           person = PersonEntity(
               name = this.person.name,
               birthDate = this.person.birthDate,
               email = this.person.email,
               phones = phones,
           ),
           createdAt = LocalDateTime.now()
       )
   }
}
