<template>
 <div id="appp">
    <div id="login">
<div id="description">
        <h1>Kubb.in</h1>
        <p>
          By registering, you are willing to save time and be more organized as
          you will have complete control over your micro transactions that come
          from subscriptions and renewals
        </p>
        <p>
          <a href="/login">To the login page</a>
        </p>
      </div>
      <div id="form">
      <form @submit.prevent="handleRegister">
        <div v-if="!successful">
          
            <label for="username">Username</label>
            <input
              v-model="user.username"
              v-validate="'required|min:3|max:20'"
              type="text"
              class="form-control"
              name="username"
            />
            <div
              v-if="submitted && errors.has('username')"
              class="alert-danger"
            >
              {{ errors.first("username") }}
            </div>
         
          
            <label for="email">Email</label>
            <input
              v-model="user.email"
              v-validate="'required|email|max:50'"
              type="email"
              class="form-control"
              name="email"
            />
            <div v-if="submitted && errors.has('email')" class="alert-danger">
              {{ errors.first("email") }}
            </div>
      
            <label for="password">Password</label>
            <input
              v-model="user.password"
              v-validate="'required|min:8|max:40'"
              type="password"
              class="form-control"
              name="password"
            />
            <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >
              {{ errors.first("password") }}
            </div>

            <button class="btn btn-primary btn-block">Sign Up</button>
   
        </div>
      </form>
            <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
  </div>

    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
const Auth = namespace("Auth");

@Component
export default class Register extends Vue {
  private user: any = { username: "", email: "", password: "" };

  private submitted: boolean = false;
  private successful: boolean = false;
  private message: string = "";

  @Auth.Getter
  private isLoggedIn!: boolean;

  @Auth.Action
  private register!: (data: any) => Promise<any>;

  mounted() {
    if (this.isLoggedIn) {
      this.$router.push("/profile");
    }
  }

  handleRegister() {
    this.message = "";
    this.submitted = true;

    this.$validator.validate().then((isValid) => {
      if (isValid) {
        this.register(this.user).then(
          (data) => {
            this.message = data.message;
            this.successful = true;
          },
          (error) => {
            this.message = error;
            this.successful = false;
          }
        );
      }
    });
  }
}
</script>

<style scoped>
:root {
  --modal-duration: 1s;
  --primary-color: #defcf9;
  --secondary-color: #42dbcc;
}

* {
  box-sizing: border-box;
  font-family: "Nunito", sans-serif;
}

html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
  width: 100%;
}

div#appp {
  width: 100%;
  height: 100vh;
}

div#app div#login {
  align-items: center;
  background-color: var(--primary-color);
  display: flex;
  justify-content: center;
  width: 100%;
  height: 100%;
}

div#app div#login div#description {
  background-color: #ffffff;
  width: 280px;
  padding: 35px;
}

div#app div#login div#description h1,
div#app div#login div#description p {
  margin: 0;
}

div#app div#login div#description p {
  font-size: 0.8em;
  color: #95a5a6;
  margin-top: 10px;
}

div#app div#login div#form {
  background-color: var(--secondary-color);
  border-radius: 5px;
  box-shadow: 0px 0px 30px 0px #666;
  color: #ecf0f1;
  width: 260px;
  padding: 35px;
}

div#app div#login div#form label,
div#app div#login div#form input {
  outline: none;
  width: 100%;
}

div#app div#login div#form label {
  color: #000;
  font-size: 0.8em;
}

div#app div#login div#form input {
  background-color: transparent;
  border: none;
  color: #ecf0f1;
  font-size: 1em;
  margin-bottom: 20px;
}

div#app div#login div#form ::placeholder {
  color: #ecf0f1;
  opacity: 1;
}

div#app div#login div#form button {
  background-color: #ffffff;
  color: black;
  cursor: pointer;
  border: none;
  padding: 10px;
  transition: background-color 0.2s ease-in-out;
  width: 100%;
}

div#app div#login div#form button:hover {
  background-color: #eeeeee;
}

a {
  text-decoration: none;
  color: #95a5a6;
}

a:visited {
  text-decoration: none;
  color: #95a5a6;
}

.error {
  color: var(--primary-color);
  margin: 0;
  display: none;
  transition: 1s ease-in-out;
}

@media screen and (max-width: 600px) {
  div#app div#login {
    align-items: unset;
    background-color: unset;
    display: unset;
    justify-content: unset;
  }

  div#app div#login div#description {
    margin: 0 auto;
    max-width: 350px;
    width: 100%;
  }

  div#app div#login div#form {
    border-radius: unset;
    box-shadow: unset;
    width: 100%;
  }

  div#app div#login div#form form {
    margin: 0 auto;
    max-width: 280px;
    width: 100%;
  }
}
</style>
