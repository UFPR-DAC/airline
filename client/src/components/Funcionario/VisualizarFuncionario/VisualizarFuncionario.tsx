import axios from "axios";
import Tabela from "../../Tabela/tabela";
import { useNavigate } from 'react-router'
import { useEffect, useState } from "react";

export default function VisualizarFuncionario() {
    const navigate = useNavigate()

       const [listaFuncionarios, setListaFuncionarios] = useState<any[]>([]);

useEffect(() => {
  const fetchFuncionarios = async () => {
    try {
      const response = await axios.get("http://localhost:3000/funcionarios");
      if (response?.data) {
        setListaFuncionarios(response.data);
      }
    } catch (error) {
      console.error("Erro ao buscar funcionários:", error);
    }
  };

  fetchFuncionarios();
}, []);

    const colunas = [
        { campo: "nome", titulo: "Nome" },
        { campo: "cpf", titulo: "Email" },
        { campo: "email", titulo: "Email" },
        { campo: "telefone", titulo: "Email" },
    ];

    const dados = [
        { nome: "João Silva", cpf:"111.111.111-11", email: "joao@email.com", telefone: "(41) 9" },
        { nome: "Maria Oliveira", cpf:"222.222.222-22", email: "maria@email.com", telefone: "(41) 9" },
        { nome: "Carlos Souza", cpf:"333.333.333-33", email: "carlos@email.com", telefone: "(41) 9" },
    ];

    const buttons = [
        { 
            body: "Editar", 
            class: "bg-blue-500 text-white px-4 py-2 rounded", 
            action: (dado: {nome: string; email: string }) => EditarFuncionario(dado) 
        },
        { 
            body: "Excluir", 
            class: "bg-red-500 text-white px-4 py-2 rounded", 
            action: (dado: {nome: string; email: string }) => ExcluirFuncionario(dado) 
        },
    ];

    function EditarFuncionario(dado: { nome: string; email: string }) {
        navigate('/funcionario/editar', { state: dado });
    }

    function ExcluirFuncionario(dado: { nome: string; email: string }) {
        alert("Excluir funcionario");
    }


    return (
        <>

            <div className="">
                <div className="flex justify-between items-center">
                    <h1 className="text-2xl font-bold mb-6 pr-100">Funcionários</h1>
                    <div className="flex ">
                        <button className="cursor-pointer bg-green-500 text-white px-4 py-2 rounded"
                            onClick={() => navigate('/funcionario/cadastrar/funcionario')}>
                            Cadastrar Funcionário
                        </button>
                    </div>
                </div>

            </div>
            <div className="w-full max-w-6xl bg-white p-6 rounded-lg shadow-md">
                <Tabela colunas={colunas} dados={listaFuncionarios} buttons={buttons} />
            </div>
        </>
    );

}