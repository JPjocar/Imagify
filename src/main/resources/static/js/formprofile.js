
// Mostrar el modal "Editar perfil"
document.getElementById("editProfileButton").addEventListener("click", function () {
    document.getElementById("editProfileModal").style.display = "block";
});

// Cerrar el modal cerrar (x)
document.getElementById("closeModal").addEventListener("click", function () {
    document.getElementById("editProfileModal").style.display = "none";
});

// Cerrar el modal al hacer clic fuera de la ventana del modal
window.addEventListener("click", function (event) {
    const modal = document.getElementById("editProfileModal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
});



