const c1 = () => import(/* webpackChunkName: "page--src--pages--register-vue" */ "/home/plpetkov/Development/kubb.in/kubb.in-frontend/src/pages/Register.vue")
const c2 = () => import(/* webpackChunkName: "page--src--pages--main-vue" */ "/home/plpetkov/Development/kubb.in/kubb.in-frontend/src/pages/Main.vue")
const c3 = () => import(/* webpackChunkName: "page--src--pages--login-vue" */ "/home/plpetkov/Development/kubb.in/kubb.in-frontend/src/pages/Login.vue")
const c4 = () => import(/* webpackChunkName: "page--src--pages--add-vue" */ "/home/plpetkov/Development/kubb.in/kubb.in-frontend/src/pages/Add.vue")
const c5 = () => import(/* webpackChunkName: "page--node-modules--gridsome--app--pages--404-vue" */ "/home/plpetkov/Development/kubb.in/kubb.in-frontend/node_modules/gridsome/app/pages/404.vue")
const c6 = () => import(/* webpackChunkName: "page--src--pages--index-vue" */ "/home/plpetkov/Development/kubb.in/kubb.in-frontend/src/pages/Index.vue")

export default [
  {
    path: "/register/",
    component: c1
  },
  {
    path: "/main/",
    component: c2
  },
  {
    path: "/login/",
    component: c3
  },
  {
    path: "/add/",
    component: c4
  },
  {
    name: "404",
    path: "/404/",
    component: c5
  },
  {
    name: "home",
    path: "/",
    component: c6
  },
  {
    name: "*",
    path: "*",
    component: c5
  }
]
