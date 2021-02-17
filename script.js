const toggle = document.getElementById("toggle");
const close = document.getElementById("close");
const open = document.getElementById("open");
const modal = document.getElementById("modal");


// Toggle navbar
toggle.addEventListener('click', ()=> 
    document.body.classList.toggle('show-nav')
);


open.addEventListener('click', ()=> window.location.href = '/kubb.in-frontend/login/register.html');
