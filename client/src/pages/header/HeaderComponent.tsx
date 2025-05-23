function HeaderComponent() {
	return (
		<nav className="flex items-center justify-center h-16 w-full bg-gradient-to-r from-purple-300 via-yellow-300 to-red-500 text-white font-semibold">
			<div className="max-w-screen-xl grid md:grid-cols-3 grid-cols-2 items-center  mx-auto p-3 h-16">
				<label htmlFor="">Exemplo </label>
				<a href="">
					<img src="images/solis.png" alt="" />
				</a>
			</div>
		</nav>
	)
}

export default HeaderComponent
