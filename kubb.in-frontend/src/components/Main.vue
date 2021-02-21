<template>
  <div>
    <div class="my-3 p-3 bg-white rounded box-shadow">
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

    <div class="my-3 p-3 bg-white rounded box-shadow">
      <h6 class="border-bottom border-gray pb-2 mb-0">Recent updates</h6>
      <div class="media text-muted pt-3">
        <img
          data-src="holder.js/32x32?theme=thumb&amp;bg=007bff&amp;fg=007bff&amp;size=1"
          alt="32x32"
          class="mr-2 rounded"
          style="width: 32px; height: 32px"
          src="https://www.plamenpetkovonline.com/assets/static/avatar.05b4712.494649193db55dfb0eaccfdf26e42952.jpg"
          data-holder-rendered="true"
        />
        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
          <strong class="d-block text-gray-dark">@plpetkov</strong>
          I'm amazed to announce that the alpha version is working correctly
        </p>
      </div>

      <small class="d-block text-right mt-3">
        <a href="#">All updates</a>
      </small>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import UserService from "@/services/UserService";

@Component
export default class Main extends Vue {
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