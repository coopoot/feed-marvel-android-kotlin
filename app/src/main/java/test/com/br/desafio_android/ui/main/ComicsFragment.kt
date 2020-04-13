package test.com.br.desafio_android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.comics_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.com.br.desafio_android.R
import test.com.br.desafio_android.adapter.CharactersComicsAdapter
import test.com.br.desafio_android.data.api.RetrofitClient
import test.com.br.desafio_android.response.ResponseCharacters
import test.com.br.desafio_android.response.ResultCharacters

class ComicsFragment : Fragment() {

    private lateinit var adapter: CharactersComicsAdapter

    companion object {
        fun newInstance() = ComicsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comics_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id = arguments!!.getString("id")
        this.loadCharacters(id!!)
    }

    private fun loadCharacters(id: String) {

        progressBarComics.visibility = View.VISIBLE

        val call = RetrofitClient().listCharacters().getListComicsCharacter(id)
        call.enqueue(object : Callback<ResponseCharacters> {
            override fun onResponse(
                call: Call<ResponseCharacters>?,
                response: Response<ResponseCharacters>?
            ) {
                response?.body()?.let {
                    val list: ResponseCharacters = it
                    configList(list.data.results)
                }
                progressBarComics.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
                imgNotfound.visibility = View.VISIBLE
                progressBarComics.visibility = View.GONE
            }
        })
    }

    private fun configList(list: List<ResultCharacters>) {

        val recyclerView = recyclerViewComics
        adapter = CharactersComicsAdapter(this)

        recyclerView.adapter = adapter

        adapter.setList(list as ArrayList<ResultCharacters>)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        adapter.setOnItemClickListener(object : CharactersComicsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {

            }
        })
    }
}
