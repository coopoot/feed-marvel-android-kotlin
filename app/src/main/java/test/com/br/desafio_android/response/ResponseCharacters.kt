package test.com.br.desafio_android.response

data class ResponseCharacters(

    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
)

data class Data(

    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ResultCharacters>
)

data class Comics(

    val available: Int,
    val collectionURI: String,
    val items: List<Items>,
    val returned: Int
)

data class Events(

    val available: Int,
    val collectionURI: String,
    val items: List<Items>,
    val returned: Int
)

data class ResultCharacters(

    val id: Int,
    val name: String,
    val title: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events,
    val urls: List<Urls>,
    val prices: List<Prices>,
    val images: List<Images>
)

data class Series(

    val available: Int,
    val collectionURI: String,
    val items: List<Items>,
    val returned: Int
)

data class Stories(

    val available: Int,
    val collectionURI: String,
    val items: List<Items>,
    val returned: Int
)

data class Thumbnail(

    val path: String,
    val extension: String
)

data class Urls(

    val type: String,
    val url: String
)

data class Items(

    val resourceURI: String,
    val name: String
)

data class Prices(

    val type: String,
    val price: Double
)

data class Images(

    val path: String,
    val extension: String
)