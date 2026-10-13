package pl.jawegiel.howareyou.data.model

data class Interaction(var likes: Long = 0, var comments: List<String> = mutableListOf())