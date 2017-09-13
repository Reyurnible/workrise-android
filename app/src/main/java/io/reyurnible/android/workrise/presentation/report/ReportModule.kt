package io.reyurnible.android.workrise.presentation.report

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module
abstract class ReportModule {

    @Binds
    @IntoMap
    @FragmentKey(ReportFragment::class)
    abstract fun bindReportPresenter(presenter: ReportPresenter): ReportPresenter

    @ContributesAndroidInjector
    abstract fun contributeReportFragment(): ReportFragment
}
