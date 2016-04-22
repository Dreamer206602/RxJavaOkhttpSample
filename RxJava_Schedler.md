#  线程控制 Scheduler的API

*  Scheduler.immediate():直接在当线程中运行，相当于不指定线程。这是默认的Scheduler。
* Scheduler.newThread():总是启动新的线程，并在新线程中执行操作
* Scheduler.io(): I/O操作(读写文件，读写数据库，网络信息交互等)所使用的Scheduler。行为模式和newThread()差不多，区别
在于io()的内部实现是用一个无数量上线的线程池，可以重用空闲的线程池，因此多数情况下io()比newThread()更有效。
不要把计算工作放在io()中，可以避免不必要的线程。
* Scheduler.computation():计算所使用的Scheduler.这个计算指的是CPU密集型计算，即不会被I/O等操作限制性能的操作
，例如图形的计算。这个Scheduler使用的固定的线程池，大小为CPU核数。不要把I/O操作放在conputation()中，否则I/O操作的等待会浪费CPU
* AndroidScheduler.mainThread():它指的是将在Android主线程运行。

## 切记切记： subscribeOn()和observeOn()的使用顺序：
*   subscribeOn():指定subscribe()所发生的线程，即Observable.OnSubscribe被激活时所处的线程。或者叫做事件产生的线程。
*   observeOn()：指定的是Subscriber所运行的线程。或者叫做事件消费线程。