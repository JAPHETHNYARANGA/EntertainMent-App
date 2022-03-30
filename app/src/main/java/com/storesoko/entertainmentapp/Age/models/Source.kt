package com.storesoko.entertainmentapp.Age.models

data class Source(
    val annotations: Annotations,
    val measures: List<String>,
    val name: String,
    val substitutions: List<Any>
)