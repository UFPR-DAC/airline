import { useLocation, useNavigate } from 'react-router'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod';
import { Confirmacao, ConfirmacaoEmb } from '../../types/confirmacaoEmbarque';

export default function ConfirmacaoEmbarque() {
    const navigate = useNavigate()
    const location = useLocation()
    const dados = location.state as ConfirmacaoEmb
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<ConfirmacaoEmb>({
        resolver: zodResolver(Confirmacao),
        mode: 'onChange',
        defaultValues: dados,
    })

    const onSubmit = (data: ConfirmacaoEmb) => console.log(data)


    return (
        <>
            <div className="flex flex-col items-center justify-center gap-8">
                <h1 className="font-medium">Confirmação de Embarque</h1>
                <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-fit items-center gap-4">

                    <div className="input-container w-84">
                        <label htmlFor="dataHora" className="input-label">
                            Data/Hora
                        </label>
                        <input readOnly id="dataHora" {...register('dataHora')} type="text" className="input-base" />
                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="aeroportoOrigem" className="input-label">
                            Aeroporto Origem
                        </label>
                        <input readOnly id="aeroportoOrigem" {...register('aeroportoOrigem')} type="text" className="input-base" />

                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="aeroportoDestino" className="input-label">
                            Aeroporto Destino
                        </label>
                        <input readOnly id="aeroportoDestino" {...register('aeroportoDestino')} type="tel" className="input-base" />

                    </div>

                    <div className="input-container w-84">
                        <label htmlFor="codigoReserva" className="input-label">
                            Código de Reserva
                        </label>
                        <input id="codigoReserva" {...register('codigoReserva')} type="text" className="input-base" />
                        {errors.codigoReserva && <p className="input-error-msg">{errors.codigoReserva?.message}</p>}
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
    );

}