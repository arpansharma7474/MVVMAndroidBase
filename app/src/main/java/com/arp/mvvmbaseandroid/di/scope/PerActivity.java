package com.arp.mvvmbaseandroid.di.scope;

import javax.inject.Scope;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Identifies a type that the injector only instantiates once. Not inherited.
 *
 * @see Scope @Scope
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface PerActivity {}
