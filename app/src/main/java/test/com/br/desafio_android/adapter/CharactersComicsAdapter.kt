package test.com.br.desafio_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_comics.view.*
import test.com.br.desafio_android.R
import test.com.br.desafio_android.response.ResultCharacters
import test.com.br.desafio_android.ui.main.ComicsFragment

class CharactersComicsAdapter(
    private val context: ComicsFragment
) : RecyclerView.Adapter<CharactersComicsAdapter.ViewHolder>() {

    private var listResponse: ArrayList<ResultCharacters> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view =
            LayoutInflater.from(context.activity).inflate(R.layout.item_list_comics, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listResponse.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listData = listResponse[position]

        holder.let {
            it.bindView(listData)
        }
    }

    fun setList(listDataSet: ArrayList<ResultCharacters>) {
        listResponse = listDataSet
    }

    fun addData(listDataAdd: List<ResultCharacters>) {
        this.listResponse.addAll(listDataAdd)
    }

    lateinit var mClickListener: ClickListener

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun bindView(listResult: ResultCharacters) {
            val nome = itemView.tvNameComics
            val price = itemView.tvComicsPrice
            val description = itemView.tvDescriptionComics
            val img = itemView.imgComics

            nome.text = listResult.title
            price.text = "R$ " + listResult.prices[0].price.toString()
            description.text = listResult.description

            try {
                Glide.with(itemView.context)
                    .load(listResult.images[0].path + "." + listResult.images[0].extension)
                    .override(itemView.width)
                    .into(img)
            } catch (exception: Exception) {
            }
        }
    }
}

