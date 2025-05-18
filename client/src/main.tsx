import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faRightLeft, faUser } from '@fortawesome/free-solid-svg-icons'

library.add(faRightLeft, faUser)

createRoot(document.getElementById('root')!).render(
	<StrictMode>
		<App />
	</StrictMode>
)
