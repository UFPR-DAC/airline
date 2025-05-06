import TabGroup from '../../components/TabGroup/TabGroup'

export default function ClientHome() {
	return (
		<>
			<div
				className="relative bg-cover bg-center min-h-screen w-full bg-no-repeat bg-fixed"
				style={{
					backgroundImage: "url('/images/raphael-nogueira-unsplash.jpg')",
				}}
			>
				<div className="relative z-10 flex items-center justify-center px-4">
					<TabGroup />
				</div>
			</div>
		</>
	)
}
