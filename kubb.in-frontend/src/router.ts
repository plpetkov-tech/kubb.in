import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Login from "@/components/Login.vue";
import Register from "@/components/Register.vue";
import AppLayout from "@/layouts/App.vue";
import Manage from "@/components/Manage.vue";

import FrontPageLayout from "@/layouts/FrontPage.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    component: FrontPageLayout,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/profile",
    component: AppLayout,
    children: [
      {
        path: "",
        component: () => import("./components/Profile.vue"),
      },
    ],
    name: "profile",
    // lazy-loaded
  },
  {
    path: "/edit/:id",
    component: AppLayout,
    children: [
      {
        path: "",
        component: () => import("./components/Edit.vue"),
      },
    ],
    name: "edit",
    // lazy-loaded
  },
  {
    path: "/main",
    component: AppLayout,
    children: [
      {
        path: "",
        component: () => import("./components/Main.vue"),
      },
    ],
    name: "main",
    // lazy-loaded
  },
  {
    path: "/add",
    component: AppLayout,
    children: [
      {
        path: "",
        component: () => import("./components/Add.vue"),
      },
    ],
    name: "add",
    // lazy-loaded
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

// router.beforeEach((to, from, next) => {
//   const publicPages = ['/login', '/register', '/home'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem('user');

//   // trying to access a restricted page + not logged in
//   // redirect to login page
//   if (authRequired && !loggedIn) {
//     next('/login');
//   } else {
//     next();
//   }
// });

export default router;
