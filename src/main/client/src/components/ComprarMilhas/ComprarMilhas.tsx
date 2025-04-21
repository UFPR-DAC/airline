import React from "react";

export default function ComprarMilhas() {
    const valorMilha = 5;
    const [quantidadeMilhas, setQuantidadeMilhas] = React.useState(0);
    const [valorTotal, setValorTotal] = React.useState(0);

    function calcularValorTotal(){
        const valor = quantidadeMilhas * valorMilha;
        return setValorTotal(valor);
    }

    return (
        <div className="min-h-screen flex items-center justify-center">
            <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md" style={{ backgroundColor: '#FEE685' }}>
                <h1 className="text-2xl font-bold mb-6 text-center">Comprar Milhas</h1>
                <form className="space-y-4">

                    <div>
                        <label htmlFor="milhas" className="block text-sm font-medium">Quantidade de Milhas</label>
                        <input
                            type="number"
                            id="milhas"
                            placeholder="0"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                            onChange={(e) => setQuantidadeMilhas(Number(e.target.value))}
                            onBlur={calcularValorTotal}
                        />
                    </div>

                    <div>
                        <label htmlFor="valor" className="block text-sm font-medium">Valor</label>
                        <input
                            type="text"                            
                            id="valor"
                            readOnly
                            placeholder={valorTotal.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                        
                    </div>

                    <button type="submit" style={{ backgroundColor: '#dc4435' }} className="text-white px-4 py-2 rounded-md w-full">
                        Comprar
                    </button>
                </form>
            </div>
        </div>
    )
}