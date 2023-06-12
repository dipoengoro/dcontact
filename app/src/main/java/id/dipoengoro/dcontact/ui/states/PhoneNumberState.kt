package id.dipoengoro.dcontact.ui.states

import id.dipoengoro.dcontact.ui.states.PhoneNumberState.Companion.checkPhoneNumberValidation
import java.util.regex.Pattern

class PhoneNumberState(val phoneNumber: String? = null, private val initialValue: String = "") :
    TextFieldState(validator = ::isPhoneNumberValid, errorFor = ::phoneNumberValidationError, initialValue) {
    init {
        phoneNumber?.let { text = it }
    }

    companion object {
        fun checkPhoneNumberValidation(phoneNumber: String): Boolean =
            Pattern.matches("^[0-9]+$", phoneNumber)
    }
}

private fun phoneNumberValidationError(phoneNumber: String): String =
    if (phoneNumber.trim().isEmpty()) "The phone number field cannot be empty"
    else "Phone number is not valid"

private fun isPhoneNumberValid(phoneNumber: String): Boolean =
    (phoneNumber.trim().isEmpty()) or
            (phoneNumber.trim().length > 6) or
            (phoneNumber.trim().length < 18)