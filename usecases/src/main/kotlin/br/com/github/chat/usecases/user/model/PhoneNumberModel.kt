package br.com.github.chat.usecases.user.model

data class PhoneNumberModel(
    val areaCode: String,
    val countryCode: String,
    val number: String
) {
    fun fullPhoneNumber() = "${this.countryCode}${this.areaCode}${this.number}"
}