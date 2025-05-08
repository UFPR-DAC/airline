import Tabela from "../../Tabela/tabela";
import { useNavigate } from 'react-router'

export default function VisualizarFuncionario() {
const navigate = useNavigate()
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

    function EditarFuncionario() {
        navigate('/funcionario/editar');
    }

    function ExcluirFuncionario() {
        alert("Excluir funcionario");
    }


    return (
        <>

            <div className="">
                <div className="flex justify-between items-center">
                    <h1 className="text-2xl font-bold mb-6 pr-100">Funcionários</h1>
                    <div className="flex ">
                        <button className="cursor-pointer bg-green-500 text-white px-4 py-2 rounded" 
                                onClick={() => navigate('/funcionario/inserir')}>
                            Cadastrar Funcionário
                        </button>
                    </div>
                </div>

            </div>
            <div className="w-full max-w-6xl bg-white p-6 rounded-lg shadow-md">
                <Tabela colunas={colunas} dados={dados} buttons={buttons} />
            </div>
        </>
    );

}