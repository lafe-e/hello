var indexApp = new Vue({
    el:'#indexApp',
    data:{},
    methods:{},

    //也有一些其它的钩子，在实例生命周期的不同阶段被调用，如 mounted、updated 和 destroyed。生命周期钩子的 this 上下文指向调用它的 Vue 实例。
    // 比如 created 钩子可以用来在一个实例被创建之后执行代码：
    created: function() {
        // `this` 指向 vm 实例
        console.log('a is: ')
    }
});