const processData = (d)=> {
   const table = document.getElementById('table-data');
   const header = document.getElementById('header-table');
   console.log(d)
   let row, cellar, cellcurr,celllink,cellname,cellnr,cellpri,cellreg,headCell;
   d.forEach(element => 
   {
       row = document.createElement('tr');
       cellar = document.createElement('td');
       cellar.setAttribute('data-label','Auto Renewal');
       cellcurr  = document.createElement('td');
       cellcurr.setAttribute('data-label','Currency');
       celllink = document.createElement('td');
       celllink.setAttribute('data-label','Website');
       cellname = document.createElement('td');
       cellname.setAttribute('scope','row');
       cellname.setAttribute('data-label','Name');
       cellnr = document.createElement('td');
       cellnr.setAttribute('data-label','Next Renewal');
       cellpri = document.createElement('td');
       cellpri.setAttribute('data-label','Price');
       cellreg = document.createElement('td');
       cellreg.setAttribute('data-label','Regularity');


       cellname.innerText = element.name;
       celllink.innerText = element.link;
       cellpri.innerText = element.price;
       cellcurr .innerText = element.currency;
       cellreg.innerText = element.regularity;
       cellnr.innerText = element.nextRenewal.split('T')[0];
       cellar.innerText = element.autoRenewal;

       row.append(cellname,celllink,cellpri,cellcurr,cellreg,cellnr,cellar);
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

     fetch("https://localhost:8080/api/subscriptions/", requestOptions)
       .then((response) => response.json())
       .then((result) => processData(result))
       .catch((error) => console.log("error", error));
   };

   sendRequestForSubscriptions(tokenJSON.accessToken);  