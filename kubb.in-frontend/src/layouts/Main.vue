<template>
  <body>
    <link rel="stylesheet" href="main.css" />

    <nav
      class="navbar navbar-expand-lg navbar-light bg-*"
      style="background-color: #defcf9"
    >
      <a class="navbar-brand" href="/">
        <img class="mr-3" src="photo.png" alt="" width="48" height="48" />
      </a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/main"
              >Home <span class="sr-only">(current)</span></a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" @click="logout()" href="#">Log out</a>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Subscription actions
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="/add">Add a new Subscription</a>
              <a class="dropdown-item" @click="deleteAllSubs()"
                >Delete all Subscriptions</a
              >
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled" href="#">Alpha Version</a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <input
            class="form-control mr-sm-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
          />
          <button class="btn btn-secondary my-2 my-sm-0" type="submit">
            Search
          </button>
        </form>
      </div>
    </nav>
    <main role="main" class="container">
      <div class="logo-box p-3 my-3 rounded box-shadow">
        <div class="lh-100">
          <h6 class="mb-0 text-black lh-100">Kubb.in</h6>
          <small>Saving you time and money since 2021</small>
        </div>
      </div>
      <slot />
    </main>
  </body>
</template>
<script>
import "bootstrap/dist/css/bootstrap.min.css";
export default {
  mounted() {
    if (typeof window !== `undefined`) {
      window.$ = require("jquery");
      require("bootstrap");
      const user = localStorage.getItem("user");
      const tokenJSON = JSON.parse(user);
      
    
    }
  },
  methods: {
    deleteAllSubs: () => {
      if(typeof window !== 'undefined'){
        let resp = prompt("Are You Sure? Type yes to continue");
        let token = JSON.parse(localStorage.getItem("user"));
        if (resp.toLowerCase() === "yes") {
          var myHeaders = new Headers();
          myHeaders.append("Authorization", `Bearer ${token.accessToken}`);

          var requestOptions = {
            method: "DELETE",
            headers: myHeaders,
            redirect: "follow",
          };

          fetch("https://kubb.in:8080/api/subscriptions/", requestOptions)
            .then((response) => response.text())
            .then((result) => window.location.reload())
            .catch((error) => console.log("error", error));
        }
      }      
    },
    logout: () => {
      localStorage.removeItem("user");
      window.location.href = "/login";
    },
  },
};

if (typeof window !== `undefined`) {
  const user = localStorage.getItem("user");
  const tokenJSON = JSON.parse(user);
}
</script>
<style>
</style>
