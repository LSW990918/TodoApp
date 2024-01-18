package com.example.mytodoapp.domain.exception

data class MismatchException(val id: Long, val modelId: Long) :
    RuntimeException("ID : $id does not match the ID : $modelId of model")