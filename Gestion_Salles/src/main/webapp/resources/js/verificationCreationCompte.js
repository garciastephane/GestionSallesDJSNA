
var valide = false;

var auto = function auto() {

    document.getElementById("nom").addEventListener("change", function () {
        controler();
    });
    
    document.getElementById("prenom").addEventListener("change", function () {
        controler();
    });

    document.getElementById("mail").addEventListener("change", function () {
        controler();
    });
    
    document.getElementById("adresse").addEventListener("change", function () {
        controler();
    });
    
    document.getElementById("datenaissance").addEventListener("change", function () {
        controler();
    });
    
    document.getElementById("login").addEventListener("change", function () {
        controler();
    });
    
    document.getElementById("password").addEventListener("change", function () {
        controler();
    });
    
    if (valide) {
        document.getElementById("create").setAttribute("disabled", null);
    }
    else {
        document.getElementById("create").setAttribute("disabled");
    }
    
}();




function controlerNomPrenom(nom) {
    return RegExp("^[a-zA-Z\\-]+$").test(nom);
}

function controlerEmail(mail) {
    return RegExp("^[a-z]+([\\.][a-z]+)*@[a-z]+\\.[a-z]{2,3}$").test(mail) ;
}

function controlerAdresse(adresse) {
    return RegExp("^[\w\s]+$").test(adresse);
}

function controlerDate(date) {
    return RegExp("(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/([1-9][0-9]{3}|0[1-9][0-9]{2}|00[1-9][0-9]{1}|000[1-9])").test(date);
}

function controlerLoginMDP(login) {
    return RegExp("^[\w\s]+$").test(login);
}


function controler() {
    valide = controlerNomPrenom(document.getElementById("nom").value)
    && controlerNomPrenom(document.getElementById("prenom").value)
    && controlerEmail(document.getElementById("mail").value)
    && controlerAdresse(document.getElementById("adresse").value)
    && controlerDate(document.getElementById("date").value)
    && controlerLoginMDP(document.getElementById("login").value)
    && controlerLoginMDP(document.getElementById("password").value)
    && controlerLoginMDP(document.getElementById("password2").value);
    
    if (valide) {
        document.getElementById("create").setAttribute("disabled", null);
    }
    else {
        document.getElementById("create").setAttribute("disabled");
    }
}