//package com.github.adelina609.stackoverrelations.ui.links
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ProgressBar
//import android.widget.Toast
//import com.arellomobile.mvp.MvpAppCompatFragment
//import com.arellomobile.mvp.presenter.InjectPresenter
//import com.arellomobile.mvp.presenter.ProvidePresenter
//import com.github.adelina609.stackoverrelations.App
//import com.github.adelina609.stackoverrelations.R
//import com.github.adelina609.stackoverrelations.data.entity.Event
//import com.github.adelina609.stackoverrelations.di.event.component.DaggerEventComponent
//import com.github.adelina609.stackoverrelations.di.event.module.QuestionModule
//import com.github.adelina609.stackoverrelations.di.event.module.PresenterModule
//import com.github.adelina609.stackoverrelations.presenter.LinksPresenter
//import kotlinx.android.synthetic.main.fragment_links.*
//import javax.inject.Inject
//
//class LinksFragment : MvpAppCompatFragment(), LinksView {
//    @Inject
//    @InjectPresenter
//    lateinit var linksPresenter: LinksPresenter
//
//    private var position = 0
//
//    @ProvidePresenter
//    fun getPresenter(): LinksPresenter = linksPresenter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        DaggerEventComponent.builder()
//            .appComponent(App.getAppComponents())
//            .eventModule(QuestionModule())
//            .presenterModule(PresenterModule())
//            .build()
//            .inject(this)
//        super.onCreate(savedInstanceState)
//        position = arguments?.getInt(EXTRA_POSITION) as Int
//        setHasOptionsMenu(true)
//        linksPresenter.getLinks(position)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? = inflater.inflate(R.layout.fragment_links, container, false)
//
//    override fun displayEvent(event: Event) {
//        tv_link_article.text = event.links.linkArticle ?: getString(R.string.none)
//        tv_link_reddit.text = event.links.linkReddit ?: getString(R.string.none)
//        tv_link_wiki.text = event.links.linkWikipedia ?: getString(R.string.none)
//    }
//
//    override fun displayError() {
//        Toast.makeText(context, getString(R.string.server_events_error), Toast.LENGTH_SHORT).show()
//    }
//
//    override fun showProgressBar() {
//        links_progressBar.visibility = ProgressBar.VISIBLE
//    }
//
//    override fun hideProgressBar() {
//        links_progressBar.visibility = ProgressBar.INVISIBLE
//    }
//
//    companion object {
//        private const val EXTRA_POSITION = "POSITION"
//
//        fun getInstance(position: Int): LinksFragment {
//            val linksFragment = LinksFragment()
//            val args = Bundle()
//            args.putInt(EXTRA_POSITION, position)
//            linksFragment.arguments = args
//            return linksFragment
//        }
//    }
//}
