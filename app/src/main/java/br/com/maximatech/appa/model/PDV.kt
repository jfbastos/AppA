package br.com.maximatech.appa.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PDV(
    var codigo: String,
    var cpfCnpj: String,
    var RazaoSocial: String,
    var tarefa: Boolean = false
): Parcelable
