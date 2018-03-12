```LiveData```是一个可被观察的数据```holder```类。与常规的被观察者不同的是，
LiveData是生命周期感知的，意味着它respects应用其它组件的生命周期，
例如activities，fragments或者services。这种感知确保LiveData仅在
观察者们（activities等）处于active状态的时候才去更新它们。

## The advantages of using LiveData
## Work with LiveData objects

根据下面的步骤to work with LiveData对象：
1. 创建一个LiveData的实例，如LiveData<User>。通常都是在ViewModel类里关联LiveData，在其构造函数中完成LiveData的实例化
2. 创建一个观察者对象实现onChanged()方法，当LiveData持有的数据类的信息发生改变时onChanged()方法被触发。
    通常定义于UI控制类中（activity或者fragment）。
3. 调用observe()方法把观察者和被观察者关联起来。

注意：你可以不绑定一个LifecycleOwner（activity或者fragment）给观察者而是使用observeForever(Observer)方法。
在这种情况下，观察者被认为是一直处于active状态因此当数据类发生改变时，回调总是被触发而不再考虑观察者关联的activity或者
fragment的所处的生命周期状态，因为它没关联任何的应用组件。你可以调用removeObserver(Observer)以解注册这些观察者。
### Create LiveData objects

注意：确保在ViewModel对象中持有LiveData的实例而不是在activity或者fragment中，这么做有以下两点原因：
 - 避免臃肿的activities和fragments。这些UI控制器只需负责数据的显示而不需要持有数据。
 - UI和数据分离，当UI的配置发生改变时（如屏幕旋转），数据能够留存下来。
### Observe LiveData objects

在大多数情况下，注册观察者给LiveData代码最好在onCreate()方法当中执行，有以下两个原因：
 - 取保不重复地调用
 - 取保最早地注册以便之后数据类地更新都能被监测到。
译者注：当注册操作在数据对象被修改之后执行时也能通知到观察者，这点不知道是怎么做到的而且跟上面
第二点讲的好像也不是很吻合。以上是译者的疑问在后面的段落中得到的解答。
译者注：observe()操作会立马触发onChanged()方法，而不是说观察者能监听到注册之前数值变化的通知。见以下说明。

After observe() is called with nameObserver passed as parameter, onChanged() is immediately invoked providing the most recent value stored in mCurrentName.
If the LiveData object hasn't set a value in mCurrentName, onChanged() is not called.

observe()方法被调用之后，onChanged()立马被触发并传入mCurrentName的最新值（mCurrentName是在ViewModel中定义的LiveData对象）。
如果mCurrentName没有被设置则onChanged()方法不被触发。
### Update LiveData objects

LiveData没有提供任何可以更新数据的public方法，而MutableLiveData提供了setValue(T) and postValue(T)方法来更新数据。
ViewModel中持有MutableLiveData对象而对外只提供LiveData类型的媒介以使所有对数据更改只能发生在ViewModel中。
setValue(T)用于UI线程，postValue(T)用于其它线程
### Use LiveData with Room

The observe() method passes the fragment, which is an instance of LifecycleOwner, as the first argument. Doing so denotes that this observer is bound to the Lifecycle object associated with the owner, meaning:

 - 如果生命周期对象并非在激活状态，则当数据对象发生改变时观察者不被触发。
 - 生命周期对象被销毁后，观察者也自动被移除。
 
LiveData可以设计为单例模式供多个观察者（activity或者fragment）观察。
## Extend LiveData
## Transform LiveData

两个接口
 - Transformations.map()
 - Transformations.switchMap()

The UI component then needs to unregister from the previous LiveData object and register to the new instance each time it calls getPostalCode().
可以监控已监控对象的某些字段，或者已监控字段对应整个对象。这样观察者不需要在多个LiveData之间切换（解注册再重新注册）。
另一个好处是：
In addition, if the UI component is recreated, it triggers another call to the repository.getPostCode() method instead of using the previous call’s result.
### Create new transformations
## Merge multiple LiveData sources

MediatorLiveData可使观察者同时观察多个被观察者