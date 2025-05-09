import { zodResolver } from '@hookform/resolvers/zod'
import { useEffect, useState } from 'react'
import { useForm } from 'react-hook-form'
import { MilesTransaction } from '../../validations/miles'
import { useAuth } from '../../contexts/AuthContext'
import { fetchUserByCpf } from '../../services/user'
import { User } from '../../types/user'
import { useNavigate } from 'react-router'

export default function ComprarMilhas() {
	const navigate = useNavigate()
	const { user, login } = useAuth()
	const VALOR_MILHA = 5
	const [valorTotal, setValorTotal] = useState(0)

	const {
		register,
		handleSubmit,
		watch,
		setValue,
		formState: { errors },
	} = useForm<MilesTransaction>({
		resolver: zodResolver(MilesTransaction),
		mode: 'onChange',
	})
	const milesInput = watch('milesAmount') || ''

	useEffect(() => {
		const miles = watch('milesAmount')
		if (typeof milesInput === 'number') {
			const total = miles * VALOR_MILHA
			setValorTotal(total)
			setValue('currencyAmount', total, { shouldValidate: true })
		}
	}, [milesInput, setValue])

	const onSubmit = async (data: MilesTransaction) => {
		const transaction: MilesTransaction = {
			...data,
			operationType: 'PURCHASE',
		}
		console.log(transaction)
		if (user) {
			const updatedUser = await fetchUserByCpf(user.cpf)
			if (updatedUser) {
				const updatedMiles = updatedUser?.saldoMilhas
					? updatedUser.saldoMilhas + data.milesAmount
					: data.milesAmount
				const newUser: User = {
					...updatedUser,
					saldoMilhas: updatedMiles,
				}
				login(newUser)
				navigate(`/cliente/${newUser.cpf}`)
			} else {
				console.error('Usuário não encontrado')
			}
		}
	}

	const onError = (errors: any) => {
		console.error('Formulário não enviado', errors)
	}

	return (
		<div className="min-h-screen flex items-center justify-center">
			<div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md" style={{ backgroundColor: '#FEE685' }}>
				<h1 className="text-2xl font-bold mb-6 text-center">Comprar milhas</h1>
				<form onSubmit={handleSubmit(onSubmit, onError)} className="space-y-4">
					<div>
						<label htmlFor="milhas" className="block text-sm font-medium">
							Quantidade de milhas
						</label>
						<input
							type="tel"
							inputMode="numeric"
							id="milhas"
							{...register('milesAmount', {
								valueAsNumber: true,
							})}
							className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
						/>
					</div>

					<div>
						<label htmlFor="valor" className="block text-sm font-medium">
							Valor a pagar
						</label>
						<input
							type="text"
							id="valor"
							readOnly
							value={valorTotal.toLocaleString('pt-BR', {
								style: 'currency',
								currency: 'BRL',
							})}
							className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
						/>
					</div>
					{errors.milesAmount && <p className="input-error-msg">{errors.milesAmount?.message}</p>}
					{errors.currencyAmount && <p className="input-error-msg">{errors.currencyAmount?.message}</p>}
					{errors.operationType && <p className="input-error-msg">{errors.operationType?.message}</p>}
					<input type="hidden" value="PURCHASE" {...register('operationType')} />
					<button type="submit" className="cursor-pointer w-full text-white bg-green-600 p-4 rounded-full">
						Comprar
					</button>
				</form>
			</div>
		</div>
	)
}
