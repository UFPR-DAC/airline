import Tabela from "../../Tabela/tabela";
import { useNavigate } from 'react-router'

export default function FuncionarioHome() {
    const navigate = useNavigate()

    const colunas = [
        { campo: "dataHora", titulo: "Data/Hora" },
        { campo: "aeroportoOrigem", titulo: "Aeroporto Origem" },
        { campo: "aeroportoDestino", titulo: "Aeroporto Destino" },
        { campo: "estado", titulo: "Estado" },
    ];

    const dados = [
        { dataHora: "27/08/2025 15:00:00", aeroportoOrigem: "CWB", aeroportoDestino: "DDC", estado: "CONFIRMADO" },
        { dataHora: "17/03/2025 12:00:00", aeroportoOrigem: "SSP", aeroportoDestino: "DCC", estado: "CANCELADO" },
        { dataHora: "07/12/2025 17:00:00", aeroportoOrigem: "COR", aeroportoDestino: "DOO", estado: "CONFIRMADO" },
    ];

    const buttons = [
        {
            body: "Embarcar",
            class: "bg-green-500 text-white px-4 py-2 rounded",
            action: (dado: { dataHora: string; aeroportoOrigem: string; aeroportoDestino: string }) => ConfirmacaoDeEmbarque(dado)
        },
        {
            body: "Cancelar Voo",
            class: "bg-red-500 text-white px-4 py-2 rounded",
            action: CancelamentoDoVoo
        },
        {
            body: "Realizar Voo ",
            class: "bg-blue-500 text-white px-4 py-2 rounded",
            action: RealizacaoDoVoo
        },
    ];

    function ConfirmacaoDeEmbarque(dado: { dataHora: string; aeroportoOrigem: string; aeroportoDestino: string }) {
        navigate('/funcionario/embarque', { state: dado });
    }

    function CancelamentoDoVoo() {
        // Mudar o Status do Voo, mudar somente o status caso o status atual seja "CONFIRMADO"
        // Caso contrário, não fazer nada
    }

    function RealizacaoDoVoo() {
        // Mudar o Status do Voo, mudar somente o status caso o status atual seja "CONFIRMADO"
        // Caso contrário, não fazer nada
    }


    return (
        <>

            <div className="">
                <div className="flex justify-between items-center">
                    <h1 className="text-2xl font-bold mb-6 pr-100">Home Funcionários</h1>
                </div>

            </div>
            <div className="w-full max-w-6xl bg-white rounded-lg shadow-md">
                <Tabela colunas={colunas} dados={dados} buttons={buttons} />
            </div>
        </>
    );

}