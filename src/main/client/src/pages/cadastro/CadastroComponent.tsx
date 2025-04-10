import Cadastro from "../../components/Cadastro/Cadastro";

function CadastroComponent() {
    return (
        <>
            <div className="relative bg-cover bg-center min-h-screen w-full bg-no-repeat bg-fixed">
                <div className="relative z-10 flex items-center justify-center px-4">
                    <Cadastro />
                </div>
            </div>
        </>
    );
}

export default CadastroComponent;