package test.com.br.desafio_android.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.com.br.desafio_android.util.API_CHARACTERS

class RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_CHARACTERS)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun listCharacters() = retrofit.create(InterfaceApi::class.java)!!
}