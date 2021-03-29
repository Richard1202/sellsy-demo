package com.example.demo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demo.R
import com.example.demo.data.model.Client
import com.example.demo.ui.adapter.ClientRecyclerAdapter
import com.example.demo.viewmodel.ClientsViewModel
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.fragment_client_list.view.*

/**
 * A fragment representing a list of clients.
 */
class ClientListFragment : Fragment() {

    private lateinit var adapter: ClientRecyclerAdapter

    private var hud: KProgressHUD? = null

    lateinit var viewModel: ClientsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_client_list, container, false)

        viewModel = ViewModelProvider(this).get(ClientsViewModel::class.java)
        viewModel.loading.observe(this, isViewLoadingObserver)
        viewModel.message.observe(this, onMessageErrorObserver)
        viewModel.clients.observe(this, renderClients)

        loadMore()

        adapter = ClientRecyclerAdapter(view.context, viewModel.clients.value ?: emptyList())
        view.list.adapter = adapter
        adapter.setOnItemClickListener(object : ClientRecyclerAdapter.OnClickListener {
            override fun onItemClick(v: View, position: Int) {
                val item = adapter.getItem(position)
                ClientDetailActivity.startActivity(activity, item)
            }
        })

        adapter.setLoadMoreListener(object : ClientRecyclerAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                val index: Int = adapter.itemCount
                loadMore(index)
                //Calling loadMore function in Runnable to fix the
                // java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling error
            }
        })

        view.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        return view
    }

    fun loadMore(index: Int = 0) {
        viewModel.fetchClients(index)
    }

    private val renderClients = Observer<List<Client>> {
        if (it.size == adapter.itemCount) {
            adapter.isMoreDataAvailable = false
        }
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        if (it) {
            hud = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)

            hud?.show()
        } else {
            hud?.dismiss()
        }
    }

    private val onMessageErrorObserver = Observer<String> {
        val toast = Toast.makeText(activity, it, Toast.LENGTH_LONG)
        toast.show()
    }

    companion object {

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            ClientListFragment().apply {

            }
    }
}