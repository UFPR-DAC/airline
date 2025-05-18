
import { useForm } from 'react-hook-form';
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function Cadastro() {

    const navigate = useNavigate();
    const { register, setValue, setFocus } = useForm();
    const [buttonColor, setButtonColor] = useState("#FEE685");

    const checkCEP = (e) => {
        const cep = e.target.value.replace(/\D/g, '');
        fetch(`https://viacep.com.br/ws/${cep}/json/`)
            .then(res => res.json()).then(data => {
                console.log(data);
                setValue('rua', data.logradouro);
                setValue('cidade', data.localidade);
                setValue('estado', data.uf);
                setFocus('numero');

            });
    }

    return (
        <div className="flex justify-center items-center min-h-screen p-8">
            <form style={{backgroundColor: '#FEE685'}} className="bg-white p-8 rounded-2xl shadow-md w-full max-w-3xl">
                <h1 className="text-2xl font-bold mb-6 text-center">Cadastro</h1>

                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                    <div>
                        <label htmlFor="nome" className="block text-sm font-medium">Nome Completo</label>
                        <input
                            type="text"
                            id="nome"
                            placeholder="Nome Completo"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="cpf" className="block text-sm font-medium">CPF</label>
                        <input
                            type="text"
                            id="cpf"
                            placeholder="000.000.000-00"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="email" className="block text-sm font-medium">E-mail</label>
                        <input
                            type="email"
                            id="email"
                            placeholder="seuemail@email.com"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="cep" className="block text-sm font-medium">CEP</label>
                        <input
                            {...register('cep')}
                            type="text"
                            id="cep"
                            placeholder="00000-000"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                            onBlur={checkCEP}
                        />
                    </div>

                    <div>
                        <label htmlFor="rua" className="block text-sm font-medium">Rua</label>
                        <input
                            {...register('rua')}
                            type="text"
                            id="rua"
                            placeholder="Rua Exemplo"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="numero" className="block text-sm font-medium">Número</label>
                        <input
                            {...register('numero')}
                            type="text"
                            id="numero"
                            placeholder="123"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="complemento" className="block text-sm font-medium">Complemento</label>
                        <input
                            {...register('complemento')}
                            type="text"
                            id="complemento"
                            placeholder="Apto, Bloco etc."
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="cidade" className="block text-sm font-medium">Cidade</label>
                        <input
                            {...register('cidade')}
                            type="text"
                            id="cidade"
                            placeholder="Curitiba"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div className="sm:col-span-2">
                        <label htmlFor="estado" className="block text-sm font-medium">Estado</label>
                        <input
                            {...register('estado')}
                            type="text"
                            id="estado"
                            placeholder="PR"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>
                </div>

                <div className="flex justify-between mt-6">
                    <button type="button" className="px-4 py-2 rounded-md" onClick={() => navigate("/login")}  style={{ backgroundColor: buttonColor }} onMouseEnter={() => setButtonColor("#FF9632")} onMouseLeave={() => setButtonColor("#FEE685")}>Login</button>
                    <button type="submit" style={{ backgroundColor: '#dc4435' }} className="text-white px-4 py-2 rounded-md">Cadastrar</button>
                </div>
            </form>
        </div>
    );
}

