<template>
<ClientOnly>
  <div>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Nunito"
    />
    <link rel="stylesheet" href="login.css" />
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
        <form @submit.prevent="doRegister">
          <p class="error" id="usernameErr">error</p>
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            v-model="username"
            placeholder="username"
            autocomplete="off"
          />
          <p class="error" id="emailErr">error</p>

          <label for="email">Email</label>
          <input
            type="text"
            id="email"
            v-model="email"
            placeholder="email@domain.com"
            autocomplete="off"
          />
          <p class="error" id="passwordErr">error</p>

          <label for="password">Password</label>
           <font-awesome-icon
             :icon='passwordIcon'
            @click="hidePassword()"
          ></font-awesome-icon>
          <input
            :type="passwordType"
            id="password"
            v-model="password"
            placeholder="**********"
          />
          <p class="error" id="cpasswordErr">error</p>
          <label for="cpassword">Confirm Password</label>
          <font-awesome-icon
            :icon='passwordIcon'
            @click="hidePassword()"
          ></font-awesome-icon>
          <input
            :type="passwordType"
            id="cpassword"
            v-model="cpassword"
            placeholder="**********"
          />

          <button type="submit">Register</button>
        </form>
      </div>
    </div>
  </div>
  </ClientOnly>
</template>

<script>
export default {
  metaInfo: {
    title: "Register with us",
  },
  mounted() {
    if (typeof window !== `undefined`) {
    window.$ = require("jquery");
    }
  },
  data() {
    return {
      passwordType:'password',
      username: "",
      password: "",
      email: "",
      cpassword: "",
      passwordIcon:'eye'
    };
  },
  methods: {
    hidePassword(){
      this.passwordType == 'text' ? this.passwordType = 'password'  : this.passwordType = 'text'
      this.passwordIcon == 'eye' ? this.passwordIcon = 'eye-slash' : this.passwordIcon = 'eye'
      },
    doRegister() {
      doRegistration(
        `${username.value}`,
        `${email.value}`,
        `${password.value}`,
        `${cpassword.value}`
      );
    },
  },
};

function doRegistration(username, email, password, cpassword) {
  let e = validateEmail("emailErr", email);
  let u = validateUsername("usernameErr", username);
  let p = validatePassword("passwordErr", password, "cpasswordErr", cpassword);
  if (e == true && u == true && p == true) {
    sendCallToAPI(username, email, password);
  }
}

const sendCallToAPI = (user, mail, pwd) => {
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    username: user,
    email: mail,
    password: pwd,
    roles: ["user"],
  });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("https://kubb.in:8080/api/auth/signup", requestOptions)
    .then((response) => response.text())
    .then((result) => (window.location.href = "/login"))
    .catch((error) => console.log("error", error));
};

const validateEmail = (dh, email) => {
  const invalidEmail = "Invalid email adress. Please try again";
  let displayHandler = document.getElementById(dh);
  let emailFormat = /\S+@\S+\.\S+/;
  let emailString = email;

  if (!emailFormat.test(emailString)) {
    displayHandler.innerHTML = invalidEmail;
    displayHandler.style.display = "block";
    displayHandler.focus();
    return false;
  } else {
    displayHandler.innerHTML = "";
    displayHandler.style.display = "none";
    return true;
  }
};

const validateUsername = (dh, username) => {
  const invalidUsername =
    "Invalid Username. It must have minimum 4 characters. Please try again";
  let displayHandler = document.getElementById(dh);
  let usernameFormat = /[a-zA-Z]{4,}/;
  let usernameString = username;

  if (!usernameFormat.test(usernameString)) {
    displayHandler.innerHTML = invalidUsername;
    displayHandler.style.display = "block";
    displayHandler.focus();
    return false;
  } else {
    displayHandler.innerHTML = "";
    displayHandler.style.display = "none";
    return true;
  }
};

const validatePassword = (dh, password, dh2, cpassword) => {
  const invalidPassword =
    "Invalid Password. It must have minimum 8 characters. And it must contain letters and numbers. Please try again";
  const notMatchingPasswords = "Passwords don't match";
  let displayHandler = document.getElementById(dh);
  let displayHandler2 = document.getElementById(dh2);
  let passwordFormat = /(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}/;
  let atleastOneNumber = /\d+/;

  let passwordString = password;
  let cpasswordString = cpassword;
  let passwordsMatch = false;
  let passwordFormatIsOk = false;

  if (!passwordFormat.test(passwordString)) {
    displayHandler.innerHTML = invalidPassword;
    displayHandler.style.display = "block";
    displayHandler.focus();
    passwordFormatIsOk = false;
  } else {
    displayHandler.innerHTML = "";
    displayHandler.style.display = "none";
    passwordFormatIsOk = true;
  }

  if (passwordString !== cpasswordString) {
    displayHandler2.innerHTML = notMatchingPasswords;
    displayHandler2.style.display = "block";
    displayHandler.focus();
    passwordsMatch = false;
  } else {
    displayHandler2.innerHTML = "";
    displayHandler2.style.display = "none";
    passwordsMatch = true;
  }

  if (passwordFormatIsOk && passwordsMatch) {
    return true;
  } else {
    return false;
  }
};
</script>
