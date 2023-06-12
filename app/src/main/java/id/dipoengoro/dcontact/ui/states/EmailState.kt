package id.dipoengoro.dcontact.ui.states

class EmailState(val email: String? = null, private val initialValue: String = "") :
    TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError, initialValue) {
    init {
        email?.let {
            if (it.isEmpty() or checkEmailValidation(email))
                text = it
        }
    }
}

private fun checkEmailValidation(email: String): Boolean =
    email.matches(Regex("^(.+)@(.+)\$"))

private fun emailValidationError(email: String): String {
    return if (email.trim().isEmpty()) {
        "The email field cannot be empty"
    } else if (checkEmailValidation(email)) {
        "The email field only accepts digits"
    } else "Email not valid"
}

private fun isEmailValid(email: String): Boolean =
    (email.trim().length > 6) or (email.trim().length < 18)