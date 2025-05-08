import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { Cadastro, CadastroFuncionario } from '../../../types/cadastroFuncionario'
import { useNavigate } from 'react-router'

export default function InserirFuncionario() {
    const navigate = useNavigate()
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<CadastroFuncionario>({
        resolver: zodResolver(Cadastro),
        mode: 'onChange',
    })
    const onSubmit = (data: CadastroFuncionario) => console.log(data)

    return (
        <div className="flex flex-col items-center justify-center gap-8">
            <h1 className="font-medium">Cadastrar Funcionários</h1>
            <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-fit items-center gap-4">
                <div className="input-container w-84">
                    <label htmlFor="email" className="input-label">
                        Email
                    </label>
                    <input id="email" type="tel" {...register('email')} className="input-base" />
                    {errors.email && <p className="input-error-msg">{errors.email?.message}</p>}
                </div>
                <div className="input-container w-84">
                    <label htmlFor="nome" className="input-label">
                        Nome
                    </label>
                    <input id="nome" type="text" {...register('nome')} className="input-base" />
                    {errors.nome && <p className="input-error-msg">{errors.nome?.message}</p>}
                </div>
                <div className="flex flex-row w-84">
                    <button className="cursor-pointer mr-25 w-40 text-white bg-gray-600 p-4 rounded-full hover:bg-gray-500"
                            onClick={() => navigate('/funcionario')} type="button">
                        Voltar
                    </button>
                    <button className="cursor-pointer w-40 text-white bg-green-600 p-4 rounded-full hover:bg-green-500"
                            onClick={() => alert("Um email com uma senha será enviado para o funcionário")}>
                        Cadastrar
                    </button>
                </div>


            </form>
        </div>
    )
}

