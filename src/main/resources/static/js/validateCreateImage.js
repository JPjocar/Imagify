const inputImg = document.getElementById("imageFile");
const showImgInput = document.getElementById("showImgInput");

inputImg.addEventListener("change", (e) => {
    const input = e.target;
    if(!input.files.length){
        showImgInput.src = "";
        alert("Select a image");
        return;
    }
    const valid = isValid(input, input.files?.[0]?.type,
        [new ValidatorJPEG(), new ValidatorJPG, new ValidatorPNG], 
        "*Este campo no cumple con JPG, JPEG, PNG");
    
    if(valid){
        objectUrl = URL.createObjectURL(input.files[0]);
        showImgInput.src = objectUrl;
    }else{
        showImgInput.src = "";
    }
    
})

const form = document.getElementById("form-create-image");

form.addEventListener("submit", (e) => {
    e.preventDefault();
    if(formValidate(form)){
        console.log("FORM VALID");
        form.submit();
    }else{
        console.log("FORM NO VALID");
    }
});


function formValidate(form){
    const title = form.querySelector("#title");
    const description = form.querySelector("#description");
    const imgFile = form.querySelector("#imageFile");

    const a = isValid(title, title.value, [new ValidatorRequired()], "*Este campo es requerido")
    const b = isValid(description, description.value, [new ValidatorRequired()], "*Este campo es requerido")
    const c = isValid(imgFile, imgFile.files?.[0]?.type,
        [new ValidatorJPEG(), new ValidatorJPG, new ValidatorPNG], 
        "*Este campo no cumple con JPG, JPEG, PNG");
    
    return a & b & c;
}

function isValid(input, value, validators, errorMessage){
    const valid = validators.some(validator => validator.isValid(value));
    if(valid){
        input.nextElementSibling.classList.add("hidden");
    }else{
        input.nextElementSibling.classList.remove("hidden");
        input.nextElementSibling.textContent = errorMessage;
    }
    return valid;
}


class ValidatorRequired{
    isValid(value){
        return value.trim();
    }
}

class ValidatorJPG{
    isValid(value){
        if(value == "image/jpg") return true;
        return false;
    }
}

class ValidatorPNG{
    isValid(value){
        if(value == "image/png") return true;
        return false;
    }
}

class ValidatorJPEG{
    isValid(value){
        if(value == "image/jpeg") return true;
        return false;
    }
}