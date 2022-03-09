package br.com.maximatech.appa.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.com.maximatech.appa.R
import br.com.maximatech.appa.model.PDV
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.include_base_list.*
import kotlinx.android.synthetic.main.item_clientes.*


class MainActivity : AppCompatActivity() {

    private var listaPDV: ArrayList<PDV> = ArrayList()
    private lateinit var adapter: ClientesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaPDV.add(PDV("151", "31.431.589/0001-06", "GOHAN"))
        listaPDV.add(PDV("152", "31.684.999/0001-10", "PAPELARIA TRIBUTÁRIA"))
        listaPDV.add(PDV("153", "43.245.519/0012-03", "KALUNGA"))

        adapter = ClientesAdapter(
            listaPDV,
            onClick = { pdv -> openActivityForResult(pdv) }
        )
        rcvDados.adapter = adapter
    }

    private fun openActivityForResult(pdv: PDV) {
        val sendIntent = Intent().apply {
            action = "br.com.maximatech.appb.PESQUISA"
            putExtra("pdv", Gson().toJson(pdv))
            type = "text/plain"
        }

        if (sendIntent.resolveActivity(packageManager) != null) {
            startForResult.launch(sendIntent)
        } else {
            Snackbar.make(this, view, "Instale o app B", Snackbar.LENGTH_SHORT).show()
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                intent?.let { receivedData ->
                    val tarefaConcluida = Gson().fromJson(receivedData.getStringExtra("tarefas"), PDV::class.java)
                    val itemChangedIndex =
                        listaPDV.indexOfFirst { tarefaConcluida.codigo == it.codigo }
                    listaPDV[itemChangedIndex] = tarefaConcluida
                    adapter.notifyDataSetChanged()
                }
            }
        }
}