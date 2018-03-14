ViewModel旨在以一种有意义的生命周期方式去保存和管理UI相关的数据。
ViewModel允许数据在配置发生改变后如屏幕旋转还能恢复回来。
译者注：接下来的几个段落介绍了为什么要从UI控制器解耦出数据处理的逻辑。

# Implement a ViewModel
Architecture Components provides ViewModel helper class for the UI controller that is responsible for preparing data for the UI. ViewModel objects are automatically retained during configuration changes so that data they hold is immediately available to the next activity or fragment instance.
架构组件提供了ViewModel帮助类来负责为UI准备数据。ViewModel对象会自动地被保留在配置发生变化期间，它们保留的数据可以立即在下一个UI控制器当中使用。

If the activity is re-created, it receives the same MyViewModel instance that was created by the first activity. When the owner activity is finished, the framework calls the ViewModel objects's onCleared() method so that it can clean up resources.
如果activity被重新创建，它将获得一个相同的ViewModel实例（由第一个activity所创建）。当创建ViewModel的activity被结束，framework将调用ViewModel的onCleared()方法，以使资源得到清理。

Caution: A ViewModel must never reference a view, Lifecycle, or any class that may hold a reference to the activity context.
警告：ViewModel绝对不能引用一个view，Lifecycle或者任何持有activity引用的类。

ViewModel objects can contain LifecycleObservers, such as LiveData objects. However ViewModel objects must never observe changes to lifecycle-aware observables, such as LiveData objects.
ViewModel绝对不能观察一个生命周期感知的被观察者，例如LiveData。
译者注：不能观察LiveData的话，是不是指不能有LiveData<LiveData<User>>这样的引用在ViewModel中出现？
译者注：什么这段把我绕晕了，不知道说的啥。LiveData是一个LifecycleObservers，但是LiveData不是被观察者吗？这里却又是一个观察者。

If the ViewModel needs the Application context, for example to find a system service, it can extend the AndroidViewModel class and have a constructor that receives the Application in the constructor, since Application class extends Context.
如果ViewModel需要一个应用上下文，可使它继承AndroidViewModel类，它有一个构造函数接收一个Application参数。

# The lifecycle of a ViewModel
![ViewModel的生命周期](../../../res/viewmodel-lifecycle.png)
ViewModel objects are scoped to the Lifecycle passed to the ViewModelProvider when getting the ViewModel. The ViewModel remains in memory until the Lifecycle it's scoped to goes away permanently: in the case of an activity, when it finishes, while in the case of a fragment, when it's detached.

ViewModel对象被限制在Lifecycle（ViewModelProvider创建ViewModel时所传递的一个参数）的范围内。ViewModel在Lifecycle的作用域永久地goes away之前会一直保留在内存当中。当Lifecycle是activity时，这个时刻是它被结束掉了；当Lifecycle是fragment时，这个时刻是它被detached了。

# Share data between fragments

In addition, both fragments must handle the scenario where the other fragment is not yet created or visible.
另外，这两个fragment还必须处理这样的场景，就是另一个fragment尚未被创建或者可见。
# Replacing Loaders with ViewModel