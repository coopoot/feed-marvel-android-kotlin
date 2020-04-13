package test.com.br.desafio_android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import test.com.br.desafio_android.R
import test.com.br.desafio_android.adapter.CharactersAdapter
import test.com.br.desafio_android.data.api.RetrofitClient
import test.com.br.desafio_android.response.ResponseCharacters
import test.com.br.desafio_android.response.ResultCharacters

class MainFragment : Fragment() {


    private lateinit var adapter: CharactersAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: CharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)
        this.loadCharacters()
    }

    private fun loadCharacters() {

        progressBar.visibility = View.VISIBLE

        val call = RetrofitClient().listCharacters().getListCharacters()
        call.enqueue(object : Callback<ResponseCharacters> {
            override fun onResponse(
                call: Call<ResponseCharacters>?,
                response: Response<ResponseCharacters>?
            ) {
                response?.body()?.let {
                    val list: ResponseCharacters = it
                    configList(list.data.results)
                }
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResponseCharacters>, t: Throwable) {
                progressBar.visibility = View.GONE
            }
        })
    }

    private fun configList(list: List<ResultCharacters>) {

        val recyclerView = recyclerView_list
        adapter = CharactersAdapter(this)

        recyclerView.adapter = adapter

        adapter.setList(list as ArrayList<ResultCharacters>)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        adapter.setOnItemClickListener(object : CharactersAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                val detailsCharactersFragment = DetailsCharactersFragment()
                val fragmentTransaction = fragmentManager!!.beginTransaction()

                val bundle = Bundle()

                bundle.putString("id", list[pos].id.toString())
                bundle.putString("name", list[pos].name)
                bundle.putString("description", list[pos].description)
                bundle.putString(
                    "image",
                    list[pos].thumbnail.path + "." + list[pos].thumbnail.extension
                )
                detailsCharactersFragment.arguments = bundle

                fragmentTransaction.replace(R.id.container, detailsCharactersFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        })
    }
}
