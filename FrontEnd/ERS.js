document.getElementById("loginButton").addEventListener("click", loginFunc);

async function loginFunc(){

	document.getElementById("loginform").style.display = "none";
	document.getElementById("tablecontainer").style.display = "block";
	if(document.getElementById("username").value == "abc") {
		document.getElementById("managerview").style.display = "block";
	}
	else {
		document.getElementById("employeeview").style.display = "block";
	}

    /*let usern = document.getElementById("username").value; 
    let userp = document.getElementById("password").value;

    let user = {
        username:usern,
        password:userp
    }

    console.log(user)

    let response = await fetch(url + "login", {

        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    });

    console.log(response.status); 

    if(response.status === 200){
        document.getElementById("login-row").innerText="Welcome!";
    } else {
        document.getElementById("login-row").innerText="Login failed! Do better."
    }*/

}