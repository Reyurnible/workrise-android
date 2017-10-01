package io.reyurnible.android.workrise.presentation.reportsetting

import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ReportSettingModule {

    @Binds
    @IntoMap
    @ActivityKey(ReportSettingActivity::class)
    abstract fun bindReportSettingPresenter(presenter: ReportSettingPresenter): ReportSettingPresenter

    @ContributesAndroidInjector
    abstract fun contributeReportSettingFragment(): ReportSettingFragment

    @ContributesAndroidInjector
    abstract fun contributeReportSettingActivity(): ReportSettingActivity


}
