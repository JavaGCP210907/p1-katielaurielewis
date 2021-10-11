const url = "http://localhost:8090/"

document.getElementById("loginButton").addEventListener("click", loginFunc);
document.getElementById("submitButton").addEventListener("click", submitReimbursement);




async function managerFunc(){

	let manager = false;
    let usern = document.getElementById("username").value; 

    let response = await fetch(url + "user?username=" + usern, {
        method: "GET",
        //body: JSON.stringify(user),
        credentials: "include"
    });

    console.log(response.status); 

    if(response.status === 200){
		manager = await response.json();
    } else {
        //document.getElementById("login-row").innerText="Please check username and password."
    }
    return manager;

}

async function loginFunc(){



    let usern = document.getElementById("username").value; 
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
    	let manager = await managerFunc();
		document.getElementById("loginform").style.display = "none";
		document.getElementById("tablecontainer").style.display = "block";
		if(manager) {
			document.getElementById("managerview").style.display = "block";
		}
		else {
			document.getElementById("employeeview").style.display = "block";
		}
    } else {
        document.getElementById("login-row").innerText="Please check username and password."
    }

}

async function reimburseFunc() { 

    let response = await fetch(url + "reimbursement", {credentials: "include"});

    console.log(response);

    if(response.status === 200) { //if the request comes back successful...

        let data = await response.json(); //get the JSON data, turn it into a JS object

        //For every Avenger object we get back, put it in the table
        for(let reimbursement of data){

            let row = document.createElement("tr"); //create a table row

            let cell = document.createElement("td"); //create a cell for the field
            cell.innerHTML = reimbursement.reimb_id; //fill the cell with the appropriate avenger data
            row.appendChild(cell); //add the td element (cell) to the tr element (row)

            //then we do this ^ for every field in the avenger model
            let cell2 = document.createElement("td"); 
            cell2.innerHTML = reimbursement.reimb_amount; 
            row.appendChild(cell2);

            let cell3 = document.createElement("td"); 
            cell3.innerHTML = reimbursement.reimb_description; 
            row.appendChild(cell3);

            let cell4 = document.createElement("td"); 
            cell4.innerHTML = reimbursement.reimb_resolved; 
            row.appendChild(cell4);

            let cell5 = document.createElement("td"); 
            cell5.innerHTML = reimbursement.reimb_submitted; 
            row.appendChild(cell5);

            let cell6 = document.createElement("td"); 
            cell6.innerHTML = reimbursement.reimb_author; 
            row.appendChild(cell6);

            let cell7 = document.createElement("td"); 
            //this would return the entire home object so we look only for the homeName
            cell7.innerHTML = avenger.home_fk.home_name; 
            row.appendChild(cell7);

            //the tr called row that we created in the for loop gets appended to the table body
            //a new row will be appended for ever Avenger object that comes in
            document.getElementById("avengerBody").appendChild(row);

        }

    }

}

async function submitReimbursement() {
	
	let amount = document.getElementById("amount").value;
	let description = document.getElementById("description").value;
	var types = document.getElementsByName("typeradio");
	var selectedType;

	for(var i = 0; i < types.length; i++) {
   		if(types[i].checked) {
   			selectedType = types[i].value;
   		}
 	}
	
	let request = {
		amount: amount,
		description: description,
		type: selectedType
	};
	
	let response = await fetch(url + "reimbursement", {
		method: "POST",
        body: JSON.stringify(request),
        credentials: "include"
	});
	
	if(response.status === 200) {
		console.log(response);
		document.getElementById("amount").value = "";
		document.getElementById("description").value = "";
	}
	
	
	
	
}
