package com.github.kornilovmikhail.mvpandroidproject.di.event.module

import com.github.kornilovmikhail.mvpandroidproject.data.repository.AnswersRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repository.QuestionsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.QuestionScope
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
//import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import com.github.kornilovmikhail.mvpandroidproject.presenter.ListPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class PresenterModule {
    @Provides
    @QuestionScope
    fun provideDetailPresenter(questionsRepo: QuestionsRepo, router: Router,
                               answersRepo: AnswersRepo): DetailPresenter =
        DetailPresenter(questionsRepo, router, answersRepo)

//    @Provides
//    @QuestionScope
//    fun provideLinksPresenter(questionsRepo: QuestionsRepo): LinksPresenter =
//        LinksPresenter(questionsRepo)

    @Provides
    @QuestionScope
    fun provideMainPresenter(questionsRepo: QuestionsRepo, router: Router): ListPresenter =
        ListPresenter(questionsRepo, router)
}
