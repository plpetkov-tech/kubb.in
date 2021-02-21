<template>
  <div>
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>we are kubb.in</title>

      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css"
        integrity="sha256-mmgLkCYLUQbXn0B1SRqzHar6dCnv9oZFPEC1g1cwlkk="
        crossorigin="anonymous"
      />
      <link rel="stylesheet" href="style.css" />
    </head>

    <nav>
      <div class="logo">
        <img src="/photo.png" alt="kubbin" />
      </div>
      <ul>
        <li><a href="/">Home</a></li>
        <li>
          <a href="/login"
            >To the application</a
          >
        </li>
        <li><a href="https://www.plamenpetkovonline.com/#contact">Contact</a></li>
        <li><a href="https://plamenpetkovonline.com">The creator</a></li>
      </ul>
    </nav>
    <header>
      <button id="toggle" class="toggle">
        <i class="fa fa-bars"></i>
      </button>
      <h1>we are kubb.in</h1>
      <img src="/source.gif" alt="grooving" />

      <p>Manage your subscriptions and renewals from one place!</p>

      <button class="cta-btn" @click="goToRegistration()" id="open">Sign Up!</button>
    </header>
    <slot />
  </div>
</template>

<static-query>
query {
  metadata {
    siteName
  }
}
</static-query>
<script>
export default {
  methods:{
   goToRegistration : ()=> {
  window.location.href = '/register'
}
  },
  data() {
    return {
      toggle: document.getElementById("toggle"),
      close: document.getElementById("close"),
      open: document.getElementById("open"),
      modal: document.getElementById("modal"),
    };
  },
  mounted() {
    toggle.addEventListener("click", () =>
      document.body.classList.toggle("show-nav")
    );
  },
};


</script>
<style>
body {
  font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto,
    "Helvetica Neue", Arial, sans-serif;
  margin: 0;
  padding: 0;
  line-height: 1.5;
}

.layout {
  max-width: 760px;
  margin: 0 auto;
  padding-left: 20px;
  padding-right: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  height: 80px;
}

.nav__link {
  margin-left: 20px;
}
</style>
