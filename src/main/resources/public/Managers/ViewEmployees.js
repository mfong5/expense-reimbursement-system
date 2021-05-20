function viewEmployees() {
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
    xhttp.open("POST", "/ViewEmployees", true);
    xhttp.send();
}

function generateTableHead(table, data) {
    let thead = table.createTHead();
    let row = thead.insertRow();
    let th = document.createElement("th");
    let text = document.createTextNode("username");
    th.appendChild(text);
    row.appendChild(th);
}

function generateTable(table, data) {
    for (let element of data) {
        let row = table.insertRow();
        let cell = row.insertCell();
        let text = document.createTextNode(element["username"]);
        cell.appendChild(text);
    }
}

viewEmployees();