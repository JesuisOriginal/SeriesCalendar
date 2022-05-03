package com.milenialsatwork.seriescalendar.view.adapter

import android.content.Context
import com.milenialsatwork.seriescalendar.model.data.Nota
import android.widget.BaseAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.milenialsatwork.seriescalendar.R
import android.widget.TextView

class ListaNotasAdapter(private val context: Context, private val notas: List<Nota>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return notas.size
    }

    override fun getItem(posicao: Int): Nota {
        return notas[posicao]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(posicao: Int, view: View, viewGroup: ViewGroup): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, viewGroup, false)
        val nota = notas[posicao]
        val titulo = viewCriada.findViewById<TextView>(R.id.item_nota_titulo)
        titulo.text = nota.titulo
        val descricao = viewCriada.findViewById<TextView>(R.id.item_nota_descricao)
        descricao.text = nota.descricao
        return viewCriada
    }
}