package com.example.mytodoapp.domain.exception

data class IncorrectNumberOfCharactersException( val minCharacters: Int, val maxCharacters: Int):
    RuntimeException("The number of characters must be between $minCharacters and $maxCharacters")