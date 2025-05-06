import Tabela from "../../common/Tabela/tabela";

export default function VisualizarFuncionario() {

    const colunas = [
        { campo: "nome", titulo: "Nome" },
        { campo: "email", titulo: "Email" },
    ];

    const dados = [
        { nome: "João Silva", email: "joao@email.com" },
        { nome: "Maria Oliveira", email: "maria@email.com" },
        { nome: "Carlos Souza", email: "carlos@email.com" },
    ];

    const buttons = [
        { body: "Editar", class: "bg-blue-500 text-white px-4 py-2 rounded", action: EditarFuncionario },
        { body: "Excluir", class: "bg-red-500 text-white px-4 py-2 rounded", action: ExcluirFuncionario },
    ];

    function EditarFuncionario(){
        alert("Editar funcionario");
    }

    function ExcluirFuncionario(){
        alert("Excluir funcionario");
    }


    return (
        <>
            <div className="flex flex-col items-center justify-center min-h-screen p-8 bg-gray-100">
                <div className="w-full max-w-6xl bg-white p-6 rounded-lg shadow-md">
                    <Tabela colunas={colunas} dados={dados} buttons={buttons} />
                </div>
            </div>
        </>
    );

}