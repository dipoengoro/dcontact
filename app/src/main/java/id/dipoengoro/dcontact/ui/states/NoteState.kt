package id.dipoengoro.dcontact.ui.states

class NoteState(val note: String? = null, private val initialValue: String = "") :
    TextFieldState(validator = ::isNoteValid, errorFor = ::noteValidationError, initialValue) {
    init {
        note?.let { text = it.trim() }
    }
}

private fun noteValidationError(note: String): String =
    if (note.trim().isEmpty()) "The note field cannot be empty" else "Note not valid"

private fun isNoteValid(note: String): Boolean = note.trim().length > 1