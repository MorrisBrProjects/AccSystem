function login() {
    let formData = new FormData();
        formData.append('username', document.getElementById("username").value);
        formData.append('password', document.getElementById("password").value);
        fetch("/login",
            {
                body: formData,
                method: "post"
            }).then((response) => {
    if (response.status == 200) {
    //document.getElementById("info").innerHTML="Erfolgreich angemeldet!";
      window.location.replace("/");
    } else if (response.status == 401) {
      //document.getElementById("info").innerHTML="Password incorekt!";
      Swal.fire({
      	title: "Fehler",
      	text: "Username oder Passwort ist falsch!",
      	icon: 'error'
      });
    }
});
}



function register() {
    let formData = new FormData();
        formData.append('username', document.getElementById("username").value);
        formData.append('password', document.getElementById("password").value);
        fetch("/register",
            {
                body: formData,
                method: "post"
            }).then((response) => {
    if (response.status == 200) {
    //document.getElementById("info").innerHTML="Erfolgreich angemeldet!";
          Swal.fire({
          	title: "Erfolg",
          	text: "Erfolgreich registriert, du wirst zum Login weitergeleitet!",
          	icon: 'success'
          });
      window.location.replace("/");
    } else if (response.status == 401) {
      //document.getElementById("info").innerHTML="Password incorekt!";
      Swal.fire({
      	title: "Fehler",
      	text: "Username wird bereits verwendet!",
      	icon: 'error'
      });
    } else if (response.status == 403) {
            //document.getElementById("info").innerHTML="Password incorekt!";
        Swal.fire({
            title: "Fehler",
            text: "Benutzername oder Passwort zu kurz! (Mindestens 5 Zeichen)",
            icon: 'error'
        });
    }
});
}
