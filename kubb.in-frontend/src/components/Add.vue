<template>
  <div>
     <div class="my-3 p-3 bg-white rounded box-shadow">
      <h2 class="titleAdd">Add a new Subscription</h2>
      <form id="newsub">
        <div class="form-group rounded box-shadow">
          <label for="name">Name</label>
          <input v-model="subscription.name" type="text" name="name" id="name" />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="site">Website</label>
          <input v-model="subscription.link" type="text" name="site" id="site" />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="price">Price</label>
          <input v-model="subscription.price" type="text" name="price" id="price" />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="currency">Currency</label>
          <select v-model="subscription.currency" id="currency">
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
          <select v-model="subscription.regularity" id="regularity">
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
            v-model="subscription.nextRenewal"
            type="date"
            name="renewal"
            id="renewal"
          />
        </div>
        <div class="form-group rounded box-shadow">
          <label for="autorenewal">Auto Renewal?</label>
          <select v-model="subscription.autoRenewal" id="autorenewal">
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
      <table class="table table-light">
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
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import UserService from "@/services/UserService";

@Component
export default class Main extends Vue {
    private subscription = {
        name: '',
        link: '',
        price: '',
        currency: '',
        regularity: '',
        nextRenewal: '',
        autoRenewal: ''
    }
    sendRequestAddNewSubscription(){
        UserService.postOneSubscription(this.subscription).then((data)=>{
           this.$router.go(0)
           
        })
    }
  readData(
    data: {
      name: string;
      link: string;
      price: string;
      currency: string;
      regularity: string;
      nextRenewal: string;
      autoRenewal: string;
    }[]
  ) {
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
    data.forEach(
      (element: {
        name: string;
        link: string;
        price: string;
        currency: string;
        regularity: string;
        nextRenewal: string;
        autoRenewal: string;
      }) => {
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
        if (table) {
          table.appendChild(row);
        }
      }
    );
  }
  mounted() {
    UserService.getSubscriptions().then(
      (response) => {
        this.readData(response.data);
      },
      (error) => {
        console.error(
          (error.response && error.response.data) ||
            error.message ||
            error.toString()
        );
      }
    );
  }
}
</script>