export default function ClientDashboard() {
	return (
		<>
			<div className="flex flex-col relative min-h-screen w-full bg-amber-200 justify-center">
				<div className="flex flex-col gap-4 items-center">
					<h2>Seus pontos</h2>
					<p className="font-medium text-4xl">43.607</p>
				</div>
				<div className="px-80">
					<button className="cursor-pointer text-white bg-green-600 p-4 rounded-full hover:bg-green-500">
						Usar pontos
					</button>
				</div>
			</div>
		</>
	)
}
