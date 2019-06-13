//package com.github.kornilovmikhail.mvpandroidproject.di.event.component
//
//import com.github.kornilovmikhail.mvpandroidproject.di.app.component.AppComponent
//import com.github.kornilovmikhail.mvpandroidproject.di.event.module.EventModule
//import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
//import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.EventScope
//import com.github.kornilovmikhail.mvpandroidproject.ui.MainActivity
//import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsFragment
//import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksFragment
//import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListFragment
//import dagger.Component
//
//@EventScope
//@Component(
//    dependencies = [AppComponent::class],
//    modules = [EventModule::class, PresenterModule::class]
//)
//interface EventComponent {
//    fun inject(mainActivity: MainActivity)
//
//    fun inject(listFragment: ListFragment)
//
//    fun inject(detailsFragment: DetailsFragment)
//
//    fun inject(linksFragment: LinksFragment)
//}
