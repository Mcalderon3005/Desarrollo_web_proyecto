/* La siguiente función se utiliza para visualizar la imagen seleccionada en la
 * página html donde se desea "cargar" utilizando un llamado "ajax"*/
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#blah')
                    .attr('src', e.target.result)
                    .height(200);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

/* La siguiente función se utiliza para activar la cantidad de elementos seleccionados
 * En el carrito de compras utilizando un llamado "ajax" */
function addCart(formulario) {
    var valor = formulario.elements[0].value;
    var url = '/carrito/agregar';
    url = url + '/' + valor;
    $("#resultsBlock").load(url);
}

function convertir() {
    var valore = parseInt(document.getElementById("valore").value);
    var resultado = 0;
    var usa = 0.21;
    var eu = 0.08;
    if (document.getElementById("uno").checked){
        resultado = valore * usa;
        alert("El valor con el IVA de USA incluido es de $" + resultado);
    }
    else if(document.getElementById("dos").checked) {
        resultado = valore * eu;
        alert("El valor con el IVA de EU incluido es de €" + resultado);
    }
    else {
        alert("Tienes que completar todos los requerimientos");
    }
}