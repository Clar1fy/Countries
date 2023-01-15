package com.timplifier.countries.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addTextChangedListenerAnonymously(
    doSomethingBeforeTextChanged: (((text: String) -> Unit))? = null,
    doSomethingAfterTextChanged: (((text: Editable) -> Unit))? = null,
    doSomethingOnTextChanged: (((text: String) -> Unit))? = null,
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            doSomethingBeforeTextChanged?.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            doSomethingOnTextChanged?.invoke(s.toString())
        }

        override fun afterTextChanged(s: Editable) {
            doSomethingAfterTextChanged?.invoke(s)
        }
    })
}

fun EditText.textCharactersCount() = text.toString().count()