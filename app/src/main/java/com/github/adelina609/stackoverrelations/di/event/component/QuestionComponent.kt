package com.github.adelina609.stackoverrelations.di.event.component

import com.github.adelina609.stackoverrelations.di.app.component.AppComponent
import com.github.adelina609.stackoverrelations.di.event.module.AnswerModule
import com.github.adelina609.stackoverrelations.di.event.scope.QuestionScope
import com.github.adelina609.stackoverrelations.di.event.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.event.module.QuestionModule
import com.github.adelina609.stackoverrelations.di.event.scope.AnswerScope
import com.github.adelina609.stackoverrelations.ui.MainActivity
import com.github.adelina609.stackoverrelations.ui.detail.DetailsFragment
import com.github.adelina609.stackoverrelations.ui.list.ListFragment
import com.github.adelina609.stackoverrelations.ui.new_answer.NewAnswerFragment
import com.github.adelina609.stackoverrelations.ui.new_question.NewQuestionFragment
import com.itis.android.firebasesimple.activity.SignInFragment
import com.itis.android.firebasesimple.activity.SignUpFragment

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

    fun inject(newQuestionFragment: NewQuestionFragment)

    fun inject(newAnswerFragment: NewAnswerFragment)

    fun inject(signInFragment: SignInFragment)

    fun inject(signUpFragment: SignUpFragment)

//    fun inject(linksFragment: LinksFragment)
}
