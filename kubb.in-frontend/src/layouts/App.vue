<template>
  <div id="app">
     <nav
      class="navbar navbar-expand-lg navbar-light bg-*"
      style="background-color: #defcf9"
    >
      <a class="navbar-brand" href="/">
        <img class="mr-3" src="../assets/photo.png" alt="" width="48" height="48" />
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
              <a class="dropdown-item" href="/manage">Manage all Subscriptions</a>
            </div>
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
              Account actions
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">


          <router-link v-if="currentUser" to="/profile" class="dropdown-item">
           
               <font-awesome-icon icon="user" /> Profile {{ currentUser.username }}
          </router-link>

      
          <a v-if="currentUser" class="dropdown-item" href @click.prevent="logOut">
               Log Out
          </a>
 

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

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script lang="ts">
import UserService from "@/services/UserService";
import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
const Auth = namespace("Auth");

@Component
export default class AppLayout extends Vue{

  @Auth.State("user")
  private currentUser!: any;

  @Auth.Action
  private signOut!: () => void;

  deleteAllSubs(){
      UserService.deleteAllSubs().then((data)=> {
          this.$router.go(0)
      });
      
  }

  get showAdminBoard(): boolean {
    if (this.currentUser && this.currentUser.roles) {
      return this.currentUser.roles.includes("ROLE_ADMIN");
    }

    return false;
  }

  get showModeratorBoard(): boolean {
    if (this.currentUser && this.currentUser.roles) {
      return this.currentUser.roles.includes("ROLE_MODERATOR");
    }

    return false;
  }

  logOut() {
    this.signOut();
    this.$router.push("/login");
  }
}
</script>


<style>
body{
    transition: transform 0.3s ease;
}
body.show-nav{
    transform: translate(200px);
}

:root {
  --modal-duration: 1s;
  --primary-color: #defcf9;
  --secondary-color: #42dbcc;
}

body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.25;
  background-color: #fafafa;
}

a {
  color: black;
}

.logo-box {
  /*d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded box-shadow*/
  display: flex;
  align-items: center;
  background-color: var(--primary-color);
  color: black;
}
.table-responsive {
  display: table;
}
table {
  border: 1px solid var(--primary-color);
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
}

table caption {
  font-size: 1.5em;
  margin: 0.25em 0 0.75em;
}

table tr {
  background: var(--primary-color);
  border: 1px solid var(--secondary-color);
  padding: 0.35em;
}

table th,
table td {
  padding: 0.625em;
  text-align: center;
}

table th {
  font-size: 0.85em;
  letter-spacing: 0.1em;
  text-transform: uppercase;
}

table td img {
  text-align: center;
}
@media screen and (max-width: 770px) {
  
  table {
    border: 0;
  }

  .table-responsive {
    display: grid;
  }

  table caption {
    font-size: 1.3em;
  }

  table thead {
    display: none;
  }

  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: 0.625em;
  }

  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: 0.8em;
    text-align: right;
  }

  table td:before {
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }

  table td:last-child {
    border-bottom: 0;
  }
}
@media screen and (min-width: 770px) and (max-width: 995px) {
  
  .table-responsive {
    display: block;
  }
}


label {
  margin-top: 10px;
}

button {
  align-self: center;
}
a {
  color: black;
}


body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.25;
  background-color: #fafafa;
}
.alert {
  margin: 15px;
}
#newsub ,.form-group{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;

}



input, select{
  width: 40%;
}
#submitBtn{
  width: 100%;
}
.form-group{
  background-color: #fafafa;
}
.titleAdd {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

label {
  margin-top: 10px;
}

button {
  align-self: center;
}
a {
  color: black;
}


@media screen and (max-width: 770px) {
  input, select{
    width: 90%;
  }
}

@media screen and (min-width: 770px) and (max-width: 995px)  {
  input, select{
    width: 70%;
  }
  }
</style>