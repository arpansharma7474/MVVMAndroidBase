package com.arp.mvvmbaseandroid.di.Components

import com.arp.mvvmbaseandroid.di.Modules.ActivityModule
import com.arp.mvvmbaseandroid.di.scope.PerActivity
import dagger.Component

/**
 * This class is manage the Activity level dependencies
 */

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {


}
