package br.com.github.chat.persistence.gateway

import br.com.github.chat.persistence.entity.toDeviceEntity
import br.com.github.chat.persistence.entity.toPhoneNumberEntity
import br.com.github.chat.persistence.entity.toUserEntity
import br.com.github.chat.persistence.repository.DeviceRepository
import br.com.github.chat.persistence.repository.PhoneRepository
import br.com.github.chat.persistence.repository.UserRepository
import br.com.github.chat.usecases.user.gateway.UserRegistrationGateway
import br.com.github.chat.usecases.user.model.UserCandidate
import br.com.github.chat.usecases.user.model.UserModel
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class UserCreationGatewayImpl(
    private val userRepository: UserRepository,
    private val phoneRepository: PhoneRepository,
    private val deviceRepository: DeviceRepository
): UserRegistrationGateway {

    override fun findAll(): List<UserModel> = emptyList()

    @Transactional
    override fun save(model: UserCandidate): UserModel {
        val userSaved = userRepository.save(model.toUserEntity())

        val phoneEntity = model.phoneNumber.toPhoneNumberEntity(userEntity = userSaved)
        phoneRepository.save(phoneEntity)

        val deviceEntity = model.device.toDeviceEntity(user = userSaved, phoneNumber = phoneEntity)
        deviceRepository.save(deviceEntity)

        return userSaved.toModel()
    }
}
