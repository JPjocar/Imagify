
const gallery = document.querySelector(".gallery");
const modal_container = document.getElementById('modal_container');
const close = document.getElementById('close');

gallery.addEventListener('click', mostrarImagen);


function mostrarImagen(e){
  if(e.target.classList.contains("item-img")){
    leeDatos(e.target.nextElementSibling);  
    modal_container.classList.add('show'); 
  }
}

function leeDatos(imagenDetalles){
  const imgInfo = {
    id: imagenDetalles.getAttribute("data-id"),
    titulo: imagenDetalles.querySelector("h1").textContent,
    descripcion: imagenDetalles.querySelector("p").textContent,
    category: imagenDetalles.querySelector(".category").textContent,
    tags: imagenDetalles.querySelector(".tags").innerHTML,
    src: imagenDetalles.previousElementSibling.src
  }
  imagenHTML(imgInfo);
}

function imagenHTML(img){
  modal_container.querySelector(".title").textContent = img.titulo;
  modal_container.querySelector(".description").textContent = img.descripcion;
  modal_container.querySelector(".show-img").src = img.src;
  modal_container.querySelector(".category").textContent = img.category;
  modal_container.querySelector(".tags").innerHTML = img.tags;
  modal_container.querySelector("#deleteImage").href = `http://localhost:8080/images/${img.id}/delete`;
  modal_container.querySelector("#hideImage").href = `http://localhost:8080/images/${img.id}/hide`;
  modal_container.querySelector("#showImage").href = `http://localhost:8080/images/${img.id}/show`;
}

close.addEventListener('click', () => {
  modal_container.classList.remove('show');
});