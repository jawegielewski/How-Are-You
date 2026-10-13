package pl.jawegiel.howareyou.data.model

data class Post(
    val id: String? = null,
    val text: String = "",
    val userId: String = "",
    val interaction: Interaction? = null,
)