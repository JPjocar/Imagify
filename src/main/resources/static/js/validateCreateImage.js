

const inputImg = document.getElementById("imageFile");
const showImgInput = document.getElementById("showImgInput");


inputImg.addEventListener("change", (e) => {
    const input = e.target;
    console.dir(input)
    if(!input.files.length){
        showImgInput.src = "";
        alert("Select a image");
        return;
    }
    const validators = new ValidatorsImage();
    validators.add([new ValidatorJPEG(), new ValidatorJPG, new ValidatorPNG])
    if(!validators.isValid(input.files[0].type)){
        alert("INVALID FORMAT. FORMAT VALID (JPG, JPEG, PNG)");
        return;
    }
    objectUrl = URL.createObjectURL(input.files[0]);
    showImgInput.src = objectUrl;
})

class Validator{
    isValid(value){
        throw new Error("This method is not avaialible")
    }
}

class ValidatorsImage extends Validator{
    constructor(){
        super();
        this.validators = [];
    }
    add(validatorsUser){
        this.validators = validatorsUser;
    }
    isValid(value){
        return this.validators.some(validator => validator.isValid(value))
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