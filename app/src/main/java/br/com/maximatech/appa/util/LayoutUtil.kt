package br.com.maximatech.appa.util

import com.amulyakhare.textdrawable.TextDrawable

object LayoutUtil {

    fun criaIconeLetraComSombraCircular(letra: String, cor: Int): TextDrawable {
        return TextDrawable.builder()
            .beginConfig()
            .bold()
            .endConfig()
            .buildRound(letra, cor)
    }


}