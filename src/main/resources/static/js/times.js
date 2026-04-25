document.addEventListener("DOMContentLoaded", () => {
    carregarIntegrantes();
});

function irParaIntegrantes(){
    window.location.href = "integrantes.html";
}

async function carregarIntegrantes() {
    const response = await fetch("/integrantes");
    const integrantes = await response.json();

    const container = document.getElementById("listaIntegrantes");
    container.innerHTML = "";

    integrantes.forEach(i => {
        const div = document.createElement("div");

        div.classList.add("item-integrante");

        div.innerHTML = `
            <div class="info">
                <span class="nome">${i.nome}</span>
                <span class="funcao">${i.funcao}</span>
            </div>
            <input type="checkbox" value="${i.id}" />
        `;

        container.appendChild(div);
    });
}

async function criarTime() {
    const mensagem = document.getElementById("mensagem");
    mensagem.classList.remove("mensagem-sucesso", "mensagem-erro");

    const nome = document.getElementById("nomeTime").value;
    const data = document.getElementById("dataTime").value;

    const selecionados = document.querySelectorAll("#listaIntegrantes input:checked");
    const ids = Array.from(selecionados).map(c => Number(c.value));

    if(!nome){
        mensagem.innerText = "Informe o nome do time";
        mensagem.classList.add("mensagem-erro");
        return;
    }

    if(!data){
        mensagem.innerText = "Informe a data";
        mensagem.classList.add("mensagem-erro");
        return;
    }

    if(ids.length === 0){
        mensagem.innerText = "Selecione pelo menos um integrante";
        mensagem.classList.add("mensagem-erro");
        return;
    }

    const response = await fetch("/times", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            nomeDoClube: nome,
            data: data,
            idIntegrante: ids
        })
    });

    if(response.ok){
        mensagem.innerText = "Time criado";
        mensagem.classList.add("mensagem-sucesso");

        document.getElementById("nomeTime").value = "";
        document.querySelectorAll("#listaIntegrantes input").forEach(c => c.checked = false);
    } else {
        const erro = await response.json();


        mensagem.innerText = "Erro: " + erro.message;
        mensagem.classList.add("mensagem-erro");
    }
}