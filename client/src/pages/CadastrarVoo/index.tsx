import { useLocation, useNavigate } from 'react-router'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod';
import { Cadastrar, CadastroVoo } from '../../types/cadastrarVoo';


export default function CadastrarVoo() 
{
    const navigate = useNavigate()
    const location = useLocation()
    const dados = location.state as CadastroVoo
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<CadastroVoo>({
        resolver: zodResolver(Cadastrar),
        mode: 'onChange',
        defaultValues: dados,
    })

    const onSubmit = (data: CadastroVoo) => console.log(data)



    return(
        <>
            <div className="flex flex-col items-center justify-center gap-8">
                <h1 className="font-medium">Cadastro de Voo</h1>
                <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-fit items-center gap-4">
                    <div className="input-container w-84">
                        <label htmlFor="dataHora" className="input-label">
                            Data/Hora
                        </label>
                        <input id="dataHora" {...register('dataHora')} type="datetime-local" className="input-base" />
                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="aeroportoOrigem" className="input-label">
                            Aeroporto Origem
                        </label>
                        <input id="aeroportoOrigem" {...register('aeroportoOrigem')} type="text" className="input-base" />
                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="aeroportoDestino" className="input-label">
                            Aeroporto Destino
                        </label>
                        <input id="aeroportoDestino" {...register('aeroportoDestino')} type="text" className="input-base" />
                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="valorPassagem" className="input-label">
                            Valor da Passagem
                        </label>
                        <input id="valorPassagem" {...register('valorPassagem')} type="text" className="input-base" />
                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="quantidadeMilhas" className="input-label">
                            Milhas
                        </label>
                        <input id="quantidadeMilhas" {...register('quantidadeMilhas')} type="text" className="input-base" />
                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="quantidadePoltronas" className="input-label">
                            Quantidade de Poltronas
                        </label>
                        <input id="quantidadePoltronas" {...register('quantidadePoltronas')} type="text" className="input-base" />
                    </div>

                    <div className="flex flex-row w-84">
                        <button className="cursor-pointer mr-25 w-40 text-white bg-gray-600 p-4 rounded-full hover:bg-gray-500"
                            type="button"
                            onClick={() => navigate('/funcionario')}>
                            Voltar
                        </button>
                        <button className="cursor-pointer w-40 text-white bg-green-600 p-4 rounded-full hover:bg-green-500">
                            Confirmar 
                        </button>
                    </div>
                </form>
            </div>
        </>
    )
}