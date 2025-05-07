import { useForm } from 'react-hook-form'
import { User } from '../../types/user'
import { zodResolver } from '@hookform/resolvers/zod'

export default function Signup() {
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm<User>({
		resolver: zodResolver(User),
		mode: 'onChange',
	})
	const onSubmit = (data: User) => console.log(data)

	const mockEstados = [
		{
			label: 'Maranhão',
			value: 'MA',
		},
		{
			label: 'Paraná',
			value: 'PR',
		},
		{
			label: 'Rio Grande do Sul',
			value: 'RS',
		},
	]

	return (
		<div className="flex flex-col items-center justify-center gap-8">
			<h1 className="font-medium">Cadastro</h1>
			<form onSubmit={handleSubmit(onSubmit)} className="flex flex-col w-lg h-100vh gap-4">
				<div className="input-container w-64">
					<label htmlFor="cpf" className="input-label">
						CPF
					</label>
					<input id="cpf" type="text" {...register('cpf')} className="input-base" />
					{errors.cpf && <p className="input-error-msg">{errors.cpf?.message}</p>}
				</div>
				<div className="input-container w-64">
					<label htmlFor="cpf" className="input-label">
						E-mail
					</label>
					<input id="email" type="email" {...register('email')} className="input-base" />
					{errors.email && <p className="input-error-msg">{errors.email?.message}</p>}
				</div>
				<div className="input-container w-84">
					<label htmlFor="nome" className="input-label">
						Nome completo
					</label>
					<input id="nome" type="text" {...register('nome')} className="input-base" />
					{errors.nome && <p className="input-error-msg">{errors.nome?.message}</p>}
				</div>
				<div className="input-container w-40">
					<label htmlFor="cep" className="input-label">
						CEP
					</label>
					<input id="cep" type="text" {...register('cep')} className="input-base" />
					{errors.cep && <p className="input-error-msg">{errors.cep?.message}</p>}
				</div>
				<div className="flex flex-row gap-4">
					<div className="input-container w-60">
						<label htmlFor="logradouro" className="input-label">
							Logradouro
						</label>
						<input id="logradouro" type="text" {...register('logradouro')} className="input-base" />
						{errors.logradouro && <p className="input-error-msg">{errors.logradouro?.message}</p>}
					</div>
					<div className="input-container w-20">
						<label htmlFor="numero" className="input-label">
							Número
						</label>
						<input id="numero" type="text" {...register('numero')} className="input-base" />
						{errors.numero && <p className="input-error-msg">{errors.numero?.message}</p>}
					</div>
				</div>
				<div className="input-container">
					<label htmlFor="complemento" className="input-label">
						Complemento
					</label>
					<input id="complemento" type="text" {...register('complemento')} className="input-base" />
					{errors.complemento && <p className="input-error-msg">{errors.complemento?.message}</p>}
				</div>
				<div className="input-container w-84">
					<label htmlFor="bairro" className="input-label">
						Bairro
					</label>
					<input id="bairro" type="text" {...register('bairro')} className="input-base" />
					{errors.bairro && <p className="input-error-msg">{errors.bairro?.message}</p>}
				</div>
				<div className="flex flex-row gap-4">
					<div className="input-container w-84">
						<label htmlFor="cidade" className="input-label">
							Cidade
						</label>
						<input id="cidade" type="text" {...register('cidade')} className="input-base" />
						{errors.cidade && <p className="input-error-msg">{errors.cidade?.message}</p>}
					</div>
					<div className="input-container w-20 relative">
						<label htmlFor="estado" className="input-label">
							Estado
						</label>
						<select id="estado" {...register('estado')} className="input-base">
							{mockEstados &&
								mockEstados.map((estado) => <option value={estado.value}>{estado.value}</option>)}
							<div className="pointer-events-none absolute right-2 top-9">▼</div>
						</select>
					</div>
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
