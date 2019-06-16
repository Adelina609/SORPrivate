//package com.github.adelina609.stackoverrelations.di.event.component
//
//import com.github.adelina609.stackoverrelations.di.app.component.AppComponent
//import com.github.adelina609.stackoverrelations.di.event.module.EventModule
//import com.github.adelina609.stackoverrelations.di.event.module.PresenterModule
//import com.github.adelina609.stackoverrelations.di.event.scope.EventScope
//import com.github.adelina609.stackoverrelations.ui.MainActivity
//import com.github.adelina609.stackoverrelations.ui.detail.DetailsFragment
//import com.github.adelina609.stackoverrelations.ui.links.LinksFragment
//import com.github.adelina609.stackoverrelations.ui.list.ListFragment
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
