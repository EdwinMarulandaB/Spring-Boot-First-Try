$(document).ready(function() {
 // Inicio
});


async function iniciarSesion(){

  let dato ={
    email:document.getElementById('txtEmail').value,
    password:document.getElementById('txtPassword').value
  };

     const request = await fetch('api/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(dato)
      });
      const respuesta = await request.text()
      if(respuesta != "FAIL"){
      localStorage.token = respuesta;
      localStorage.email = dato.email;
         window.location.href = 'usuarios.html'
      }else{
        alert("Las credenciales son incorrectas. Por favor intente nuevamente")
        location.reload();
      }

}