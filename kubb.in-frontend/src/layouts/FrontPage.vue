<template scoped>
  <div>
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>we are kubb.in</title>
    </head>

    <nav>
      <div class="logo">
        <img src="../assets/photo.png" alt="kubbin" />
      </div>
      <ul>
        <li><a href="/">Home</a></li>
        <li>
          <a href="/login">To the application</a>
        </li>
        <li>
          <a href="https://www.plamenpetkovonline.com/#contact">Contact</a>
        </li>
        <li><a href="https://plamenpetkovonline.com">The creator</a></li>
      </ul>
    </nav>
    <header>
      <button id="toggle" class="toggle">
        <font-awesome-icon icon="bars" />
      </button>
      <h1>we are kubb.in</h1>
      <img src="../assets/source.gif" alt="grooving" />

      <p>Manage your subscriptions and renewals from one place!</p>

      <button class="cta-btn" @click="goToRegistration()" id="open">
        Sign Up!
      </button>
    </header>
    <div class="container">
      <h2>What is this application about?</h2>
      <p>
        Complexity comes when you enjoy different services, often at little
        price, lets say 5 euro monthly and you start to stack different
        services. At some point you lose track of the different services and
        start potentially to lose money, as you may don't use everything you are
        subscribed for.
      </p>
      <p>
        We aim at reducing this complexity and give people the power of being in
        control of the services they consume
      </p>
      <h2>Tell me more!</h2>
      <p>
        With kubb.in you can manage all your subscriptions in one place and be
        able to visualize what you are paying for and in effect, use your money
        wisely.
      </p>
      <h2>Benefits</h2>
      <ul>
        <li>Lifetime Access</li>
        <li>Easy to use interface</li>
        <li>Tailored for your needs!</li>
      </ul>
      <p>
        We wouldn't offer a product ourselves wouldn't need or use so we think
        you will be happy to be with us.
      </p>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'FrontPageLayout',
    mounted(){
        const toggle = document.getElementById("toggle");
const close = document.getElementById("close");
const open = document.getElementById("open");
const modal = document.getElementById("modal");


// Toggle navbar
toggle.addEventListener('click', ()=> 
    document.body.classList.toggle('show-nav')
);


open.addEventListener('click', ()=> window.location.href = '/register');

    }
  };
  
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Lato&display=swap');


* {
    box-sizing: border-box;
}

body {
    font-family: 'Lato', sans-serif;
    margin:0;
    transition: transform 0.3s ease;
    overflow-x: hidden; /* Hide scrollbars */
}

body.show-nav{
    transform: translate(200px);
}

nav{
    background-color:#defcf9;
    border-right: 2px solid rgba(200, 200, 200, 0.1);
    color: #000;
    position: fixed;
    top:0;
    left:0;
    width: 200px;
    height: 100%;
    z-index: 100;
    transform: translateX(-100%);
}

nav .logo{
    padding:30px 0;
    text-align: center;
}
nav .logo img{
    height: 75px;
    width: 75px;
}

nav ul{
    padding:0;
    list-style-type: none;
    margin: 0;
}
nav ul li{
    border-bottom: 2px solid rgba(200, 200, 200, 0.1);
    padding:20px;
}
nav ul li:first-of-type{
    border-top: 2px solid rgba(200, 200, 200, 0.1);
}

nav ul li a{
    color: #000;
    text-decoration: none;
}

nav ul li a:hover{
    text-decoration: underline;
}

header{
    background-color: #defcf9;
    color: #000;
    font-size: 130%;
    position: relative;
    padding: 40px 15px;
    text-align: center;
}

header h1{
    margin:0;
}
header p{
    margin:30px 0;
}
button,input[type='submit']{
    background-color: #42dbcc;
    border: 0;
    border-radius: 5px;
    color: #fff;
    cursor: pointer;
    padding:8px 12px;
}

button:focus{
    outline: none;
}

.toggle{
    background-color: rgba(0, 0, 0, 0.3);
    position: absolute;
    top: 20px;
    left: 20px;
}

.cta-btn{
    padding:12px 30px;
    font-size: 20px;
}

.container{
    padding:15px;
    margin: 0 auto;
    max-width: 100%;
    width: 800px;
}
.modal-container{
    display: none;
    background-color: rgba(0, 0, 0, 0.6);
    position: fixed;
    top:0;
    left:0;
    bottom: 0;
    right:0;
}
.modal-container.show-modal{
    display:block;
}

.modal{
    background-color: #fff;
    border-radius: 5px;
    position: absolute;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    overflow: hidden;
    top: 50%;
    left:50%;
    transform: translate(-50%,-50%);
    max-width: 100%;
    width: 400px;
    animation-name: modalopen;
    animation-duration: var(--modal-duration);
}

.modal-header{
    background: #defcf9;
    color: white;
    padding:15px;
}

.modal-header h3{
    margin:0;
    border-bottom: 1px solid #333;
}

.modal-content{
    padding:20px;
}

.modal-form div{
    margin:15px 0;
}

.modal-form label{
    display: block;
    margin-bottom: 5px;
}

.modal-form .form-input{
    padding:8px;
    width: 100%;
}

.close-btn{
    background: transparent;
    font-size: 25px;
    position: absolute;
    top:0;
    right:0;
}

@keyframes modalopen {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
.logo-box {
    /*d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded box-shadow*/
    display: flex;
    align-items: center;
    background-color: #defcf9;
  color: black;
  
  }
  .table-responsive {
    display: table;
  }
  table {
    border: 1px solid #defcf9;
    border-collapse: collapse;
    margin: 0;
    padding: 0;
    width: 100%;
  }
  
  table caption {
    font-size: 1.5em;
    margin: .25em 0 .75em;
  }
  
  table tr {
    background: #defcf9;
    border: 1px solid #42dbcc;
    padding: .35em;
  }
  
  table th, table td {
    padding: .625em;
    text-align: center;
  }
  
  table th {
    font-size: .85em;
    letter-spacing: .1em;
    text-transform: uppercase;
  }
  
  table td img { text-align: center; }
  @media screen and (max-width: 770px) {
  
  table { border: 0; }
  
  .table-responsive {
    display: grid;
  }
  
  table caption { font-size: 1.3em; }
  
  table thead { display: none; }
  
  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  table td:before {
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  table td:last-child { border-bottom: 0; }
  }
  @media screen and (min-width: 770px) and (max-width: 995px)  {
    .table-responsive {
      display: block;
    }
    }
  

</style>