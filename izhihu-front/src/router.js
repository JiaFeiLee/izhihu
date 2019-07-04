import Vue from 'vue'
import Router from 'vue-router'
// import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home/index'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('./views/Register.vue')
    },
    // {
    //   path: '/home',
    //   name: 'home',
    //   component: () => import('./views/Home.vue')
    // },
    {
      path: '/home',
      name: 'home',
      component: () => import('./views/Home.vue'),
      redirect: '/home/index',
      children: [
        {
          path: '/home/index',
          menuName: 'index',
          component: () => import('./views/index/Index.vue')
        },
        {
          path: '/home/discovery',
          menuName: 'discovery',
          component: () => import('./views/discovery/Discovery.vue')
        },
        {
          path: '/home/topic',
          menuName: 'topic',
          component: () => import('./views/topic/Topic.vue')
        },
        {
          path: '/home/topicDetail/:topicid',
          menuName: 'topicDetail',
          component: () => import('./views/topic/TopicDetail.vue')
        },
        {
          path: '/home/favorite',
          menuName: 'favorite',
          component: () => import('./views/personal/Favorite.vue')
        },
        {
          path: '/home/favoriteDetail',
          menuName: 'favoriteDetail',
          component: () => import('./views/personal/FavoriteDetail.vue')
        },
        {
          path: '/home/people/:userid',
          menuName: 'people',
          component: () => import('./views/personal/People.vue')
        },
        {
          path: '/home/question/:quesid',
          menuName: 'question',
          component: () => import('./views/personal/Question.vue')
        },
        {
          path: '/home/editarticle',
          menuName: 'editarticle',
          component: () => import('./components/index/Editarticle.vue')
        },
        {
          path: '/home/editquestion',
          menuName: 'editquestion',
          component: () => import('./components/index/Editquestion.vue')
        }
      ]
    }
  ]
})
