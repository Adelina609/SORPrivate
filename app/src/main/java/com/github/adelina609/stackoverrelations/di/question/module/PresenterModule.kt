package com.github.adelina609.stackoverrelations.di.question.module

import android.content.SharedPreferences
import com.github.adelina609.stackoverrelations.data.repository.AnswersRepo
import com.github.adelina609.stackoverrelations.data.repository.NotificationNetworkRepo
import com.github.adelina609.stackoverrelations.data.repository.QuestionsRepo
import com.github.adelina609.stackoverrelations.di.question.scope.QuestionScope
import com.github.adelina609.stackoverrelations.presenter.*
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class PresenterModule {
    @Provides
    @QuestionScope
    fun provideDetailPresenter(
        questionsRepo: QuestionsRepo, router: Router,
        answersRepo: AnswersRepo
    ): DetailPresenter =
        DetailPresenter(questionsRepo, router, answersRepo)

    @Provides
    @QuestionScope
    fun provideMainPresenter(questionsRepo: QuestionsRepo, router: Router): ListPresenter =
        ListPresenter(questionsRepo, router)

    @Provides
    @QuestionScope
    fun provideNewQuestionPresenter(questionsRepo: QuestionsRepo, router: Router): NewQuestionPresenter =
        NewQuestionPresenter(questionsRepo, router)

    @Provides
    @QuestionScope
    fun provideNewAnswerPresenter(
        answersRepo: AnswersRepo, router: Router
        , sharedPreferences: SharedPreferences
    ): NewAnswerPresenter =
        NewAnswerPresenter(answersRepo, router, sharedPreferences)

    @Provides
    @QuestionScope
    fun provideSignInPresenter(router: Router): SignInPresenter =
        SignInPresenter(router)

    @Provides
    @QuestionScope
    fun provideSignUprPresenter(): SignUpPresenter =
        SignUpPresenter()

    @Provides
    @QuestionScope
    fun provideMainActivityPresenter(router: Router): MainPresenter = MainPresenter(router)

    @Provides
    @QuestionScope
    fun provideProfilePresenter(
        questionsRepo: QuestionsRepo, router: Router,
        answersRepo: AnswersRepo, sharedPreferences: SharedPreferences
    ): ProfilePresenter =
        ProfilePresenter(questionsRepo, router, answersRepo, sharedPreferences)

    @Provides
    @QuestionScope
    fun provideSharedPrefsPresenter(router: Router, sharedPreferences: SharedPreferences) =
        SharedPreferencePresenter(router, sharedPreferences)

    @Provides
    @QuestionScope
    fun provideNotifPresenter(
        notificationNetworkRepo: NotificationNetworkRepo,
        router: Router
    ) = NotificationPresenter(notificationNetworkRepo, router)

}
