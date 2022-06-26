package br.com.github.chat.usecases.user.model

data class PhoneNumberModel(
    val areaCode: Int,
    val countryCode: Int,
    val number: Int
) {
    fun fullPhoneNumber() = "${this.countryCode}${this.areaCode}${this.number}"
}