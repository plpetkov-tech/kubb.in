<template>
  <Layout>
    <div class="my-3 p-3 bg-white rounded box-shadow">
      <h2 class="titleAdd">Add a new Subscription</h2>
      <form id="newsub">
        <div class="form-group rounded box-shadow">
          <label for="name">Name</label>
          <input v-model="subname" type="text" name="name" id="name" />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="site">Website</label>
          <input v-model="site" type="text" name="site" id="site" />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="price">Price</label>
          <input v-model="price" type="text" name="price" id="price" />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="currency">Currency</label>
          <select v-model="currency" id="currency">
            <option value="AED">AED</option>
            <option value="ARS">ARS</option>
            <option value="AUD">AUD</option>
            <option value="BGN">BGN</option>
            <option value="BRL">BRL</option>
            <option value="BSD">BSD</option>
            <option value="CAD">CAD</option>
            <option value="CHF">CHF</option>
            <option value="CLP">CLP</option>
            <option value="CNY">CNY</option>
            <option value="COP">COP</option>
            <option value="CZK">CZK</option>
            <option value="DKK">DKK</option>
            <option value="DOP">DOP</option>
            <option value="EGP">EGP</option>
            <option value="EUR">EUR</option>
            <option value="FJD">FJD</option>
            <option value="GBP">GBP</option>
            <option value="GTQ">GTQ</option>
            <option value="HKD">HKD</option>
            <option value="HRK">HRK</option>
            <option value="HUF">HUF</option>
            <option value="IDR">IDR</option>
            <option value="ILS">ILS</option>
            <option value="INR">INR</option>
            <option value="ISK">ISK</option>
            <option value="JPY">JPY</option>
            <option value="KRW">KRW</option>
            <option value="KZT">KZT</option>
            <option value="MXN">MXN</option>
            <option value="MYR">MYR</option>
            <option value="NOK">NOK</option>
            <option value="NZD">NZD</option>
            <option value="PAB">PAB</option>
            <option value="PEN">PEN</option>
            <option value="PHP">PHP</option>
            <option value="PKR">PKR</option>
            <option value="PLN">PLN</option>
            <option value="PYG">PYG</option>
            <option value="RON">RON</option>
            <option value="RUB">RUB</option>
            <option value="SAR">SAR</option>
            <option value="SEK">SEK</option>
            <option value="SGD">SGD</option>
            <option value="THB">THB</option>
            <option value="TRY">TRY</option>
            <option value="TWD">TWD</option>
            <option value="UAH">UAH</option>
            <option value="USD" selected="">USD</option>
            <option value="UYU">UYU</option>
            <option value="VND">VND</option>
            <option value="ZAR">ZAR</option>
          </select>
        </div>
        <div class="form-group rounded box-shadow">
          <label for="regularity">Regularity</label>
          <select v-model="regularity" id="regularity">
            <option>Daily</option>
            <option>Weekly</option>
            <option>Monthly</option>
            <option>Yearly</option>
            <option>Other...</option>
          </select>
        </div>
        <div class="form-group rounded box-shadow">
          <label for="renewal">Date of next Renewal</label>
          <input
            v-model="nextrenewal"
            type="date"
            name="renewal"
            id="renewal"
          />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="autorenewal">Auto Renewal?</label>
          <select v-model="autorenewal" id="autorenewal">
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </div>
      </form>
    </div>
    <button
      type="button"
      id="submitBtn"
      @click="sendRequestAddNewSubscription()"
      class="btn btn-secondary btn-lg"
    >
      Add
    </button>

    <div class="my-3 p-3 bg-white rounded box-shadow">
      <h2 class="titleAdd">Your subscriptions</h2>
      <table class="table table-light table-responsive">
        <thead>
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Website</th>
            <th scope="col">Price</th>
            <th scope="col">Currency</th>
            <th scope="col">Regularity</th>
            <th scope="col">Next Renewal</th>
            <th scope="col">Auto Renewal</th>
          </tr>
        </thead>
        <tbody id="table-data"></tbody>
      </table>
    </div>
  </Layout>
</template>

<script>
import Layout from "~/layouts/Main.vue";

export default {
  metaInfo: {
    title: "Add a new Subscription",
  },
  components: {
    Layout,
  },
  data() {
    return {
      subname: "",
      autorenewal: "",
      currency: "",
      price: "",
      nextrenewal: "",
      site: "",
      regularity: "",
    };
  },
  methods: {
    sendRequestAddNewSubscription() {
      if(typeof window !== 'undefined'){
        window.sendRequestAddNewSubscriptionf(JSON.parse(localStorage.getItem("user")).accessToken, this.getFormObj());
      }
    },
    getFormObj() {
      return {
        name: this.subname,
        autorenewal: this.autorenewal,
        currency: this.currency,
        price: this.price,
        nextrenewal: this.nextrenewal,
        site: this.site,
        regularity: this.regularity,
      };
    },
  },
  mounted() {
    if (typeof window !== `undefined`) {
      const user = localStorage.getItem("user");
      const tokenJSON = JSON.parse(user);
      window.addEventListener("load", () => {
        if (tokenJSON == undefined || tokenJSON == null) {
          window.location.href = "/login";
        }
      });

      const processData = (d) => {
        const table = document.getElementById("table-data");
        const header = document.getElementById("header-table");
        let row,
          cellar,
          cellcurr,
          celllink,
          cellname,
          cellnr,
          cellpri,
          cellreg,
          headCell;
        d.forEach((element) => {
          row = document.createElement("tr");
          cellar = document.createElement("td");
          cellar.setAttribute("data-label", "Auto Renewal");
          cellcurr = document.createElement("td");
          cellcurr.setAttribute("data-label", "Currency");
          celllink = document.createElement("td");
          celllink.setAttribute("data-label", "Website");
          cellname = document.createElement("td");
          cellname.setAttribute("scope", "row");
          cellname.setAttribute("data-label", "Name");
          cellnr = document.createElement("td");
          cellnr.setAttribute("data-label", "Next Renewal");
          cellpri = document.createElement("td");
          cellpri.setAttribute("data-label", "Price");
          cellreg = document.createElement("td");
          cellreg.setAttribute("data-label", "Regularity");

          cellname.innerText = element.name;
          celllink.innerText = element.link;
          cellpri.innerText = element.price;
          cellcurr.innerText = element.currency;
          cellreg.innerText = element.regularity;
          cellnr.innerText = element.nextRenewal.split("T")[0];
          cellar.innerText = element.autoRenewal === "true" ? "Yes" : "No";

          row.append(
            cellname,
            celllink,
            cellpri,
            cellcurr,
            cellreg,
            cellnr,
            cellar
          );
          table.appendChild(row);
        });
      };

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
      const submitBtn = document.getElementById("submitBtn");
      /*


*/
window.sendRequestAddNewSubscriptionf = (token, sub) => {
        let validations = validateSub(sub);
        if (validations) {
          var myHeaders = new Headers();
          myHeaders.append("Authorization", `Bearer ${token}`);
          myHeaders.append("Content-Type", "application/json");

          var raw = JSON.stringify({
            autoRenewal: `${sub.autorenewal}`,
            currency: `${sub.currency}`,
            link: `${sub.site}`,
            name: `${sub.name}`,
            nextRenewal: `${sub.nextrenewal}`,
            price: `${sub.price}`,
            regularity: `${sub.regularity}`,
          });

          var requestOptions = {
            method: "PUT",
            headers: myHeaders,
            body: raw,
            redirect: "follow",
          };

          fetch("https://kubb.in:8080/api/subscription/", requestOptions)
            .then((response) => response.text())
            .then((result) => window.location.reload())
            .catch((error) => console.log("error", error));
        }
      };

      const deleteAllSubs = () => {
        let resp = prompt("Are You Sure? Type yes to continue");

        if (resp.toLowerCase() === "yes") {
          var myHeaders = new Headers();
          myHeaders.append("Authorization", `Bearer ${tokenJSON.accessToken}`);

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
      };
      const logout = () => {
        localStorage.removeItem("user");
      };

      const showAlert = (alert) => {
        const alertParent = alert.parentElement;
        const alertEle = document.createElement("div");
        alertEle.innerText = "Wrong input, please introduce valid data";
        alertEle.className = "alert alert-danger fade show";
        alertParent.insertBefore(alertEle, alert.parentElement.children[0]);

        setTimeout(closeAlert, 2500);
      };

      const closeAlert = () => {
        //$(".alert").alert("close");
        document.getElementsByClassName("alert")[0].remove();
      };

      const validateSub = (sub) => {
        //name is not empty or null
        if (sub.name == "" || sub.name == null || sub.name == undefined) {
          showAlert(document.getElementById("name"));

          return false;
        }
        if (sub.site == "" || sub.site == null || sub.site == undefined) {
          showAlert(document.getElementById("site"));

          return false;
        }
        //price is only numbers
        if (
          /[0-9]{1,}/.test(sub.price) != true ||
          sub.price == null ||
          sub.price == undefined
        ) {
          showAlert(price);

          return false;
        }
        //currency is only letters
        if (
          sub.currency == "" ||
          sub.currency == null ||
          sub.currency == undefined
        ) {
          showAlert(currency);

          return false;
        }
        //regularity is only letters
        if (
          sub.regularity == "" ||
          sub.regularity == null ||
          sub.regularity == undefined
        ) {
          showAlert(regularity);

          return false;
        }

        if (
          (sub.autorenewal != "true" && sub.autorenewal != "false") ||
          sub.autorenewal == null ||
          sub.autorenewal == undefined
        ) {
          showAlert(autorenewal);

          return false;
        }
        return true;
      };
    if (tokenJSON != null) {
      sendRequestForSubscriptions(tokenJSON.accessToken);
    }
    }
  },
  created() {
    
  },
  metaInfo: {
    title: "About us",
  },
};
</script>
