package test.com.br.desafio_android.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import test.com.br.desafio_android.response.ResponseCharacters
import test.com.br.desafio_android.util.API_KEY
import test.com.br.desafio_android.util.HASH
import test.com.br.desafio_android.util.TS

interface InterfaceApi {

    @GET("characters?ts=${TS}&apikey=${API_KEY}&hash=${HASH}")
    fun getListCharacters(): Call<ResponseCharacters>

    @GET("characters/{id}/comics?ts=${TS}&apikey=${API_KEY}&hash=${HASH}")
    fun getListComicsCharacter(@Path("id") id: String): Call<ResponseCharacters>
}