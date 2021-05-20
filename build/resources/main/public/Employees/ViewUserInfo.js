function viewUserInfo() {

  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var response = JSON.parse(this.responseText);
        document.getElementById("username").innerText = "Username: "+response.username;
        document.getElementById("password").innerText = "Password: "+response.password;
    }
  };
  xhttp.open("POST", "/EmployeeInfo", true);
  xhttp.send();
}
viewUserInfo();
