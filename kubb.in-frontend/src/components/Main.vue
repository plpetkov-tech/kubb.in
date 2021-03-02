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
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
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
import User from "@/store/modules/auth.module";
import { namespace } from "vuex-class";

const Auth = namespace("Auth");

@Component
export default class Main extends Vue {
  @Auth.State("user")
  private currentUser!: any;

  @Auth.Action
  private signOut!: () => void;

  deleteOneSub(subid:any){
    UserService.deleteOneSub(subid).then((data)=>{
      this.$router.go(0)
    });
  }
  readData(
    data: {
      id:string;
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
      celledit,
      celldelete,
      headCell;
    data.forEach(
      (element: {
        id:string;
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
        celledit = document.createElement("td");
        celledit.setAttribute("data-label", "Edit");
        celldelete = document.createElement("td");
        celldelete.setAttribute("data-label", "Delete");

        cellname.innerText = element.name;
        celllink.innerText = element.link;
        cellpri.innerText = element.price;
        cellcurr.innerText = element.currency;
        cellreg.innerText = element.regularity;
        cellnr.innerText = element.nextRenewal.split("T")[0];
        cellar.innerText = element.autoRenewal === "true" ? "Yes" : "No";

        celldelete.innerHTML = `<button class='btn btn-secondary'>Delete</button>`;
        celldelete.addEventListener('click',()=>{
          this.deleteOneSub(`${element.id}`)
        })
        celledit.innerHTML = "<button class='btn btn-secondary'>Edit</button>";
        celledit.addEventListener('click',()=>{
          this.goToEditPage(`${element.id}`)
        })
        row.append(
          cellname,
          celllink,
          cellpri,
          cellcurr,
          cellreg,
          cellnr,
          cellar,
          celledit,
          celldelete
        );
        if (table) {
          table.appendChild(row);
        }
      }
    );
  }
  goToEditPage(arg0: string) {
    this.$router.push('/edit/'+arg0)
  }
  editOneSub(id: string,sub:any) {
    UserService.editOneSubscription(id,sub).then((data)=>{console.log(data)});
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
        this.signOut();
        this.$router.push("/login");
      }
    );
  }
  
}
</script>