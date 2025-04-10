import { useForm } from 'react-hook-form';

export default function Cadastro() {

    const { register, setValue, setFocus } = useForm();

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
        <div className="flex justify-center items-center min-h-screen p-8 bg-gray-200">  {/* bg-gradient-to-r from-purple-300 via-yellow-300 to-red-500 */}
            <form className="bg-white p-8 rounded-2xl shadow-md w-full max-w-3xl">
                <h1 className="text-2xl font-bold mb-6 text-center">Cadastro</h1>

                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                    <div>
                        <label htmlFor="nome" className="block text-sm font-medium text-gray-700">Nome Completo</label>
                        <input
                            type="text"
                            id="nome"
                            placeholder="Nome Completo"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="cpf" className="block text-sm font-medium text-gray-700">CPF</label>
                        <input
                            type="text"
                            id="cpf"
                            placeholder="000.000.000-00"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="email" className="block text-sm font-medium text-gray-700">E-mail</label>
                        <input
                            type="email"
                            id="email"
                            placeholder="seuemail@email.com"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="cep" className="block text-sm font-medium text-gray-700">CEP</label>
                        <input
                            {...register('cep')}
                            type="text"
                            id="cep"
                            placeholder="00000-000"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                            onBlur={checkCEP}
                        />
                    </div>

                    <div>
                        <label htmlFor="rua" className="block text-sm font-medium text-gray-700">Rua</label>
                        <input
                            {...register('rua')}
                            type="text"
                            id="rua"
                            placeholder="Rua Exemplo"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="numero" className="block text-sm font-medium text-gray-700">Número</label>
                        <input
                            {...register('numero')}
                            type="text"
                            id="numero"
                            placeholder="123"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="complemento" className="block text-sm font-medium text-gray-700">Complemento</label>
                        <input
                            {...register('complemento')}
                            type="text"
                            id="complemento"
                            placeholder="Apto, Bloco etc."
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="cidade" className="block text-sm font-medium text-gray-700">Cidade</label>
                        <input
                            {...register('cidade')}
                            type="text"
                            id="cidade"
                            placeholder="Curitiba"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div className="sm:col-span-2">
                        <label htmlFor="estado" className="block text-sm font-medium text-gray-700">Estado</label>
                        <input
                            {...register('estado')}
                            type="text"
                            id="estado"
                            placeholder="PR"
                            className="mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>
                </div>

                <div className="flex justify-between mt-6">
                    <button type="button" className="bg-gray-300 text-gray-700 px-4 py-2 rounded-md">Voltar</button>
                    <button type="submit" style={{ backgroundColor: '#dc4435' }} className="text-white px-4 py-2 rounded-md">Cadastrar</button>
                </div>
            </form>
        </div>
    );
}

