<template>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Nunito"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
    />
    <link rel="stylesheet" href="login.css" />
    <div id="login">
      <div id="description">
        <h1>Kubb.in</h1>
        <p>
          By logging in you agree to the ridiculously long terms that you didn't
          bother to read. If you don't have an account register
          <a href="/register">here</a>
        </p>
      </div>
      <div id="form">
        <form @submit.prevent="doLogin">
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            v-model="username"
            placeholder="username"
            autocomplete="off"
          />

          <label for="password">Password</label>&nbsp;
          <i
            class="fas"
            :class="[passwordIcon]"
            @click="hidePassword = !hidePassword"
          ></i>
          <input
            :type="passwordType"
            id="password"
            v-model="password"
            placeholder="**********"
          />

          <button type="submit">Log in</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Login Page",
  },
  data() {
    return {
      username: "",
      password: "",
      hidePassword: true,
      jsonRequest: {},

    };
  },
  mounted() {
if (typeof window !== `undefined`) {
  const user = localStorage.getItem("user");
  const tokenJSON = JSON.parse(user);
      if (user != null) {
      if (user.includes("ROLE_USER")) {
        window.location.href = "/main";
      }
    }
  const redirectWithToken = (token) =>{
  if (token !== undefined) {
    localStorage.setItem("user", JSON.stringify(token));
    window.location.href = "/main";
  }
}



}
  },
  computed: {
    passwordType() {
      return this.hidePassword ? "password" : "text";
    },
    passwordIcon() {
      return this.hidePassword ? "fa-eye" : "fa-eye-slash";
    },
  },
  methods: {
    doLogin() {
      if(typeof window !== 'undefined'){
        sendLoginRequest(`${username.value}`, `${password.value}`);
      }
    },
    checkIfLogged() {
      console.log("jsontoken ", data.user);
    },
  },
};
const redirectWithToken = (token) =>{
  if(typeof window !== 'undefined'){
      if (token !== undefined) {
    localStorage.setItem("user", JSON.stringify(token));
    window.location.href = "/main";
  }
  }
}
function sendLoginRequest(u, p) {
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  var raw = JSON.stringify({ username: u, password: p });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("https://kubb.in:8080/api/auth/signin", requestOptions)
    .then((response) => response.json())
    .then((result) =>
      result.error
        ? console.log("wrong password todo")
        : redirectWithToken(result)
    )
    .catch((error) => console.log("error", error));
}
</script>