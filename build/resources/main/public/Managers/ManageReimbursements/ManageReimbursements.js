function manageReimbursements() {
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
      xhttp.open("POST", "/ManageEmployeeReimbursements", true);
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
  let total = 0;
  for (let element of data) {
    let row = table.insertRow();
    for (key in element) {
        let cell = row.insertCell();
        let text = document.createTextNode(element[key]);

        cell.appendChild(text);
    }

    if (element.status == "Pending"){
        let approveCell = row.insertCell();
        let approveButton = document.createElement("BUTTON");
        approveButton.innerHTML = "Approve";
        approveButton.setAttribute("id","approveButton"+total)
        approveButton.setAttribute("onclick","Approve(id)");
        approveCell.appendChild(approveButton);

        let denyCell = row.insertCell();
        let denyButton = document.createElement("BUTTON");
        denyButton.innerHTML = "Deny";
        denyButton.setAttribute("id","denyButton"+total);
        denyButton.setAttribute("onclick","Deny(id)");
        denyCell.appendChild(denyButton);
    }
    row.setAttribute("id","row"+total);
    total += 1;
  }
}

function Approve(id){
    let index = id.replace("approveButton","");
    let row = document.getElementById("row"+index);

    let form = document.createElement("FORM");
    form.setAttribute("method","post");
    form.setAttribute("action","/Managers/ManageReimbursements/EmployeeReimbursements");

    var username = document.createElement("input");
    username.setAttribute("type","text");
    username.setAttribute("name","username");

    var reason = document.createElement("input");
    reason.setAttribute("type","text");
    reason.setAttribute("name","reason");

    var expense = document.createElement("input");
    expense.setAttribute("type","text");
    expense.setAttribute("name","expense");

    var status = document.createElement("input");
    status.setAttribute("type","text");
    status.setAttribute("name","status");

    username.setAttribute("value",row.cells[0].innerHTML);
    reason.setAttribute("value",row.cells[1].innerHTML);
    expense.setAttribute("value",row.cells[2].innerHTML);
    status.setAttribute("value","Approved");

    let button = document.getElementById(id);

    form.appendChild(username);
    form.appendChild(expense);
    form.appendChild(reason);
    form.appendChild(status);
    form.appendChild(button);

    document.body.appendChild(form);
}

function Deny(id){
    let index = id.replace("denyButton","");
    let row = document.getElementById("row"+index);

    let form = document.createElement("FORM");
    form.setAttribute("method","post");
    form.setAttribute("action","/Managers/ManageReimbursements/EmployeeReimbursements");

    var username = document.createElement("input");
    username.setAttribute("type","text");
    username.setAttribute("name","username");

    var reason = document.createElement("input");
    reason.setAttribute("type","text");
    reason.setAttribute("name","reason");

    var expense = document.createElement("input");
    expense.setAttribute("type","text");
    expense.setAttribute("name","expense");

    var status = document.createElement("input");
    status.setAttribute("type","text");
    status.setAttribute("name","status");

    username.setAttribute("value",row.cells[0].innerHTML);
    reason.setAttribute("value",row.cells[1].innerHTML);
    expense.setAttribute("value",row.cells[2].innerHTML);
    status.setAttribute("value","Denied");

    let button = document.getElementById(id);

    form.appendChild(username);
    form.appendChild(expense);
    form.appendChild(reason);
    form.appendChild(status);
    form.appendChild(button);

    document.body.appendChild(form);
}


manageReimbursements();

