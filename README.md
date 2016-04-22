# RxJava+Okhttp的练手项目
* ``zip``
这是zip方法的核心代码：
try {
      Subscriber<? super T> st = hook.onLift(operator).call(o);
                    try {
                        // new Subscriber created and being subscribed with so 'onStart' it
                        st.onStart();
                        onSubscribe.call(st);
                    }
会重新生成一个新的Subscriber对象，把两个Observable的东西进行合并                  
* 
* 
* 
* 
* 
* 
* 
* 
* 