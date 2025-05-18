import { useForm, Controller } from 'react-hook-form'
import { FlightSearchParams } from '../../types/flightSearch'
import { zodResolver } from '@hookform/resolvers/zod'
import { useHookFormMask } from 'use-mask-input'
import { useNavigate } from 'react-router'
import { useSearch } from '../../contexts/FlightSearchContext'

export default function FlightSearch() {
	const navigate = useNavigate()
	const { runSearch } = useSearch()
	const today = new Date().toLocaleDateString()

	const {
		control,
		register,
		handleSubmit,
		formState: { errors },
	} = useForm<FlightSearchParams>({
		resolver: zodResolver(FlightSearchParams),
		defaultValues: {
			origin: '',
			destination: '',
			departureDate: today,
			arrivalDate: '',
		},
	})
	const registerWithMask = useHookFormMask(register)

	const onSubmit = (data: FlightSearchParams) => {
		runSearch(data)
		navigate('/busca')
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
										<div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6" />
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
														if (inputOrigin.length > 3) inputOrigin.slice(0, 3)
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
						{/*<FontAwesomeIcon
						icon={['fas', 'right-left']}
						className="px-4 py-4 rounded-full absolute right-170 top-33 border-1 border-gray-300 bg-white"
					/>*/}
						<div className="flex flex-col">
							<div className="flex items-center rounded-l-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-400">
								<label htmlFor="destination" className="block text-sm/6 font-medium text-gray-900">
									Destino
								</label>
								<div className="m-2">
									<div className="flex items-center">
										<div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6" />
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
														if (inputDestination.length > 3) inputDestination.slice(0, 3)
														onChange(inputDestination)
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
					</div>
					<div className="flex">
						<div className="flex flex-col">
							<div className="flex w-40 items-center rounded-l-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-400">
								<label htmlFor="departureDate" className="block text-sm/6 font-medium text-gray-900">
									Ida
								</label>
								<div className="m-2">
									<div className="flex items-center">
										<div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6" />
										<input
											id="departureDate"
											type="tel"
											{...registerWithMask('departureDate', '99/99/9999', {
												required: true,
											})}
											placeholder={today}
											className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
										/>
									</div>
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
									<div className="flex items-center">
										<div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6" />
										<input
											id="arrivalDate"
											type="tel"
											{...registerWithMask('arrivalDate', '99/99/9999', {
												required: true,
											})}
											placeholder="dd/mm/yyyy"
											className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
										/>
									</div>
								</div>
							</div>
							{errors.arrivalDate && <p className="input-error-msg">{errors.arrivalDate?.message}</p>}
						</div>
					</div>
					<button type="submit" className="cursor-pointer w-40 text-white bg-green-600 p-4 rounded-full">
						Pesquisar
					</button>
				</form>
			</div>
		</div>
	)
}
