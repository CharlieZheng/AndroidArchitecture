当需要preserve的数据十分复杂是，你可以把ViewModel，onSaveInstanceState()和本地存储结合起来使用。

# Manging simpler cases: onSaveInstanceState()

Instead of storing complex objects here, persist the complex objects in local storage and store a unique ID for these objects in onSaveInstanceState().

不要直接存储这些复杂对象，而是持久化这些复杂对象并用onSaveInstanceState()方法保存一个唯一的ID对应这一个个对象
# Managing more complex states: divide and conquer
# Restoring complex states: reassembling the pieces