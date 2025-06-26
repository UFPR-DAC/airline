import { useForm } from 'react-hook-form'
import { EmployeeSignup } from '../../validations/user'
import { zodResolver } from '@hookform/resolvers/zod'
import { useHookFormMask } from 'use-mask-input'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

export default function SignupFuncionario() {
	const {
		register,
		handleSubmit,
		watch,
		setValue,
		formState: { errors },
	} = useForm<EmployeeSignup>({
		resolver: zodResolver(EmployeeSignup),
		mode: 'onChange',
	})
	const registerWithMask = useHookFormMask(register)
	const navigate = useNavigate();

	const onSubmit = async (data: EmployeeSignup) => {
		const payload = {
			cpf: data.cpf,
			email: data.email,
			nome: data.nome,
            telefone: data.telefone
		}
		const response = await axios.post("http://localhost:3000/funcionarios", payload);
		alert(`Usuário ${response?.data?.nome} criado!`)
		navigate("/login");
	}

	return (
		<div className="flex flex-col items-center justify-center gap-8">
			<h1 className="font-medium">Cadastro de funcionário</h1>
			<form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-100vh gap-4">
				<div className="input-container w-64">
					<label htmlFor="cpf" className="input-label">
						CPF
					</label>
					<input
						id="cpf"
						type="tel"
						{...registerWithMask('cpf', 'cpf', {
							required: true,
						})}
						className="input-base"
					/>
					{errors.cpf && <p className="input-error-msg">{errors.cpf?.message}</p>}
				</div>
				<div className="input-container w-64">
					<label htmlFor="cpf" className="input-label">
						E-mail
					</label>
					<input
						id="email"
						type="email"
						{...registerWithMask('email', 'email', {
							required: true,
						})}
						className="input-base"
					/>
					{errors.email && <p className="input-error-msg">{errors.email?.message}</p>}
				</div>
				<div className="input-container w-84">
					<label htmlFor="nome" className="input-label">
						Nome completo
					</label>
					<input id="nome" type="text" {...register('nome')} className="input-base" />
					{errors.nome && <p className="input-error-msg">{errors.nome?.message}</p>}
				</div>
                				<div className="input-container w-84">
					<label htmlFor="telefone" className="input-label">
						Telefone
					</label>
					<input id="nome" type="text" {...register('nome')} className="input-base" />
					{errors.telefone && <p className="input-error-msg">{errors.telefone?.message}</p>}
				</div>
				<button
					type="submit"
					className="cursor-pointer w-84 text-white bg-green-600 p-4 rounded-full hover:bg-green-500"
				>
					Cadastrar
				</button>
			</form>
		</div>
	)
}
