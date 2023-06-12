package id.dipoengoro.dcontact.ui.states

class AliasState(val alias: String? = null, private val initialValue: String = "") :
    TextFieldState(validator = ::isAliasValid, errorFor = ::aliasValidationError, initialValue) {
    init {
        alias?.let { text = it.trim() }
    }
}

private fun aliasValidationError(alias: String): String =
    if (alias.trim().isEmpty()) "The alias field cannot be empty" else "Alias not valid"

private fun isAliasValid(alias: String): Boolean = alias.trim().length > 1