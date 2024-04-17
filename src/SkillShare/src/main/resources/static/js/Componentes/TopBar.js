class TopBar extends HTMLElement{
    constructor(){
        super();

    }

    async connectedCallback(){
        let topBar = await this.buildTopBar();

        this.appendChild(topBar);
    }

    async buildTopBar(){
        let container = document.createElement("div");
        container.classList.add("topBar");

        let menuBox = this.buildMenuBox();
        let topBarTitle = this.buildTopBarTitle();
        let topBarOptionsBox = await this.buildTopBarOptionsBox();

        container.appendChild(menuBox);
        container.appendChild(topBarTitle);
        container.appendChild(topBarOptionsBox);

        return container;

    }

    buildMenuBox(){
        let div = document.createElement("div");
        div.classList.add("topBarMenuButton");
        div.onclick = function(){
            let menuBackground = document.getElementsByClassName("menuBackground")[0];
            let menu = document.getElementsByClassName("menu")[0];

            if(menuBackground.classList.contains("show")){
                menuBackground.classList.remove("show");
                menuBackground.classList.add("hide");

                menu.classList.remove("show");
                menu.classList.add("hide");
            }else{
                menuBackground.classList.remove("hide");
                menuBackground.classList.add("show");

                menu.classList.remove("hide");
                menu.classList.add("show");
            }
            
        }

        let span = document.createElement("span");
        span.className = "fas fa-bars topBarMenuButton-faIcon";

        let label = document.createElement("label");
        label.textContent = "Menu";

        div.appendChild(span);
        div.appendChild(label);

        return div;
    }

    buildTopBarTitle(){
        let label = document.createElement("label");
        label.classList.add("topBarTitle");
        label.onclick = function () {
            window.location.href = "/";
        }
        
        let negrito = document.createElement("b");
        negrito.textContent = "SkillShare";

        label.appendChild(negrito);

        return label;
    }

    async buildTopBarOptionsBox(){
        let response = await serverRequester.fazerGet("/usuario/isLogged");

        let div = document.createElement("div");
        div.classList.add("topBarOptionsDiv");

        let label = document.createElement("label");
        label.classList.add("topBarOptionLabel");
        if(response["responseJson"] == false){
            label.textContent = "Entrar";
            label.onclick = function(){
                window.location.href = "login";

            }

        }else{
            label.textContent = "Sair";
            label.onclick = function(){
                serverRequester.fazerGet("/usuario/logout");
                window.location.href = "/";

            }

        }

        div.appendChild(label);

        return div;
    }

}

// Declara a nova tag para que seja reconhecida na página HTML
customElements.define("top-bar", TopBar);
