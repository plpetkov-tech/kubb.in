window.addEventListener('load', () => {
    if (tokenJSON == undefined || tokenJSON == null) {
        window.location.href = '/kubb.in-frontend/login/index.html'
    }
})

const form = document.getElementById('newsub');
let subname = document.getElementById('name');
let autorenewal = document.getElementById('autorenewal');
let currency = document.getElementById('currency');
let price = document.getElementById('price');
let nextrenewal = document.getElementById('renewal');
let site = document.getElementById('site');
let regularity = document.getElementById('regularity');
let getFormObj = () => {
    return {
        "name": subname.value,
        "autorenewal": autorenewal.value,
        "currency": currency.value,
        "price": price.value,
        "nextrenewal": nextrenewal.value,
        "site": site.value,
        "regularity": regularity.value
    }
}

const processData = (d) => {
    const table = document.getElementById('table-data');
    const header = document.getElementById('header-table');
    console.log(d)
    let row, cellar, cellcurr, celllink, cellname, cellnr, cellpri, cellreg, headCell;
    d.forEach(element => {
        row = document.createElement('tr');
        cellar = document.createElement('td');
        cellar.setAttribute('data-label', 'Auto Renewal');
        cellcurr = document.createElement('td');
        cellcurr.setAttribute('data-label', 'Currency');
        celllink = document.createElement('td');
        celllink.setAttribute('data-label', 'Website');
        cellname = document.createElement('td');
        cellname.setAttribute('scope', 'row');
        cellname.setAttribute('data-label', 'Name');
        cellnr = document.createElement('td');
        cellnr.setAttribute('data-label', 'Next Renewal');
        cellpri = document.createElement('td');
        cellpri.setAttribute('data-label', 'Price');
        cellreg = document.createElement('td');
        cellreg.setAttribute('data-label', 'Regularity');


        cellname.innerText = element.name;
        celllink.innerText = element.link;
        cellpri.innerText = element.price;
        cellcurr.innerText = element.currency;
        cellreg.innerText = element.regularity;
        cellnr.innerText = element.nextRenewal.split('T')[0];
        cellar.innerText = element.autoRenewal === 'true' ? 'Yes' : 'No';

        row.append(cellname, celllink, cellpri, cellcurr, cellreg, cellnr, cellar);
        table.appendChild(row)
    });
}
const user = localStorage.getItem("user");
const tokenJSON = JSON.parse(user);
const sendRequestForSubscriptions = (token) => {
    var myHeaders = new Headers();
    myHeaders.append("Authorization", `Bearer ${token}`);

    var requestOptions = {
        method: "GET",
        headers: myHeaders,
        redirect: "follow",
    };

    fetch("https://kubb.in:8080/api/subscriptions/", requestOptions)
        .then((response) => response.json())
        .then((result) => processData(result))
        .catch((error) => console.log("error", error));
};

sendRequestForSubscriptions(tokenJSON.accessToken);


const submitBtn = document.getElementById('submitBtn');
submitBtn.addEventListener('click', (e) => {
    //showAlert(price)
    sendRequestAddNewSubscription(tokenJSON.accessToken, getFormObj());
})


const sendRequestAddNewSubscription = (token, sub) => {

    let izmislica = validateSub(sub);
    if (izmislica) {
        var myHeaders = new Headers();
        myHeaders.append("Authorization", `Bearer ${token}`);
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({ "autoRenewal": `${sub.autorenewal}`, "currency": `${sub.currency}`, "link": `${sub.site}`, "name": `${sub.name}`, "nextRenewal": `${sub.nextrenewal}`, "price": `${sub.price}`, "regularity": `${sub.regularity}` });

        var requestOptions = {
            method: 'PUT',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("https://kubb.in:8080/api/subscription/", requestOptions)
            .then(response => response.text())
            .then(result => window.location.reload())
            .catch(error => console.log('error', error));
    }
}


const deleteAllSubs = () => {
    let resp = prompt("Are You Sure? Type yes to continue");

    if (resp.toLowerCase() === 'yes') {
        var myHeaders = new Headers();
        myHeaders.append("Authorization", `Bearer ${tokenJSON.accessToken}`);

        var requestOptions = {
            method: 'DELETE',
            headers: myHeaders,
            redirect: 'follow'
        };

        fetch("https://kubb.in:8080/api/subscriptions/", requestOptions)
            .then(response => response.text())
            .then(result => window.location.reload())
            .catch(error => console.log('error', error));
    }
}
const logout = () => {
    localStorage.removeItem('user');
}

const showAlert = (alert) => {
    const alertParent = alert.parentElement;
    const alertEle = document.createElement('div');
    alertEle.innerText = 'Wrong input, please introduce valid data';
    alertEle.className = 'alert alert-danger fade show';
    alertParent.insertBefore(alertEle, alert.parentElement.children[0]);
    console.log(alertEle);

    setTimeout(closeAlert, 2500)

}

const closeAlert = () => {
    $('.alert').alert('close')
}

const validateSub = (sub) => {

        //name is not empty or null
        if (sub.name == '' || sub.name == null || sub.name == undefined) {
            showAlert(subname)
            
            return false;

        }
        //price is only numbers
        if ( /[0-9]{1,}/.test(sub.price) != true || (sub.price == null || sub.price == undefined)) {
            showAlert(price)
            
            return false;

        }
        //currency is only letters
        if (sub.currency == '' || sub.currency == null || sub.currency == undefined) {
            showAlert(currency)
            
            return false;

        }
        //regularity is only letters
        if (sub.regularity == '' || sub.regularity == null || sub.regularity == undefined) {
            showAlert(regularity)
            
            return false;

        }
        //autorenewal is true or false
        console.log(sub.autorenewal)
        if ((sub.autorenewal != 'true' && sub.autorenewal != 'false' )||sub.autorenewal == null || sub.autorenewal == undefined) {
            showAlert(autorenewal)
            
            return false;
        }
        return true;
    


}