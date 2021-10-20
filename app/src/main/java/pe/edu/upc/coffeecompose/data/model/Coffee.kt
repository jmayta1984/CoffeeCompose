package pe.edu.upc.coffeecompose.data.model

class Coffee(
    val poster: String


) {
    companion object {
        fun mock() = listOf(
            Coffee("https://user-images.githubusercontent.com/38768001/137636011-09664dad-686c-4db3-939f-96c2c9c7c6c5.png"),
            Coffee("https://user-images.githubusercontent.com/38768001/137636013-9da9ff14-91ca-41c7-9c5d-331eba887bb7.png"),
            Coffee("https://user-images.githubusercontent.com/38768001/137636016-f4cb8933-6815-4cc1-a6ca-4a0ae6a2cb40.png"),
            Coffee("https://user-images.githubusercontent.com/38768001/137636011-09664dad-686c-4db3-939f-96c2c9c7c6c5.png"),
            Coffee("https://user-images.githubusercontent.com/38768001/137636013-9da9ff14-91ca-41c7-9c5d-331eba887bb7.png"),
            Coffee("https://user-images.githubusercontent.com/38768001/137636016-f4cb8933-6815-4cc1-a6ca-4a0ae6a2cb40.png")
        )
    }
}