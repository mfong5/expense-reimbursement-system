function viewAllReimbursements() {
      let xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {

                var response = JSON.parse(this.responseText);
                let table = document.querySelector("table");
                let data = Object.keys(response[0]);
                generateTableHead(table, data);
                generateTable(table, response);
            }
      };
      xhttp.open("POST", "/ViewAllReimbursements", true);
      xhttp.send();
}

function generateTableHead(table, data) {
  let thead = table.createTHead();
  let row = thead.insertRow();
  for (let key of data) {
    let th = document.createElement("th");
    let text = document.createTextNode(key);
    th.appendChild(text);
    row.appendChild(th);
  }
}

function generateTable(table, data) {
  for (let element of data) {
    let row = table.insertRow();

    for (key in element) {
        let cell = row.insertCell();
        let text = document.createTextNode(element[key]);
        cell.appendChild(text);
    }
  }
}

viewAllReimbursements();

