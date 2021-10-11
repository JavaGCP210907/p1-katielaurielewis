const url = "http://localhost:8090/"
let manager = false

document.getElementById("loginButton").addEventListener("click", loginFunc);
document.getElementById("submitButton").addEventListener("click", submitReimbursement);
document.getElementById("pending-btn").addEventListener("click", pendingFunc);
document.getElementById("approved-btn").addEventListener("click", approvedFunc);
document.getElementById("denied-btn").addEventListener("click", deniedFunc);
document.getElementById("logout-btn").addEventListener("click", logoutFunc);




async function managerFunc(){

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
			pendingFunc();
		}
		else {
			document.getElementById("employeeview").style.display = "block";
			        document.getElementById("login-row").innerText=""
			
		}
    } else {
        document.getElementById("login-row").innerText="Please check username and password."
    }
}

async function logoutFunc() {
	let response = await fetch(url + "logout", {
		credentials:"include"
	});
	
	if(response.status === 200) {
		document.getElementById("loginform").style.display = "block";
		document.getElementById("tablecontainer").style.display = "none";
		document.getElementById("managerview").style.display = "none";
		document.getElementById("employeeview").style.display = "none";
		
		document.getElementById("reimbursement-body-manager").innerHTML = "";
		document.getElementById("reimbursement-head-manager").innerHTML = "";
		document.getElementById("reimbursement-body-employee").innerHTML = "";
		document.getElementById("reimbursement-head-employee").innerHTML = "";
		
		document.getElementById("username").value = "";
    	document.getElementById("password").value = "";
	}
}

async function pendingFunc() { 

	document.getElementById("reimbursement-body-manager").innerHTML = "";
	document.getElementById("reimbursement-head-manager").innerHTML = "";
	document.getElementById("reimbursement-body-employee").innerHTML = "";
	document.getElementById("reimbursement-head-employee").innerHTML = "";

    let response = await fetch(url + "reimbursement/pending?manager=" + manager, {credentials: "include"});

    console.log(response);

    if(response.status === 200) {
    
    	let tableHeadId = "reimbursement-head"
    	let tableBodyId = "reimbursement-body"
    	if(manager) {
    		tableHeadId = tableHeadId + "-manager"
    		tableBodyId = tableBodyId + "-manager"	
    	} else {
    		tableHeadId = tableHeadId + "-employee"
    		tableBodyId = tableBodyId + "-employee"
    	}
    	let columns = document.getElementById(tableHeadId)
        	
        let column1 = document.createElement("th");
        column1.setAttribute("scope", "col");
        column1.innerHTML = "ID";
        columns.appendChild(column1);
        	
        let column2 = document.createElement("th");
        column2.setAttribute("scope", "col");
        column2.innerHTML = "Amount";
        columns.appendChild(column2);
        	
        let column3 = document.createElement("th");
        column3.setAttribute("scope", "col");
        column3.innerHTML = "Description";
        columns.appendChild(column3);
        	
        let column4 = document.createElement("th");
        column4.setAttribute("scope", "col");
        column4.innerHTML = "Submitted";
        columns.appendChild(column4);
        	
        if(manager) {
        	let column5 = document.createElement("th");
        	column5.setAttribute("scope", "col");
        	column5.innerHTML = "Author";
        	columns.appendChild(column5);
        }
        	
        let column6 = document.createElement("th");
        column6.setAttribute("scope", "col");
        column6.innerHTML = "Status";
        columns.appendChild(column6);
        	
        let column7 = document.createElement("th");
        column7.setAttribute("scope", "col");
        column7.innerHTML = "Type";
        columns.appendChild(column7);
        	
        if(manager) {
        	let column8 = document.createElement("th");
        	column8.setAttribute("scope", "col");
        	column8.innerHTML = "Approve";
        	columns.appendChild(column8);
        	
        	let column9 = document.createElement("th");
        	column9.setAttribute("scope", "col");
        	column9.innerHTML = "Deny";
        	columns.appendChild(column9);
        }

        let data = await response.json();

        for(let reimbursement of data){       	

            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            //then we do this ^ for every field in the avenger model
            let cell2 = document.createElement("td"); 
            cell2.innerHTML = reimbursement.amount; 
            row.appendChild(cell2);

            let cell3 = document.createElement("td"); 
            cell3.innerHTML = reimbursement.description; 
            row.appendChild(cell3);

            let cell5 = document.createElement("td"); 
            cell5.innerHTML = reimbursement.submitted; 
            row.appendChild(cell5);

			if(manager) {
				let cell6 = document.createElement("td"); 
            	cell6.innerHTML = reimbursement.author.firstName + " " + reimbursement.author.lastName; 
            	row.appendChild(cell6);
			}
            
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.status.status;
            row.appendChild(cell8);
            
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimbursement.type.type;
            row.appendChild(cell9);
            
            if(manager) {
            	let cell10 = document.createElement("td");
            	let cell10Button = document.createElement("button");
            	cell10Button.value = reimbursement.id;
            	cell10Button.setAttribute("onclick", "approveReimbursement(this.value)")
            	cell10Button.innerHTML = "Approve";
            	cell10.appendChild(cell10Button);
            	row.appendChild(cell10);
            
            	let cell11 = document.createElement("td");
            	let cell11Button = document.createElement("button");
            	cell11Button.value = reimbursement.id;
            	cell11Button.setAttribute("onclick", "denyReimbursement(this.value)")
            	cell11Button.innerHTML = "Deny";
            	cell11.appendChild(cell11Button);
            	row.appendChild(cell11);
            }
            
            //the tr called row that we created in the for loop gets appended to the table body
            //a new row will be appended for ever Avenger object that comes in
            document.getElementById(tableBodyId).appendChild(row);

        }

    }

}

async function approvedFunc() {

	document.getElementById("reimbursement-body-manager").innerHTML = "";
	document.getElementById("reimbursement-head-manager").innerHTML = "";
	document.getElementById("reimbursement-body-employee").innerHTML = "";
	document.getElementById("reimbursement-head-employee").innerHTML = "";

    let response = await fetch(url + "reimbursement/approved?manager=" + manager, {credentials: "include"});

    console.log(response);

    if(response.status === 200) {

        let data = await response.json();
        
        let tableHeadId = "reimbursement-head"
    	let tableBodyId = "reimbursement-body"
    	if(manager) {
    		tableHeadId = tableHeadId + "-manager"
    		tableBodyId = tableBodyId + "-manager"	
    	} else {
    		tableHeadId = tableHeadId + "-employee"
    		tableBodyId = tableBodyId + "-employee"
    	}
    	let columns = document.getElementById(tableHeadId)
        	
        let column1 = document.createElement("th");
        column1.setAttribute("scope", "col");
        column1.innerHTML = "ID";
        columns.appendChild(column1);
        	
        let column2 = document.createElement("th");
        column2.setAttribute("scope", "col");
        column2.innerHTML = "Amount";
        columns.appendChild(column2);
        	
        let column3 = document.createElement("th");
        column3.setAttribute("scope", "col");
        column3.innerHTML = "Description";
        columns.appendChild(column3);
        	
        let column4 = document.createElement("th");
        column4.setAttribute("scope", "col");
        column4.innerHTML = "Resolved";
        columns.appendChild(column4);
        	
        let column5 = document.createElement("th");
        column5.setAttribute("scope", "col");
        column5.innerHTML = "Submitted";
        columns.appendChild(column5);
        
        if(manager) {
        	let column6 = document.createElement("th");
        	column6.setAttribute("scope", "col");
        	column6.innerHTML = "Author";
        	columns.appendChild(column6);
        }
        
        let column7 = document.createElement("th");
        column7.setAttribute("scope", "col");
        column7.innerHTML = "Resolver";
        columns.appendChild(column7);
        	
        let column8 = document.createElement("th");
        column8.setAttribute("scope", "col");
        column8.innerHTML = "Status";
        columns.appendChild(column8);
        	
        let column9 = document.createElement("th");
        column9.setAttribute("scope", "col");
        column9.innerHTML = "Type";
        columns.appendChild(column9);

        for(let reimbursement of data) {

            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            //then we do this ^ for every field in the avenger model
            let cell2 = document.createElement("td"); 
            cell2.innerHTML = reimbursement.amount; 
            row.appendChild(cell2);

            let cell3 = document.createElement("td"); 
            cell3.innerHTML = reimbursement.description; 
            row.appendChild(cell3);

            let cell4 = document.createElement("td"); 
            cell4.innerHTML = reimbursement.resolved; 
            row.appendChild(cell4);

            let cell5 = document.createElement("td"); 
            cell5.innerHTML = reimbursement.submitted; 
            row.appendChild(cell5);

			if(manager) {
				let cell6 = document.createElement("td"); 
            	cell6.innerHTML = reimbursement.author.firstName + " " + reimbursement.author.lastName; 
            	row.appendChild(cell6);
			}

            let cell7 = document.createElement("td"); 
            cell7.innerHTML = reimbursement.resolver.firstName + " " + reimbursement.resolver.lastName;
            row.appendChild(cell7);
            
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.status.status;
            row.appendChild(cell8);
            
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimbursement.type.type;
            row.appendChild(cell9);
            
            //the tr called row that we created in the for loop gets appended to the table body
            //a new row will be appended for ever Avenger object that comes in
            document.getElementById(tableBodyId).appendChild(row);

        }

    }

}

async function deniedFunc() {

	document.getElementById("reimbursement-body-manager").innerHTML = "";
	document.getElementById("reimbursement-head-manager").innerHTML = "";
	document.getElementById("reimbursement-body-employee").innerHTML = "";
	document.getElementById("reimbursement-head-employee").innerHTML = "";

    let response = await fetch(url + "reimbursement/denied?manager=" + manager, {credentials: "include"});

    console.log(response);

    if(response.status === 200) {

        let data = await response.json();
        
        let tableHeadId = "reimbursement-head"
    	let tableBodyId = "reimbursement-body"
    	if(manager) {
    		tableHeadId = tableHeadId + "-manager"
    		tableBodyId = tableBodyId + "-manager"	
    	} else {
    		tableHeadId = tableHeadId + "-employee"
    		tableBodyId = tableBodyId + "-employee"
    	}
    	let columns = document.getElementById(tableHeadId)
        	
        let column1 = document.createElement("th");
        column1.setAttribute("scope", "col");
        column1.innerHTML = "ID";
        columns.appendChild(column1);
        	
        let column2 = document.createElement("th");
        column2.setAttribute("scope", "col");
        column2.innerHTML = "Amount";
        columns.appendChild(column2);
        	
        let column3 = document.createElement("th");
        column3.setAttribute("scope", "col");
        column3.innerHTML = "Description";
        columns.appendChild(column3);
        	
        let column4 = document.createElement("th");
        column4.setAttribute("scope", "col");
        column4.innerHTML = "Resolved";
        columns.appendChild(column4);
        	
        let column5 = document.createElement("th");
        column5.setAttribute("scope", "col");
        column5.innerHTML = "Submitted";
        columns.appendChild(column5);
        	
        if(manager) {
        	let column6 = document.createElement("th");
        	column6.setAttribute("scope", "col");
        	column6.innerHTML = "Author";
        	columns.appendChild(column6);
        }
        
        let column7 = document.createElement("th");
        column7.setAttribute("scope", "col");
        column7.innerHTML = "Resolver";
        columns.appendChild(column7);
        	
        let column8 = document.createElement("th");
        column8.setAttribute("scope", "col");
        column8.innerHTML = "Status";
        columns.appendChild(column8);
        	
        let column9 = document.createElement("th");
        column9.setAttribute("scope", "col");
        column9.innerHTML = "Type";
        columns.appendChild(column9);

        for(let reimbursement of data){

            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            //then we do this ^ for every field in the avenger model
            let cell2 = document.createElement("td"); 
            cell2.innerHTML = reimbursement.amount; 
            row.appendChild(cell2);

            let cell3 = document.createElement("td"); 
            cell3.innerHTML = reimbursement.description; 
            row.appendChild(cell3);

            let cell4 = document.createElement("td"); 
            cell4.innerHTML = reimbursement.resolved; 
            row.appendChild(cell4);

            let cell5 = document.createElement("td"); 
            cell5.innerHTML = reimbursement.submitted; 
            row.appendChild(cell5);

			if(manager) {
				let cell6 = document.createElement("td"); 
            	cell6.innerHTML = reimbursement.author.firstName + " " + reimbursement.author.lastName; 
            	row.appendChild(cell6);
			}

            let cell7 = document.createElement("td"); 
            cell7.innerHTML = reimbursement.resolver.firstName + " " + reimbursement.resolver.lastName;
            row.appendChild(cell7);
            
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.status.status;
            row.appendChild(cell8);
            
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimbursement.type.type;
            row.appendChild(cell9);
            
            //the tr called row that we created in the for loop gets appended to the table body
            //a new row will be appended for ever Avenger object that comes in
            document.getElementById(tableBodyId).appendChild(row);

        }

    }

}

async function reimburseFunc() {

	document.getElementById("reimbursement-body").innerHTML = "";

    let response = await fetch(url + "reimbursement", {credentials: "include"});

    console.log(response);

    if(response.status === 200) {

        let data = await response.json();

        for(let reimbursement of data){

            let row = document.createElement("tr");

            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.id;
            row.appendChild(cell);

            //then we do this ^ for every field in the avenger model
            let cell2 = document.createElement("td"); 
            cell2.innerHTML = reimbursement.amount; 
            row.appendChild(cell2);

            let cell3 = document.createElement("td"); 
            cell3.innerHTML = reimbursement.description; 
            row.appendChild(cell3);

            /*let cell4 = document.createElement("td"); 
            cell4.innerHTML = reimbursement.resolved; 
            row.appendChild(cell4);*/

            let cell5 = document.createElement("td"); 
            cell5.innerHTML = reimbursement.submitted; 
            row.appendChild(cell5);

            let cell6 = document.createElement("td"); 
            cell6.innerHTML = reimbursement.author.firstName + reimbursement.author.lastName; 
            row.appendChild(cell6);

           /* let cell7 = document.createElement("td"); 
            cell7.innerHTML = reimbursement.resolver.firstName + reimbursement.resolver.lastName;
            row.appendChild(cell7);*/
            
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.status.status;
            row.appendChild(cell8);
            
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimbursement.type.type;
            row.appendChild(cell9);
            
            let cell10 = document.createElement("td");
            let cell10Button = document.createElement("button");
            cell10Button.value = reimbursement.id;
            cell10Button.setAttribute("onclick", "approveReimbursement(this.value)")
            cell10Button.innerHTML = "Approve";
            cell10.appendChild(cell10Button);
            row.appendChild(cell10);
            
            let cell11 = document.createElement("td");
            let cell11Button = document.createElement("button");
            cell11Button.value = reimbursement.id;
            cell11Button.setAttribute("onclick", "denyReimbursement(this.value)")
            cell11Button.innerHTML = "Deny";
            cell11.appendChild(cell11Button);
            row.appendChild(cell11);
            
            //the tr called row that we created in the for loop gets appended to the table body
            //a new row will be appended for ever Avenger object that comes in
            document.getElementById("reimbursement-body").appendChild(row);

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
		pendingFunc();
		console.log(response);
		document.getElementById("amount").value = "";
		document.getElementById("description").value = "";
	}
}

async function approveReimbursement(id) {
	console.log("approve " + id)
	
	let response = await fetch(url + "reimbursement/" + id + "/approve", {
		method: "POST",
		credentials: "include"
	});
	
	if(response.status === 200) {
		pendingFunc();
	}
}

async function denyReimbursement(id) {
	let response = await fetch(url + "reimbursement/" + id + "/deny", {
		method: "POST",
		credentials: "include"
	});
	
	if(response.status === 200) {
		pendingFunc();
	}
}
