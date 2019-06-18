package com.github.adelina609.stackoverrelations.di.question.component

import com.github.adelina609.stackoverrelations.di.app.component.AppComponent
import com.github.adelina609.stackoverrelations.di.question.module.AnswerModule
import com.github.adelina609.stackoverrelations.di.question.scope.QuestionScope
import com.github.adelina609.stackoverrelations.di.question.module.PresenterModule
import com.github.adelina609.stackoverrelations.di.question.module.QuestionModule
import com.github.adelina609.stackoverrelations.di.question.scope.AnswerScope
import com.github.adelina609.stackoverrelations.ui.MainActivity
import com.github.adelina609.stackoverrelations.ui.detail.DetailsFragment
import com.github.adelina609.stackoverrelations.ui.list.ListFragment
import com.github.adelina609.stackoverrelations.ui.new_answer.NewAnswerFragment
import com.github.adelina609.stackoverrelations.ui.new_question.NewQuestionFragment
import com.github.adelina609.stackoverrelations.ui.notification.NotificationFragment
import com.github.adelina609.stackoverrelations.ui.profile.ProfileFragment
import com.github.adelina609.stackoverrelations.ui.profile.SharedPreferenceFragment
import com.github.adelina609.stackoverrelations.ui.auth.SignInActivity
import com.github.adelina609.stackoverrelations.ui.auth.SignUpActivity

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

    fun inject(signInActivity: SignInActivity)

    fun inject(signUpActivity: SignUpActivity)

    fun inject(profileFragment: ProfileFragment)

    fun inject(sharedPreferenceFragment: SharedPreferenceFragment)

    fun inject(notificationFragment: NotificationFragment)
}
