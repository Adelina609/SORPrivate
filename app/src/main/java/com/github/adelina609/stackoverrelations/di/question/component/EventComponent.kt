//package com.github.adelina609.stackoverrelations.di.question.component
//
//import com.github.adelina609.stackoverrelations.di.app.component.AppComponent
//import com.github.adelina609.stackoverrelations.di.question.module.EventModule
//import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
//import com.github.adelina609.stackoverrelations.di.question.scope.EventScope
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
