import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

export default new VueRouter({
    mode: 'history', // 해쉬값 제거 방식
    routes: [
        {
            path: '/',
            redirect: 'main.do',
        }, {
            path: '/main.do',
            component: () => import('../pages/mainPageView.vue'),
        }, {
            path: '/login.do',
            component: () => import('../pages/loginView.vue'),
        }, {
            path: '/register.do',
            component: () => import('../pages/userRegisterView.vue'),
        }
    ]
});