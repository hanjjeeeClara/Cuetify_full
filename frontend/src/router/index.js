import Vue from 'vue'
import VueRouter from 'vue-router'
import TableApp from '../components/TableApp'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: TableApp
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
