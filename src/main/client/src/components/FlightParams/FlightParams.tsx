import { useForm, Controller } from 'react-hook-form'
import { FlightSearchParams } from '../../types/flightSearch'
import { zodResolver } from '@hookform/resolvers/zod'
import { useHookFormMask } from 'use-mask-input'
import formatIntlToBrDate from '../../utils/functions/formatDate'

export default function FlightParams() {
	const today = new Date().toLocaleDateString()

	const savedSearch = localStorage.getItem('search')
	const parsedSearch = savedSearch ? JSON.parse(savedSearch) : null

	console.log('parsed search')
	console.log(parsedSearch)

	const {
		control,
		register,
		handleSubmit,
		formState: { errors },
	} = useForm<FlightSearchParams>({
		resolver: zodResolver(FlightSearchParams),
		defaultValues: {
			origin: parsedSearch?.origin || '',
			destination: parsedSearch?.destination || '',
			departureDate: formatIntlToBrDate(parsedSearch?.departureDate) || today,
			arrivalDate: formatIntlToBrDate(parsedSearch?.arrivalDate) || '',
		},
	})

	const registerWithMask = useHookFormMask(register)

	const onSubmit = (data: FlightSearchParams) => {
		console.log('nova busca ', data)

		const searchData = {
			origin: data.origin,
			destination: data.destination,
			departureDate: data.departureDate,
			arrivalDate: data.arrivalDate,
		}

		localStorage.setItem('search', JSON.stringify(searchData))
	}

	return (
		<div className="flex flex-col gap-2">
			<div className="flex gap-4 bg-amber-200 p-8 rounded-2xl rounded-tl-none">
				<form onSubmit={handleSubmit(onSubmit)} className="flex gap-4">
					<div className="flex">
						<div className="flex flex-col">
							<div className="flex items-center rounded-l-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-400">
								<label htmlFor="origin" className="block text-sm/6 font-medium text-gray-900">
									Origem
								</label>
								<div className="m-2">
									<div className="flex items-center">
										<Controller
											name="origin"
											control={control}
											render={({ field: { value, onChange, ...field } }) => (
												<input
													{...field}
													id="origin"
													name="origin"
													type="text"
													autoFocus
													placeholder="CWB"
													minLength={3}
													maxLength={3}
													value={value}
													onChange={(e) => {
														let inputOrigin = e.target.value.toUpperCase()
														if (inputOrigin.length > 3)
															inputOrigin = inputOrigin.slice(0, 3)
														onChange(inputOrigin)
													}}
													className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
												/>
											)}
										/>
									</div>
								</div>
							</div>
							{errors.origin && <p className="input-error-msg">{errors.origin?.message}</p>}
						</div>

						<div className="flex flex-col">
							<div className="flex items-center rounded-l-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-400">
								<label htmlFor="destination" className="block text-sm/6 font-medium text-gray-900">
									Destino
								</label>
								<div className="m-2">
									<div className="flex items-center">
										<Controller
											name="destination"
											control={control}
											render={({ field: { value, onChange, ...field } }) => (
												<input
													{...field}
													id="destination"
													name="destination"
													type="text"
													placeholder="GRU"
													minLength={3}
													maxLength={3}
													value={value}
													onChange={(e) => {
														let inputDestination = e.target.value.toUpperCase()
														if (inputDestination.length > 3)
															inputDestination = inputDestination.slice(0, 3)
														onChange(inputDestination)
													}}
													className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
												/>
											)}
										/>
									</div>
								</div>
							</div>
							{errors.destination && <p className="input-error-msg">{errors.destination?.message}</p>}
						</div>
					</div>

					<div className="flex">
						<div className="flex flex-col">
							<div className="flex w-40 items-center rounded-l-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-400">
								<label htmlFor="departureDate" className="block text-sm/6 font-medium text-gray-900">
									Ida
								</label>
								<div className="m-2">
									<input
										id="departureDate"
										{...registerWithMask('departureDate', '99/99/9999', {
											required: true,
										})}
										placeholder={today}
										className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
									/>
								</div>
							</div>
							{errors.departureDate && <p className="input-error-msg">{errors.departureDate?.message}</p>}
						</div>

						<div className="flex flex-col">
							<div className="flex w-40 items-center rounded-r-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-400">
								<label htmlFor="arrivalDate" className="block text-sm/6 font-medium text-gray-900">
									Volta
								</label>
								<div className="m-2">
									<input
										id="arrivalDate"
										{...registerWithMask('arrivalDate', '99/99/9999', {
											required: true,
										})}
										placeholder="dd/mm/yyyy"
										className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
									/>
								</div>
							</div>
							{errors.arrivalDate && <p className="input-error-msg">{errors.arrivalDate?.message}</p>}
						</div>
					</div>

					<button
						type="submit"
						className="cursor-pointer w-40 text-green-600 bg-transparent border-2 border-green-600 p-4 rounded-full font-medium"
					>
						Pesquisar
					</button>
				</form>
			</div>
		</div>
	)
}
