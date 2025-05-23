import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod';
import { useHookFormMask } from 'use-mask-input'
import { useLocation, useNavigate } from 'react-router'
import { Editar, EditarFunc } from '../../../types/editarFuncionario'
export default function EditarFuncionario() {
    const navigate = useNavigate()
    const location = useLocation()
    const dados = location.state as EditarFunc

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<EditarFunc>({
        resolver: zodResolver(Editar),
        mode: 'onChange',
        defaultValues: dados,
    })

    const registerWithMask = useHookFormMask(register)

    const onSubmit = (data: EditarFunc) => console.log(data)

    function editarFuncionario() {
        
    }

    return (
        <div className="flex flex-col items-center justify-center gap-8">
            <h1 className="font-medium">Editar Funcionário</h1>
            <form  onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-fit items-center gap-4">
                
                <div className="input-container w-84">
                    <label htmlFor="nome" className="input-label">
                        Nome
                    </label>
                    <input readOnly id="nome" type="text" {...register('nome')} className="input-base"/>
                    {errors.nome && <p className="input-error-msg">{errors.nome?.message}</p>}
                </div>

                <div className="input-container w-84">
                    <label htmlFor="cpf" className="input-label">
                        CPF
                    </label>
                    <input readOnly id="cpf" type="text" {...registerWithMask('cpf', 'cpf', { required: true})}  className="input-base"/>
                    {errors.cpf && <p className="input-error-msg">{errors.cpf?.message}</p>}
                </div>
                
                <div className="input-container w-84">
                    <label htmlFor="email" className="input-label">
                        Email
                    </label>
                    <input id="email" type="tel" {...register('email')} className="input-base"/>
                    {errors.email && <p className="input-error-msg">{errors.email?.message}</p>}
                </div>

                <div className="input-container w-84">
                    <label htmlFor="telefone" className="input-label">
                        Telefone
                    </label>
                    <input id="telefone" type="text" {...registerWithMask('telefone', ['(99) 9 9999-9999'], { required: true})} className="input-base"/>
                    {errors.telefone && <p className="input-error-msg">{errors.telefone?.message}</p>}
                </div>

                <div className="flex flex-row w-84">
                    <button className="cursor-pointer mr-25 w-40 text-white bg-gray-600 p-4 rounded-full hover:bg-gray-500"
                            onClick={() => navigate('/funcionario')} type="button">
                        Voltar
                    </button>
                    <button className="cursor-pointer w-40 text-white bg-green-600 p-4 rounded-full hover:bg-green-500"
                            onClick={() => alert("Dados do funcionário editados com sucesso")}>
                        Editar
                    </button>
                </div>


            </form>
        </div>
    )
}

