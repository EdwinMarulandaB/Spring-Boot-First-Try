$(document).ready(function() {
 // Inicio
});


async function registrarUsuarios(){

  let dato ={
    nombre:document.getElementById('txtNombre').value,
    apellido:document.getElementById('txtApellido').value,
    telefono:document.getElementById('txtTelefono').value,
    email:document.getElementById('txtEmail').value,
    password:document.getElementById('txtPassword').value
  };

  if(document.getElementById('txtPassword').value != document.getElementById('txtRepetirPassword').value){
    alert("Las contraseñas no son iguales")
    location.reload();
    return;
  }
     const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(dato)
      });

      alert("Creaste un nuevo usuario")
      window.location.href = 'login.html'

}



