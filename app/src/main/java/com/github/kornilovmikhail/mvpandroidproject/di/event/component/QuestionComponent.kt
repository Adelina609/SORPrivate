package com.github.kornilovmikhail.mvpandroidproject.di.event.component

import com.github.kornilovmikhail.mvpandroidproject.di.app.component.AppComponent
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.AnswerModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.QuestionScope
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.QuestionModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.AnswerScope
import com.github.kornilovmikhail.mvpandroidproject.ui.MainActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListFragment

import dagger.Component

@QuestionScope
@AnswerScope
@Component(
    dependencies = [AppComponent::class],
    modules = [QuestionModule::class, PresenterModule::class, AnswerModule::class]
)
interface QuestionComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(listFragment: ListFragment)

    fun inject(detailsFragment: DetailsFragment)

//    fun inject(linksFragment: LinksFragment)
}
