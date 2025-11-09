package br.com.bnms.electriccarapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.bnms.electriccarapp.R
import br.com.bnms.electriccarapp.domain.CarroDomain
import br.com.bnms.electriccarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONTokener
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@Suppress("DEPRECATION")
class CarFragment : Fragment() {

    private lateinit var fabCalcular: FloatingActionButton
    private lateinit var listaCarros: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var noWifi: ImageView
    private lateinit var noWifiText: TextView
    private val carroArray: ArrayList<CarroDomain> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupListeners()


    }

    override fun onResume() {
        super.onResume()

        // Only call the service *after* the views are initialized
        if (checkForInternet(requireContext())) {
            progress.isVisible = true
            callService()
        } else {
            emptyState()
        }
    }

    fun emptyState(){
        progress.isVisible = false
        listaCarros.isVisible = false
        noWifi.isVisible = true
        noWifiText.isVisible = true
    }

    fun setupView(view: View) {
        view.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
            progress = findViewById(R.id.pb_loader)

            noWifi = findViewById(R.id.iv_empty_state)
            noWifiText = findViewById(R.id.tv_no_wifi)
        }
    }
    fun setUpLista() {

        val carAdapter = CarAdapter(carroArray)

        listaCarros.apply {
            isVisible = true
            adapter = carAdapter
        }
    }
    fun setupListeners() {
        fabCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    fun callService(){
        val urlBase =  "https://raw.githubusercontent.com/belarminon/my-eletric-car-app/refs/heads/master/my-electric-car-app.json"
        getCarInformation(urlBase)
    }

    fun checkForInternet(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else{
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    fun getCarInformation(urlString: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("Coroutine", "Iniciando")

            var urlConnection: HttpsURLConnection? = null
            try {
                val url = URL(urlString)
                urlConnection = url.openConnection() as HttpsURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                )

                val responsoCode = urlConnection.responseCode

                if (responsoCode == HttpsURLConnection.HTTP_OK) {
                    val response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    // Switch to main thread to update UI or log
                    withContext(Dispatchers.Main) {
                        processResponse(response)
                    }

                } else {
                    Log.e("Error", "Serviço indisponivel no momento ...")
                }


            } catch (ex: Exception) {
                Log.e("Error", "Erro ao realizar processamento: ${ex.message}")
            } finally {
                urlConnection?.disconnect()
                withContext(Dispatchers.Main) {
                    Log.d("Coroutine", "Finalizado")
                }
            }
        }
    }

    private fun processResponse(response: String) {
        try {
            val jsonArray = JSONTokener(response).nextValue() as JSONArray

            for (i in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(i)

                val model = CarroDomain(
                    id = json.getString("id").toInt(),
                    preco = json.getString("preco"),
                    bateria = json.getString("bateria"),
                    potencia = json.getString("potencia"),
                    tempoRecarga = json.getString("tempoRecarga"),
                    urlPhoto = json.getString("urlPhoto")
                )
                carroArray.add(model)
            }
            setUpLista()
            progress.isVisible = false
            noWifi.isVisible = false
            noWifiText.isVisible = false
        } catch (ex: Exception) {
            Log.e("Error", "Erro ao processar JSON: ${ex.message}")
        }
    }
}