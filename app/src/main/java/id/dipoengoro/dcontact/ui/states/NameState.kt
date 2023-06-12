package id.dipoengoro.dcontact.ui.states

class NameState(val name: String? = null, private val initialValue: String = "") :
    TextFieldState(validator = ::isNameValid, errorFor = ::nameValidationError, initialValue) {
    init {
        name?.let { text = it.trim() }
    }
}

private fun nameValidationError(name: String): String =
    if (name.trim().isEmpty()) "The name field cannot be empty" else "Name not valid"

private fun isNameValid(name: String): Boolean = name.trim().length > 1