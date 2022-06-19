import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

export default new VueRouter({
    mode: 'history', // 해쉬값 제거 방식
    routes: [
        {
            path: '/',
            redirect: 'main',
        }, {
            path: '/main',
            component: () => import('../pages/mainPageView.vue'),
        }, {
            path: '/login',
            component: () => import('../pages/loginView.vue'),
        }, {
            path: '/register',
            component: () => import('../pages/userRegisterView.vue'),
        }, {
            path: '/exhib/:id/detail',
            component: () => import('../pages/exhibitDetailView.vue'),
            name: 'exhibitDetailView'
        },{
            path: '/exhib/:id/modify',
            component: () => import('../pages/exhibitRegisterView.vue'),
            name:'exhibitRegisterView',
            props: true,
        }, {
            path: '/exhib/list',
            component: () => import('../pages/exhibitListView.vue'),
        }, {
            path: '/exhib/register',
            component: () => import('../pages/exhibitRegisterView.vue'),
        }, {
            path: '/myPage',
            component: () => import('../pages/myPageView.vue'),
        }, {
            path: '/airecommend',
            component: () => import('../pages/aiRecommendView.vue')
        }
    ]
});