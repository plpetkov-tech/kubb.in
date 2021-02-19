<template>
  <Layout>
    <div class="my-3 p-3 bg-white rounded box-shadow">
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
  </Layout>
</template>

<script>
import Layout from "~/layouts/Main.vue";

export default {
  metaInfo: {
    title: "Kubb.in Subscription Manager",
  },
  components: {
    Layout,
  },
  data() {
    return {
      test: "test",
    };
  },
  mounted() {
    if (typeof window !== "undefined") {
      const user = localStorage.getItem("user");
      const tokenJSON = JSON.parse(user);
      if (tokenJSON !== null) {
        sendRequestForSubscriptions(tokenJSON.accessToken);
      } else {
        window.location.href = '/login'
      }
      const deleteAllSubs = () => {
        let resp = prompt("Are You Sure? Type yes to continue");
        let token = JSON.parse(localStorage.getItem("user"));
        if (resp.toLowerCase() === "yes") {
          var myHeaders = new Headers();
          myHeaders.append("Authorization", `Bearer ${token.accessToken}`);

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
      
      
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("user");
      window.location.href = "/login";
    },
  },
};

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

    row.append(cellname, celllink, cellpri, cellcurr, cellreg, cellnr, cellar);
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
</script>
