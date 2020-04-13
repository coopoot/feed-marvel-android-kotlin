package test.com.br.desafio_android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.details_characters_fragment.*
import test.com.br.desafio_android.R

class DetailsCharactersFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsCharactersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_characters_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = arguments!!.getString("id")
        val name = arguments!!.getString("name")
        val image = arguments!!.getString("image")
        val description = arguments!!.getString("description")

        tvTituloDetalhes.text = name
        tvDescricaoDetalhes.text = description

        context?.let {
            Glide.with(it)
                .load(image)
                .into(imgDetails)
        }

        floatingSales.setOnClickListener {
            val comicsFragment = ComicsFragment()
            val fragmentTransaction = fragmentManager!!.beginTransaction()

            val bundle = Bundle()

            bundle.putString("id", id)
            comicsFragment.arguments = bundle

            fragmentTransaction.replace(R.id.container, comicsFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}
