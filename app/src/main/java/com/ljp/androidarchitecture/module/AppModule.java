package com.ljp.androidarchitecture.module;

import dagger.Module;

@Module(includes = ViewModelModule.class)
//创建的Module都可以写在这里被AppModule包括，也可以写在AppComponent中的modules={...}里面与AppMoudle同级
public class AppModule {
}