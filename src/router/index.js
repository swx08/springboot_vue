import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/Manage'),
    redirect:'/Home',
    children:[
      {path: 'Home', name: 'Home', component: () => import('../views/Home')},
      {path: 'User', name: 'User', component: () => import('../views/User')},
      {path: 'personal', name: 'Personal', component: () => import('../views/Personal')},
      {path: 'Role', name: 'Role', component: () => import('../views/Role')},
      {path: 'Menu', name: 'Menu', component: () => import('../views/Menu')}
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login')
  },
  {
    path: '/Regist',
    name: 'Regist',
    component: () => import(/* webpackChunkName: "about" */ '../views/Regist')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
