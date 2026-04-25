document.getElementById("formIntegrantes").addEventListener("submit", async function(e) {
    e.preventDefault();

    const data = {
        nome: document.getElementById("nome").value,
        funcao: document.getElementById("funcao").value
    };

    const response = await fetch("/integrantes", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    const mensagem = document.getElementById("mensagem");

    mensagem.classList.remove("mensagem-sucesso", "mensagem-erro");

    if (response.ok) {
        mensagem.innerText = "Integrante cadastrado!";
        mensagem.classList.add("mensagem-sucesso");
        this.reset();
    } else {
        const erro = await response.json();
        mensagem.innerText = "Erro: " + erro.message;
        mensagem.classList.add("mensagem-erro");
    }
});

function irParaTimes(){
    window.location.href = "times.html";
}