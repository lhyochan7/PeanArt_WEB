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
        }, {
            path: '/exhib/:id/detail.do',
            component: () => import('../pages/exhibitDetailView.vue'),
        }, {
            path: '/exhib/list.do',
            component: () => import('../pages/exhibitListView.vue'),
        }, {
            path: '/exhib/register.do',
            component: () => import('../pages/exhibitRegisterView.vue'),
        }, {
            path: '/myPage.do',
            component: () => import('../pages/myPageView.vue'),
        }
    ]
});