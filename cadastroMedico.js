$(document).ready(function () {

    $("#btnCadastrar").click(function () {
        $.ajax({
            url: 'medicoCTRL', //lugar onde a servlet está
            type: 'POST',
            data: {
                nome: $("#nomeMedico").val(),
                cpf: $("#cpfMedico").val(),
                email:$("#emailMedico").val(),
                especialidade:$("#especialidadeMedico").val(),
                oper: "cadastrar"
            },
            complete: function (e, xhr, result) {
                if (e.readyState == 4 && e.status == 200) {
                    try { //Converte a resposta HTTP JSON em um objeto JavaScript
                        var Obj = eval("(" + e.responseText + ")");

                    } catch (err) { //
                        // Mostra Aviso
                        alert("ERRO!");

                    }
                    if (Obj != null) {

                        //aqui serão tratados o retorno da controladora
                        if (Obj == 1) {

                            alert("Médico cadastrado com SUCESSO!");

                        } else {

                            alert("Algo de errado aconteceu!");

                        }
                    }
                }

            }
        });

    });

});
