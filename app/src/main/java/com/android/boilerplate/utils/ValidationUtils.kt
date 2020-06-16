package com.android.boilerplate.utils

import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidationUtils {

    private const val PATTERN_NAME = "^[A-Za-z]*$"
    private const val PATTERN_CODE = "^[0-9A-Za-z]*$"
    // accept at least one uppercase, one lowercase alphabet, one number and one special character and within a range 0f 8-20 characters, original pattern "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$"
    private const val PATTERN_PASSWORD = "^(?=.*[0-9])(?=.*[A-z])(?=.*[@#$%^[_]{|}&+=.,%~(*)-/:;<=>?!])(?=\\S+$).{8,20}$"

    fun isValidName(name: CharSequence): Boolean {
        val pattern: Pattern = Pattern.compile(PATTERN_NAME)
        val matcher: Matcher = pattern.matcher(name)
        return matcher.matches()
    }

    fun isValidEmail(email: CharSequence): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: CharSequence): Boolean {
        val pattern: Pattern = Pattern.compile(PATTERN_PASSWORD)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    fun isValidCode(code: CharSequence): Boolean {
        val pattern: Pattern = Pattern.compile(PATTERN_CODE)
        val matcher: Matcher = pattern.matcher(code)
        return matcher.matches()
    }
}